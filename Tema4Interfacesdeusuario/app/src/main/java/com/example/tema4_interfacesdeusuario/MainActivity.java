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
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Button;

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
        Animation miAnimacion = AnimationUtils.loadAnimation(this, R.anim.animacion);
        // Configurar la animacion
        miAnimacion.setRepeatMode(Animation.RESTART);
        miAnimacion.setRepeatCount(20);
        // Aplicar la animacion al texto
        miTexto.startAnimation(miAnimacion);
        // Elementos que se sugeriran
        String[] opciones = {"Opcion1", "Opcion2", "Opcion3", "Opcion4", "Opcion5"};

        // Buscar el AutoCompleteTextView
        AutoCompleteTextView textoLeido = findViewById(R.id.miTexto);
        // Crear el ArrayAdapter
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, opciones);
        // Asignar el adaptador
        textoLeido.setAdapter(adaptador);
        // Buscar el Spinner
        Spinner miSpinner = findViewById(R.id.miSpinner);
        // Peliculas que apareceran en el Spinner
        String[] valores = {"Star Wars", "Reservoir dogs", "El Club de la lucha", "Snatch: Cerdos y Diamantes"};
        // Crear el adaptador del Spinner
        ArrayAdapter<String> adaptadorSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valores);
        adaptadorSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Asignar el adaptador al Spinner
        miSpinner.setAdapter(adaptadorSpinner);
        // Detectar la pelicula seleccionada
        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                System.out.println("Has seleccionado el valor: " + adapterView.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                System.out.println("No has seleccionado nada");
            }
        });
        // Buscar el CheckBox
        CheckBox checkbox1 = findViewById(R.id.checkbox1);
        // Listener para cambios en el CheckBox
        checkbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Codigo para seleccionado
                    Toast.makeText(MainActivity.this, "Has seleccionado la opcion", Toast.LENGTH_SHORT).show();
                } else {
                    // Codigo para no seleccionado
                    Toast.makeText(MainActivity.this, "Has desmarcado la opcion", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Buscar el RadioGroup, el boton y el TextView
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        Button buttonCheck = findViewById(R.id.button_check);
        TextView textViewResult = findViewById(R.id.textViewResult);
        // Comprobar el RadioButton seleccionado
        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el ID del RadioButton seleccionado
                int selectedId = radioGroup.getCheckedRadioButtonId();
                // Verificar si algun RadioButton esta seleccionado
                if (selectedId != -1) {
                    RadioButton selectedRadioButton = findViewById(selectedId);
                    String selectedOption = selectedRadioButton.getText().toString();
                    textViewResult.setText("Seleccionado: " + selectedOption);
                } else {
                    textViewResult.setText("No se ha seleccionado ninguna opcion");
                }
            }
        });

        // Buscar el SeekBar y el TextView
        SeekBar seekBar = findViewById(R.id.seekBar);
        TextView textViewProgress = findViewById(R.id.textViewProgress);

        // Listener para los cambios en el SeekBar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewProgress.setText("Valor: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
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