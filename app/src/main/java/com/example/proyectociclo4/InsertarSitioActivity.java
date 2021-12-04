package com.example.proyectociclo4;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.proyectociclo4.adapters.DesignSpinner;
import com.example.proyectociclo4.modelos.Sitio;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectociclo4.ado.SitioADO;
import com.example.proyectociclo4.clases.Mensajes;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class InsertarSitioActivity extends AppCompatActivity {

    ImageView btnimageView;
    double latitud;
    double longitud;
    EditText txtLatitud;
    EditText txtLongitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_insertar_sitio);

         txtLatitud = (EditText) findViewById(R.id.sitio_insertar_txtLatitud);
         txtLongitud = (EditText) findViewById(R.id.sitio_insertar_txtLongitud);
        EditText txtNombre = (EditText) findViewById(R.id.sitio_insertar_txtNombre);
        EditText txtDescripcion = (EditText) findViewById(R.id.sitio_insertar_txtDescripcion);

        Spinner spnTipo = (Spinner) findViewById(R.id.sitio_insertar_spnTipo);
        Button btnVolver = (Button) findViewById(R.id.sitio_insertar_btnVolver);
        Button btnGuardar = (Button) findViewById(R.id.sitio_insertar_btnGuardar);
        ImageView btnimageView = (ImageView) findViewById(R.id.btnimageView6);


/*        ArrayList<String> datos = new ArrayList<>();
        datos.add("Item 1");
        datos.add("Item 2");
        datos.add("Item 3");
        DesignSpinner ds = new DesignSpinner(datos);
        spnTipo.setAdapter(ds);*/
        spnTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextSize(20);
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnimageView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivityForResult(new Intent((Activity)v.getContext(), MapsActivity.class), 5000);
           }
            }
        );

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double lat = 0;
                double lon = 0;
                String nombre = txtNombre.getText().toString();
                String descripcion = txtDescripcion.getText().toString();
                String tipo = (String) spnTipo.getSelectedItem();

                try {
                    lat = Double.parseDouble(txtLatitud.getText().toString());
                    lon = Double.parseDouble(txtLongitud.getText().toString());
                }
                catch (Exception ex)
                {}

                if (validarCamposVacios(nombre, descripcion, lat, lon)) {
                    Sitio registro = new Sitio();
                    registro.setNombre(nombre);
                    registro.setDescripcion(descripcion);
                    registro.setLatitud(lat);
                    registro.setLongitud(lon);
                    registro.setTipo(tipo);

                    SitioADO db = new SitioADO(view.getContext());
                    long id = db.insertar(registro);
                    registro.setId((int) id);

                    // Write a message to the database
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    database.getReference().child("Sitio").child(String.valueOf(id)).setValue(registro);




                    if (id > 0)
                        new Mensajes(view.getContext()).alert("Registro guardado", "Se ha guardado el registro " + String.valueOf(id) + " satisfactoriamente!!.");
                    else
                        new Mensajes(view.getContext()).alert("Error", "Ha ocurrido un error al intentar guardar el registro.");
                    db.listar();
                }
                else {
                    new Mensajes(view.getContext()).alert("Advertencia", "Se han encontrado campos vacios, por favor digitelos.");
                }
            }
        });



    }

    public void asignarCoordenada (double lat,   double lon){
        this.latitud = lat;
        this.longitud = lon;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==5000){
            Bundle parametros = data.getExtras();
            asignarCoordenada(parametros.getDouble("latitud"), parametros.getDouble("longitud"));
            txtLatitud.setText(String.valueOf(latitud));
            txtLongitud.setText(String.valueOf(longitud));
    }

    }

    public static boolean validarCamposVacios(String nombre, String descripcion, double latitud, double longitud)
    {
        boolean camposValidados = false;

        //Forma larga
/*        if(!nombre.isEmpty())
            if(!descripcion.isEmpty())
                if(latitud>0)
                    if(longitud>0)
                        camposValidados=true;*/

        //Forma corta
        if(!nombre.isEmpty() && !descripcion.isEmpty() && latitud>0 && longitud>0)
            camposValidados=true;

        return camposValidados;
    }
}