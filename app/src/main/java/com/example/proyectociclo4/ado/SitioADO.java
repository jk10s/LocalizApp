package com.example.proyectociclo4.ado;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.proyectociclo4.clases.SqliteConex;
import com.example.proyectociclo4.modelos.Sitio;

import java.util.ArrayList;

public class SitioADO extends SqliteConex {

    Context contexto;

    public SitioADO(@Nullable Context c)
    {
        super(c);
        this.contexto = c;
    }

    public long insertar(Sitio sitio)
    {
        long id = 0;

        try
        {
            SqliteConex conexion = new SqliteConex(this.contexto);
            SQLiteDatabase db = conexion.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put("nombre", sitio.getNombre());
            valores.put("descripcion", sitio.getDescripcion());
            valores.put("tipo", sitio.getTipo());
            valores.put("latitud", sitio.getLatitud());
            valores.put("longitud", sitio.getLongitud());

            id = db.insert("sitios", null, valores);

        }
        catch (Exception ex)
        {

        }

        return id;
    }

    public ArrayList<Sitio> listar()
    {
        ArrayList<Sitio> registros = new ArrayList<>();

        try
        {
            SqliteConex conexion = new SqliteConex(this.contexto);
            SQLiteDatabase db = conexion.getWritableDatabase();

            Cursor cregistros = db.rawQuery("SELECT id, nombre, descripcion, tipo, latitud, longitud FROM sitios", null);
            if(cregistros.moveToFirst())
            {
                do {
                    Sitio registo = new Sitio();
                    registo.setId(cregistros.getInt(0));
                    registo.setNombre(cregistros.getString(1));
                    registo.setDescripcion(cregistros.getString(2));
                    registo.setTipo(cregistros.getString(3));
                    registo.setLatitud(cregistros.getDouble(4));
                    registo.setLongitud(cregistros.getDouble(5));

                    registros.add(registo);
                }
                while (cregistros.moveToNext());
            }


        }
        catch (Exception ex)
        {

        }


        return registros;
    }

    public boolean editar(Sitio registro)
    {
        boolean editado = false;

        SqliteConex conexion = new SqliteConex(this.contexto);
        SQLiteDatabase db = conexion.getWritableDatabase();

        try
        {
            db.execSQL("UPDATE sitios" +
                    "   SET nombre = '" + registro.getNombre() + "'," +
                    "       descripcion = '" + registro.getDescripcion() + "'," +
                    "       tipo = '" + registro.getTipo() + "'," +
                    "       latitud = '" + String.valueOf(registro.getLatitud()) + "'," +
                    "       longitud = '" + String.valueOf(registro.getLongitud()) + "'" +
                    " WHERE id = '" + registro.getId() + "'");
            editado=true;
        }
        catch (Exception ex)
        {

        }
        finally {
            db.close();
        }
        return editado;
    }

    public boolean eliminar(int id)
    {
        boolean eliminado = false;

        SqliteConex conexion = new SqliteConex(this.contexto);
        SQLiteDatabase db = conexion.getWritableDatabase();

        try
        {
            db.execSQL("DELETE FROM sitios" +
                    "      WHERE id = '" + String.valueOf(id) + "'");
            eliminado=true;
        }
        catch (Exception ex)
        {

        }
        finally {
            db.close();
        }


        return eliminado;
    }

    public Sitio obtener(int id)
    {
        Sitio registro = null;

        try
        {
            SqliteConex conexion = new SqliteConex(this.contexto);
            SQLiteDatabase db = conexion.getWritableDatabase();

            Cursor cregistro = db.rawQuery("SELECT id, nombre, descripcion, tipo, latitud, longitud FROM sitios WHERE id = " + String.valueOf(id), null);
            if(cregistro.moveToFirst())
            {
                registro = new Sitio();
                registro.setId(cregistro.getInt(0));
                registro.setNombre(cregistro.getString(1));
                registro.setDescripcion(cregistro.getString(2));
                registro.setTipo(cregistro.getString(3));
                registro.setLatitud(cregistro.getDouble(4));
                registro.setLongitud(cregistro.getDouble(5));
            }

        }
        catch (Exception ex)
        {

        }

        return registro;
    }
}
