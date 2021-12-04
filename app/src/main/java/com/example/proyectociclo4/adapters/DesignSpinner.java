package com.example.proyectociclo4.adapters;

import android.database.DataSetObserver;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.proyectociclo4.R;

import java.util.ArrayList;

public class DesignSpinner implements SpinnerAdapter {

    private final ArrayList<String> datos;

    public DesignSpinner(ArrayList<String> datos) {
        this.datos = datos;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
/*        TextView vista = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.support_simple_spinner_dropdown_item, parent);
        vista.setText(datos.get(position));
        vista.setTextSize(20);
        vista.setTextColor(Color.WHITE);*/
        TextView vista = (TextView) convertView;
        return vista;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int position) {
        return datos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
/*        TextView vista = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.support_simple_spinner_dropdown_item, parent);
        vista.setText(datos.get(position));
        vista.setTextSize(20);
        vista.setTextColor(Color.WHITE);*/
        TextView vista = (TextView) convertView;
        return vista;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
