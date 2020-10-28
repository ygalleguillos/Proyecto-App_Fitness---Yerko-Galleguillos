package com.example.app_fitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Clases.AdminSQLiteOpenHelper;

public class Insumos_act extends AppCompatActivity {

    private EditText edcodigo, ednombre, edprecio, edstock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insumos_act);

        edcodigo = (EditText) findViewById(R.id.edit_codigo);
        ednombre = (EditText) findViewById(R.id.edit_nombre);
        edprecio = (EditText) findViewById(R.id.edit_precio);
        edstock = (EditText) findViewById(R.id.edit_stock);
    }


    public void AnadirInsumos(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "fichero", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        if(!edcodigo.getText().toString().isEmpty())
        {
            ContentValues registro = new ContentValues();

            registro.put("codigo",edcodigo.getText().toString());
            registro.put("nombre",ednombre.getText().toString());
            registro.put("precio",edprecio.getText().toString());
            registro.put("stock",edstock.getText().toString());

            db.insert("insumos",null,registro);
            db.close();

            Toast.makeText(this,"Se ha guardado un insumo!", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"Debe Ingresar un insumo.", Toast.LENGTH_LONG).show();
        }

    }


    public void MostrarInsumos(View v)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"fichero", null,1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String codigo = edcodigo.getText().toString();

        if (!codigo.isEmpty())
        {
            Cursor fila = db.rawQuery("SELECT nombre, precio, stock FROM insumos WHERE codigo ="+codigo,null);

            if (fila.moveToFirst())//devuelve un resultado en caso de no encontrar ningun campo devuelve vacio.
            {
               ednombre.setText(fila.getString(0));
               edprecio.setText(fila.getString(1));
               edstock.setText(fila.getString(2));
            }
            else
            {
                Toast.makeText(this,"Debes ingresa el codigo del insumo.", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this,"Debes ingresa el codigo del insumo.", Toast.LENGTH_SHORT).show();
        }
    }

    public void EliminarInsumos(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"fichero",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String codigo = edcodigo.getText().toString();

        db.delete("insumos","codigo"+codigo, null);
        db.close();

        Toast.makeText(this,"Has eliminado el insumo", Toast.LENGTH_LONG).show();

    }

    public void ActualizarInsumos(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"fichero",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String codigo = edcodigo.getText().toString();

        ContentValues cont = new ContentValues();

        cont.put("codigo", edcodigo.getText().toString());
        cont.put("nombre", ednombre.getText().toString());
        cont.put("precio", edprecio.getText().toString());
        cont.put("stock", edstock.getText().toString());

        if (!codigo.isEmpty())
        {
            db.update("insumos",cont,"codigo"+codigo, null);

            Toast.makeText(this,"Se ha actualizado el campo.", Toast.LENGTH_LONG).show();

            edcodigo.setText("");
            ednombre.setText("");
            edprecio.setText("");
            edstock.setText("");
        }
    }

}