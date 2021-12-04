package com.example.proyectociclo4.adapters;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectociclo4.R;
import com.example.proyectociclo4.ado.SitioADO;
import com.example.proyectociclo4.clases.Mensajes;
import com.example.proyectociclo4.modelos.Sitio;
import com.example.proyectociclo4.semana4_editarsitioActivity;
import com.example.proyectociclo4.semana4_versitiosActivity;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Sitios_RecyclerView extends RecyclerView.Adapter<Sitios_RecyclerView.ViewModelRegistro> {

    ArrayList<Sitio> datos;

    public Sitios_RecyclerView(ArrayList<Sitio> datos) {
        this.datos = datos;
    }

    @NonNull
    @Override
    public ViewModelRegistro onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, null, false);
        return new ViewModelRegistro(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewModelRegistro holder, int position) {
        holder.asignarRegistros(datos.get(position));
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class ViewModelRegistro extends RecyclerView.ViewHolder {

        int id;
        TextView txtNombre;
        TextView txtDescripcion;

        public ViewModelRegistro(@NonNull View itemView) {
            super(itemView);

            txtNombre = (TextView) itemView.findViewById(R.id.semana4_recycleritem_nombre);
            txtDescripcion = (TextView) itemView.findViewById(R.id.semana4_recycleritem_descripcion);
            ImageButton btnEditar = (ImageButton) itemView.findViewById(R.id.semana4_recycleritem_btnEditar);
            ImageButton btnEliminar = (ImageButton) itemView.findViewById(R.id.semana4_recycleritem_btnEliminar);

            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new Mensajes(view.getContext()).confirm("Confirmación", "¿Realmente desea eliminar el registro?", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    SitioADO dbsitio = new SitioADO(view.getContext());
                                    if(dbsitio.eliminar(id))
                                        new Mensajes(view.getContext()).alert("Registro eliminado", "Se ha eliminado el registro correctamente.");
                                    else
                                        new Mensajes(view.getContext()).alert("Error", "Se ha producido un error al intentar eliminar el registro.");

                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    database.getReference().child("Sitio").child(String.valueOf(id)).removeValue();


                                    ((semana4_versitiosActivity) view.getContext()).recreate();
                                }
                            },
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    new Mensajes(view.getContext()).alert("Proceso cancelado", "Se ha cancelado el proceso.");
                                }
                            });
                }
            });

            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in = new Intent(view.getContext(), semana4_editarsitioActivity.class);
                    in.putExtra("id", id);
                    view.getContext().startActivity(in);
                }
            });

        }

        public void asignarRegistros(Sitio registro)
        {
            txtNombre.setText(registro.getNombre());
            txtDescripcion.setText(registro.getDescripcion());
            id = registro.getId();
        }
    }
}
