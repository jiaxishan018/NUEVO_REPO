package com.example.tema4_interfacesdeusuario;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TextView miTexto = findViewById(R.id.texto);
        // Cargar la animación
        Animation miAnimacion = AnimationUtils.loadAnimation(this, R.anim.animacion);
        // Configurar la animación
        miAnimacion.setRepeatMode(Animation.RESTART);
        miAnimacion.setRepeatCount(20);
        // Aplicar la animación al texto
        miTexto.startAnimation(miAnimacion);
    }

    protected void onStart(){
        super.onStart();
        TextView miTexto = (TextView) findViewById(R.id.texto);
        miTexto.setText("Nuevo texto a mostrar");
        // Opcion 1 para cambiar el color.
        miTexto.setTextColor(Color.parseColor("#0000FF"));
        // Opcion 2 para cambiar el color
        miTexto.setTextColor(Color.RED);
        // Cambiar el texto a cursiva
        miTexto.setTypeface(null, Typeface.ITALIC);
        // Cambiar el tamaño del texto
        miTexto.setTextSize(24);
        // Cambiar el tipo de letra
        miTexto.setTypeface(Typeface.SANS_SERIF);
    }
}