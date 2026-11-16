package com.example.tema5_listadosymens;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdaptadorPais extends ArrayAdapter<String> {

    String[] paises;
    String[] capitales;

    public AdaptadorPais(Context context, String[] paises, String[] capitales) {
        super(context, R.layout.item_pais, paises);
        this.paises = paises;
        this.capitales = capitales;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View vista = convertView;

        if (vista == null) {
            vista = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_pais, parent, false);
        }

        TextView txtPais = vista.findViewById(R.id.txtPais);
        TextView txtCapital = vista.findViewById(R.id.txtCapital);

        txtPais.setText(paises[position]);
        txtCapital.setText("Capital: " + capitales[position]);

        return vista;
    }
}