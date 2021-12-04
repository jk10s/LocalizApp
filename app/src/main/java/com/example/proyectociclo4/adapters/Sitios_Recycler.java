package com.example.proyectociclo4.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectociclo4.R;
import com.example.proyectociclo4.modelos.Sitio;

import java.util.ArrayList;

public class Sitios_Recycler extends RecyclerView.Adapter <Sitios_Recycler.ViewModeRegistro> {

    ArrayList<Sitio> datos;

    public Sitios_Recycler(ArrayList<Sitio> datos) {
        this.datos = datos;
    }

    @NonNull
    @Override
    public ViewModeRegistro onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, null, false);
        return new ViewModeRegistro(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewModeRegistro holder, int position) {
        holder.asignarRegistros(datos.get(position));
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class ViewModeRegistro extends RecyclerView.ViewHolder {

        int id;
        TextView txtNombre;
        TextView txtDescripcion;


        public ViewModeRegistro(@NonNull View itemView) {
            super(itemView);

            //txtNombre = (TextView) itemView.findViewById(R.id.textnombre);
            //txtDescripcion = (TextView) itemView.findViewById(R.id.textdescripcion);
            //ImageButton btnEditar = (ImageButton) itemView.findViewById(R.id.);
            //ImageButton btnEliminar = (ImageButton) itemView.findViewById(R.id.semana4_recycleritem_btnEliminar);

        }

        public void asignarRegistros(Sitio registro) {
            txtNombre.setText(registro.getNombre());
            txtDescripcion.setText(registro.getDescripcion());
            id = registro.getId();


        }
    }
}
