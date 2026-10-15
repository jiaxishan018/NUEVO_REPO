
package com.example.tema4_interfacesdeusuario;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TextView miTexto = findViewById(R.id.texto);
        // Cargar la animacion
        Animation miAnimacion =
                AnimationUtils.loadAnimation(this, R.anim.animacion);
        // Configurar la animacion
        miAnimacion.setRepeatMode(Animation.RESTART);
        miAnimacion.setRepeatCount(20);
        // Aplicar la animacion al texto
        miTexto.startAnimation(miAnimacion);
        // Elementos que se sugeriran
        String[] opciones = {
                "Opcion1", "Opcion2", "Opcion3", "Opcion4", "Opcion5"
        };
        // Buscar el AutoCompleteTextView
        AutoCompleteTextView textoLeido = findViewById(R.id.miTexto);
        // Crear el ArrayAdapter
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                opciones
        );
        // Asignar el adaptador
        textoLeido.setAdapter(adaptador);
        // Buscar el Spinner
        Spinner miSpinner = findViewById(R.id.miSpinner);
        // Peliculas que apareceran en el Spinner
        String[] valores = {
                "Star Wars",
                "Reservoir dogs",
                "El Club de la lucha",
                "Snatch: Cerdos y Diamantes"
        };

        // Crear el adaptador del Spinner
        ArrayAdapter<String> adaptadorSpinner = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                valores
        );
        adaptadorSpinner.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        // Asignar el adaptador al Spinner
        miSpinner.setAdapter(adaptadorSpinner);
        // Detectar la pelicula seleccionada
        miSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(
                            AdapterView<?> adapterView,
                            View view,
                            int position,
                            long id) {
                        System.out.println("Has seleccionado el valor: "
                                + adapterView.getItemAtPosition(position));
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        System.out.println("No has seleccionado nada");
                    }
                });
    }
    @Override
    protected void onStart() {
        super.onStart();
        TextView miTexto = findViewById(R.id.texto);
        miTexto.setText("Nuevo texto a mostrar");
        // Cambiar el color del texto
        miTexto.setTextColor(Color.RED);
        // Cambiar el texto a cursiva
        miTexto.setTypeface(null, Typeface.ITALIC);
        // Cambiar el tamaño del texto
        miTexto.setTextSize(24);
        // Cambiar el tipo de letra
        miTexto.setTypeface(Typeface.SANS_SERIF);
    }
}