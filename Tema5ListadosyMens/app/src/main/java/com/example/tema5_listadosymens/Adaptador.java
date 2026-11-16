package com.example.tema5_listadosymens;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Adaptador extends ArrayAdapter<Datos> {

    private Datos[] datos;

    public Adaptador(Context context, Datos[] datos) {
        super(context, R.layout.elemento, datos);
        this.datos = datos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Obtener el LayoutInflater
        LayoutInflater mostrador = LayoutInflater.from(getContext());

        // Inflar el layout personalizado
        View elemento = mostrador.inflate(R.layout.elemento, parent, false);

        // Obtener referencias a los TextView
        TextView text1 = elemento.findViewById(R.id.text1);
        TextView text2 = elemento.findViewById(R.id.text2);

        // Poner los datos
        text1.setText(datos[position].getTexto1());
        text2.setText(datos[position].getTexto2());

        return elemento;
    }
}