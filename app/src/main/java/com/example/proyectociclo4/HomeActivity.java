package com.example.proyectociclo4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    Button btnIniciaruno;
    Button btnEditarSitios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnIniciaruno=findViewById(R.id.btnIniciaruno);
        btnEditarSitios=findViewById(R.id.btnEditarSitios);


        btnIniciaruno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,InsertarSitioActivity.class));
            }
        });
        btnEditarSitios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, semana4_versitiosActivity.class));
            }
        }
        );

    }
}