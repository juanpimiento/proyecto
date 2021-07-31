package com.juanpimiento.proyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainRegisterU extends AppCompatActivity {

    public CheckBox chk_term;
    public Button btn_register;
    public Activity mySefl;
    public TextView tv_term;
    private EditText et_name;
    private EditText et_lastname;
    private EditText et_email;
    private EditText et_password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register_u);

        mySefl = this;
        et_name = findViewById(R.id.et_name);
        et_lastname = findViewById(R.id.et_lastname);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        mAuth = FirebaseAuth.getInstance();

        tv_term = (TextView)findViewById(R.id.tv_term);
        SpannableString content = new SpannableString(tv_term.getText());
        content.setSpan(new UnderlineSpan(), 0, tv_term.length(), 0);
        tv_term.setText(content);
        tv_term.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent terminos = new Intent(mySefl,MainTerminos.class);
                startActivity(terminos);
            }
        });



        chk_term = findViewById(R.id.chk_term);

        chk_term.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

//                Intent terminos = new Intent(mySefl,MainTerminos.class);
//                startActivity(terminos);
                btn_register.setEnabled(isChecked);

            }
        });

        btn_register = findViewById(R.id.btn_register);
        btn_register.setEnabled(false);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(mySefl, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d( "bien ", "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("mal", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(mySefl, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });

    }
}