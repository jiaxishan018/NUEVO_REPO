package com.example.prctica1_realizarunacalculadorabasica;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tvDisplay;
    String texto = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);

        // Botones de los numeros
        int[] numeros = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
                R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7,
                R.id.btn8, R.id.btn9
        };

        for (int i = 0; i < numeros.length; i++) {
            Button boton = findViewById(numeros[i]);

            boton.setOnClickListener(v -> {
                String valor = ((Button) v).getText().toString();

                if (texto.equals("0")) {
                    texto = valor;
                } else {
                    texto = texto + valor;
                }

                tvDisplay.setText(texto);
            });
        }

        // Botones de las operaciones
        int[] operaciones = {
                R.id.btnSumar, R.id.btnRestar,
                R.id.btnMultiplicar, R.id.btnDividir
        };

        for (int i = 0; i < operaciones.length; i++) {
            Button boton = findViewById(operaciones[i]);

            boton.setOnClickListener(v -> {
                char ultimo = texto.charAt(texto.length() - 1);

                if (ultimo != '+' && ultimo != '-'
                        && ultimo != '×' && ultimo != '÷') {
                    String valor = ((Button) v).getText().toString();
                    texto = texto + valor;
                    tvDisplay.setText(texto);
                }
            });
        }

        // Boton de la coma
        Button btnComa = findViewById(R.id.btnComa);

        btnComa.setOnClickListener(v -> {
            int i = texto.length() - 1;
            boolean tieneComa = false;

            while (i >= 0 && texto.charAt(i) != '+'
                    && texto.charAt(i) != '-'
                    && texto.charAt(i) != '×'
                    && texto.charAt(i) != '÷') {

                if (texto.charAt(i) == ',') {
                    tieneComa = true;
                }

                i--;
            }

            if (!tieneComa) {
                if (texto.equals("0")) {
                    texto = "0,";
                } else if (texto.charAt(texto.length() - 1) == '+'
                        || texto.charAt(texto.length() - 1) == '-'
                        || texto.charAt(texto.length() - 1) == '×'
                        || texto.charAt(texto.length() - 1) == '÷') {
                    texto = texto + "0,";
                } else {
                    texto = texto + ",";
                }

                tvDisplay.setText(texto);
            }
        });

        // Boton C: borrar el ultimo caracter
        Button btnC = findViewById(R.id.btnC);

        btnC.setOnClickListener(v -> {
            if (texto.length() > 1) {
                texto = texto.substring(0, texto.length() - 1);
            } else {
                texto = "0";
            }

            tvDisplay.setText(texto);
        });

        // Boton AC: borrar todo
        Button btnAC = findViewById(R.id.btnAC);

        btnAC.setOnClickListener(v -> {
            texto = "0";
            tvDisplay.setText(texto);
        });

        // Boton igual
        Button btnIgual = findViewById(R.id.btnIgual);

        btnIgual.setOnClickListener(v -> {
            tvDisplay.setText(texto);
        });
    }
}
