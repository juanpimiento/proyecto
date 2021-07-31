package com.juanpimiento.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainTerminos extends AppCompatActivity {

    public Button btn_acceptterm;
    public Activity mySefl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_terminos);

        mySefl =this;
        btn_acceptterm = findViewById(R.id.btn_acceptterm);
        btn_acceptterm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(mySefl,MainRegisterU.class);
                startActivity(register);
            }
        });
    }
}