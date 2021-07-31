package com.juanpimiento.proyecto.ui.newproduct;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juanpimiento.proyecto.R;
import com.juanpimiento.proyecto.databinding.FragmentGalleryBinding;
import com.juanpimiento.proyecto.databinding.FragmentRegisterProductBinding;
import com.juanpimiento.proyecto.ui.gallery.GalleryViewModel;

public class RegisterProductFragment extends Fragment {

    private RegisterProductViewModel RegisterProductViewModel;
    private FragmentRegisterProductBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RegisterProductViewModel =
                new ViewModelProvider(this).get(RegisterProductViewModel.class);

        binding = FragmentRegisterProductBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}