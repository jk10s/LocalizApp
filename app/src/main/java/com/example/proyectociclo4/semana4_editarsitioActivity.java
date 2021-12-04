package com.example.proyectociclo4;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectociclo4.ado.SitioADO;
import com.example.proyectociclo4.clases.Mensajes;
import com.example.proyectociclo4.modelos.Sitio;
import com.google.firebase.database.FirebaseDatabase;

public class semana4_editarsitioActivity extends AppCompatActivity {

    private Sitio registro = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semana4_editarsitio);

        EditText txtNombre = (EditText) findViewById(R.id.semana4_sitio_editar_txtNombre);
        EditText txtDescripcion = (EditText) findViewById(R.id.semana4_sitio_editar_txtDescripcion);
        EditText txtLatitud = (EditText) findViewById(R.id.semana4_sitio_editar_txtLatitud);
        EditText txtLongitud = (EditText) findViewById(R.id.semana4_sitio_editar_txtLongitud);
        Spinner spnTipo = (Spinner) findViewById(R.id.semana4_sitio_editar_spnTipo);
        Button btnVolver = (Button) findViewById(R.id.semana4_sitio_editar_btnVolver);
        Button btnGuardar = (Button) findViewById(R.id.semana4_sitio_editar_btnGuardar);

        Bundle parametros = getIntent().getExtras();
        //if(parametros!=null)
        if(parametros.containsKey("id"))
        {
            SitioADO dbSitio = new SitioADO(this);
            int id = parametros.getInt("id");
            registro = dbSitio.obtener(id);

            txtNombre.setText(registro.getNombre());
            txtDescripcion.setText(registro.getDescripcion());
            txtLatitud.setText(String.valueOf(registro.getLatitud()));
            txtLongitud.setText(String.valueOf(registro.getLongitud()));

            int posicion = ((ArrayAdapter<String>) spnTipo.getAdapter()).getPosition(registro.getTipo());
            spnTipo.setSelection(posicion);

        }

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registro.setNombre(txtNombre.getText().toString());
                registro.setDescripcion(txtDescripcion.getText().toString());
                registro.setTipo((String) spnTipo.getSelectedItem());
                registro.setLatitud(Double.parseDouble(txtLatitud.getText().toString()));
                registro.setLongitud(Double.parseDouble(txtLongitud.getText().toString()));

                SitioADO dbSitio = new SitioADO(view.getContext());
                if(dbSitio.editar(registro))
                    new Mensajes(view.getContext()).alert("Registro editado", "Se ha editado el registro satisfactoriamente.");
                else
                    new Mensajes(view.getContext()).alert("Error", "Se ha producido un error al intentar editar el registro.");

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                database.getReference().child("Sitio").child(String.valueOf(registro.getId())).setValue(registro);

                onBackPressed();

            }
        });

    }
}