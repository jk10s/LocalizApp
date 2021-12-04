package com.example.proyectociclo4;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectociclo4.adapters.Sitios_RecyclerView;
import com.example.proyectociclo4.modelos.Sitio;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class semana4_versitiosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_semana4_versitios);

        RecyclerView rcvSitios = (RecyclerView) findViewById(R.id.semana4_versitios_recyclerview);
        rcvSitios.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Sitio> sitios = new ArrayList<>();

        //Conexion con Firebase.
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        try {
            database.setPersistenceEnabled(true);

        }
        catch (Exception ex)
        {}
        database.getReference().child("Sitio").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                sitios.clear();
                for(DataSnapshot sitioActual : dataSnapshot.getChildren()) {
                    Sitio s = sitioActual.getValue(Sitio.class);
                    sitios.add(s);
                }

                Sitios_RecyclerView adaptador = new Sitios_RecyclerView(sitios);
                rcvSitios.setAdapter(adaptador);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
       

//        refrescarLista();

        FloatingActionButton btnAgregar = (FloatingActionButton) findViewById(R.id.semana4_versitios_btnAgregar);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), InsertarSitioActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        refrescarLista();
    }

    private void refrescarLista()
    {
/*        RecyclerView rcvSitios = (RecyclerView) findViewById(R.id.semana4_versitios_recyclerview);
        rcvSitios.setLayoutManager(new LinearLayoutManager(this));

        SitioADO db = new SitioADO(this);
        ArrayList<Sitio> sitios = db.listar();

        Sitios_RecyclerView adaptador = new Sitios_RecyclerView(sitios);
        rcvSitios.setAdapter(adaptador);
*/
    }
}