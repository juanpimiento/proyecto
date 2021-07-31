package com.juanpimiento.proyecto.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.juanpimiento.proyecto.R;
import com.juanpimiento.proyecto.databinding.FragmentHomeBinding;
import com.juanpimiento.proyecto.ui.database.model.Product;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private Activity myself;
    private FirebaseFirestore db;
    private ArrayList<Product> lista;
    private RecyclerView rbrows;
    private RecyclerView.Adapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        db = FirebaseFirestore.getInstance();
        myself = getActivity();
        db.collection("productos")

                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            lista = new ArrayList<Product>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Product nuevo = document.toObject(Product.class);
                                lista.add(nuevo);
                            }
                            loadProducts();
                        } else {
//                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        rbrows = root.findViewById(R.id.rv_favorites);
        rbrows.setLayoutManager(new LinearLayoutManager(getActivity()));



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void loadProducts(){

        mAdapter = new ProductAdapter(lista, myself);
        rbrows.setAdapter(mAdapter);

    }
}

class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{

    private List<Product> productModelList;
    private Activity myself;

    public ProductAdapter(List<Product> productModelList, Activity myself){
        this.productModelList = productModelList;
        this.myself = myself;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product , parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder holder, int position) {
        String name = this.productModelList.get(position).getNombre();
       // String description = this.productModelList.get(position).getDescripcion();
        String urlImage = this.productModelList.get(position).getImagen();
        String priceR = this.productModelList.get(position).getPrecio();
        String price = "$";
        for(int i = 0; i<priceR.length();i++){
            if(price.length()>1 && (priceR.length()-i)%3 == 0){
                price = price + ".";
            }
            price = price + priceR.charAt(i);
        }

        holder.name.setText(name);
        //holder.description.setText(description);
        holder.price.setText(price);
        Glide.with(myself).load(urlImage).into(holder.image);
        holder.add_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(myself, "nombre: "+ name, Toast.LENGTH_SHORT).show();
            }
        });
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(myself, "precio: "+ name, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount(){
        return productModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView identi;
        private TextView name;
        private TextView description;
        private ImageView image;
        private TextView price;
        private ImageButton add_car;
        private ImageButton favorite;

        public ViewHolder(View v){
            super(v);
            identi = v.findViewById(R.id.row_id);
            name = v.findViewById(R.id.row_nombre);
           // description = v.findViewById(R.id.row_);
            image = v.findViewById(R.id.row_imagen);
            price = v.findViewById(R.id.row_precio);
            add_car = v.findViewById(R.id.ib_add_car);
            favorite = v.findViewById(R.id.ib_favorite);

        }
    }
}