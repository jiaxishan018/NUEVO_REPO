package com.example.tema5_listadosymens;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView grid = findViewById(R.id.gridView);
        TextView texto = findViewById(R.id.textView);

        // Crear los datos
        Datos[] datos = new Datos[]{
                new Datos("España", "Madrid"),
                new Datos("Francia", "París"),
                new Datos("Italia", "Roma"),
                new Datos("Alemania", "Berlín"),
                new Datos("Portugal", "Lisboa"),
                new Datos("Bélgica", "Bruselas"),
                new Datos("Grecia", "Atenas"),
                new Datos("Suecia", "Estocolmo"),
                new Datos("Noruega", "Oslo"),
                new Datos("Dinamarca", "Copenhague")
        };

        // Crear el adaptador personalizado
        Adaptador miAdaptador = new Adaptador(this, datos);

        // Conectar el adaptador con el GridView
        grid.setAdapter(miAdaptador);

        // Detectar cuando pulsamos un elemento
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                texto.setText(datos[position].getTexto1());
            }
        });
    }
}