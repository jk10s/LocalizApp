package com.example.proyectociclo4.clases;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class Mensajes {

    private Context contexto;
    private AlertDialog.Builder msj;

    public Mensajes(Context contexto) {
        this.contexto = contexto;
        this.msj = new AlertDialog.Builder(this.contexto);
    }

    public void alert(String titulo, String cuerpo)
    {
        msj.setTitle(titulo);
        msj.setMessage(cuerpo);
        msj.create();
        msj.show();
    }

    public void confirm(String titulo, String cuerpo, DialogInterface.OnClickListener eventoSi, DialogInterface.OnClickListener eventoNo)
    {
        msj.setTitle(titulo);
        msj.setMessage(cuerpo);
        msj.setNegativeButton("No", eventoNo);
        msj.setPositiveButton("Si", eventoSi);
        msj.create();
        msj.show();
    }

}
