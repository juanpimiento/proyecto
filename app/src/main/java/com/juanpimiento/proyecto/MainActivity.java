package com.juanpimiento.proyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.juanpimiento.proyecto.ui.database.UserDatabase;
import com.juanpimiento.proyecto.ui.database.model.User;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button btn_register;
    private Button btn_login;
    private ImageButton ib_register;
    private Activity myself;
    private UserDatabase userDatabase;
    private SharedPreferences sharedpreference;
    private FirebaseAuth autenticacion;
    private EditText et_email;
    private EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_user);

        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        ib_register = findViewById(R.id.ib_register);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        myself = this;


        autenticacion = FirebaseAuth.getInstance();

        sharedpreference = getSharedPreferences("MY_PREFERENCES", MODE_PRIVATE);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//        //LEER DATOS
//        db.collection("users")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d("FIREBASE_BD", document.getId() + " => " + document.getData());
//                            }
//                        } else {
//                            Log.w("FIREBASE_BD", "Error getting documents.", task.getException());
//                        }
//                    }
//                });
//
//        //AGREGAR DATOS
//        // Create a new user with a first and last name
//        Map<String, Object> user = new HashMap<>();
//        user.put("first", "Ada");
//        user.put("last", "Lovelace");
//        user.put("born", 1815);
//
//        // Add a new document with a generated ID
//        db.collection("users")
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d("FIREBASE_BD", "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w("FIREBASE_BD", "Error adding document", e);
//                    }
//                });



        String userName = sharedpreference.getString("username",null);
        String pass = sharedpreference.getString("password",null);

        userDatabase = UserDatabase.getInstance(this);
        User user = new User("Sergio","Moreno");
        long response = userDatabase.getUserDao().insertUser(user);



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = et_email.getText().toString();
                String password = et_password.getText().toString();

                autenticacion.signInWithEmailAndPassword(email, password)

                        .addOnCompleteListener(myself, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Intent menup = new Intent(myself, MainMenu.class);
                                    startActivity(menup);
                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(myself, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });








        ib_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regis = new Intent(myself,MainRegisterU.class);
                startActivity(regis);
            }
        });

    }
}