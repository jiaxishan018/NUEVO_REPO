package com.example.prctica1_realizarunacalculadorabasica;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tvDisplay;
    String texto = "0";
    boolean nuevoNumero = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);

        // Botones numericos
        int[] numeros = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
                R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7,
                R.id.btn8, R.id.btn9
        };

        for (int i = 0; i < numeros.length; i++) {
            Button boton = findViewById(numeros[i]);

            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String numero = ((Button) v).getText().toString();
                    escribirNumero(numero);
                }
            });
        }

        // Boton de la coma decimal
        Button btnComa = findViewById(R.id.btnComa);
        btnComa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escribirComa();
            }
        });

        // Botones de operaciones
        int[] operaciones = {
                R.id.btnSumar, R.id.btnRestar,
                R.id.btnMultiplicar, R.id.btnDividir
        };

        for (int i = 0; i < operaciones.length; i++) {
            Button boton = findViewById(operaciones[i]);

            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String operacion = ((Button) v).getText().toString();
                    escribirOperacion(operacion);
                }
            });
        }

        // Boton C: borrar el ultimo caracter
        Button btnC = findViewById(R.id.btnC);
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrarUltimo();
            }
        });

        // Boton AC: borrar todo
        Button btnAC = findViewById(R.id.btnAC);
        btnAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texto = "0";
                nuevoNumero = false;
                tvDisplay.setText(texto);
            }
        });

        // Boton igual
        Button btnIgual = findViewById(R.id.btnIgual);
        btnIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularResultado();
            }
        });
    }

    // Escribir los numeros sin ceros iniciales
    public void escribirNumero(String numero) {
        if (nuevoNumero) {
            texto = numero;
            nuevoNumero = false;
        } else if (texto.equals("0")) {
            texto = numero;
        } else if (terminaEnOperador()) {
            texto = texto + numero;
        } else {
            texto = texto + numero;
        }

        tvDisplay.setText(texto);
    }

    // Comprobar si el ultimo caracter es una operacion
    public boolean terminaEnOperador() {
        if (texto.length() == 0) {
            return false;
        }

        char ultimo = texto.charAt(texto.length() - 1);
        return ultimo == '+' || ultimo == '-' || ultimo == '×' || ultimo == '÷';
    }

    // Escribir la coma solo una vez por numero
    public void escribirComa() {
        if (nuevoNumero) {
            texto = "0";
            nuevoNumero = false;
        }

        if (terminaEnOperador()) {
            texto = texto + "0,";
        } else {
            int inicio = texto.length() - 1;

            while (inicio >= 0) {
                char caracter = texto.charAt(inicio);

                if (caracter == '+' || caracter == '-' || caracter == '×' || caracter == '÷') {
                    break;
                }

                inicio--;
            }

            String numeroActual = texto.substring(inicio + 1);

            if (!numeroActual.contains(",")) {
                texto = texto + ",";
            }
        }

        tvDisplay.setText(texto);
    }

    // Añadir una operacion
    public void escribirOperacion(String operacion) {
        if (nuevoNumero) {
            nuevoNumero = false;
        }

        if (texto.length() > 0 && !terminaEnOperador()) {
            texto = texto + operacion;
            tvDisplay.setText(texto);
        }
    }

    // Borrar el ultimo caracter
    public void borrarUltimo() {
        if (texto.length() > 1) {
            texto = texto.substring(0, texto.length() - 1);

            if (terminaEnOperador()) {
                // No hace falta cambiar nada mas
            }
        } else {
            texto = "0";
        }

        tvDisplay.setText(texto);
    }

    // Calcular una expresion con una operacion
    public void calcularResultado() {
        int posicion = -1;
        char operador = ' ';

        for (int i = 1; i < texto.length(); i++) {
            char caracter = texto.charAt(i);

            if (caracter == '+' || caracter == '-' || caracter == '×' || caracter == '÷') {
                posicion = i;
                operador = caracter;
                break;
            }
        }

        if (posicion != -1 && !terminaEnOperador()) {
            try {
                String parte1 = texto.substring(0, posicion);
                String parte2 = texto.substring(posicion + 1);

                double numero1 = Double.parseDouble(parte1.replace(",", "."));
                double numero2 = Double.parseDouble(parte2.replace(",", "."));
                double resultado = 0;

                if (operador == '+') {
                    resultado = numero1 + numero2;
                } else if (operador == '-') {
                    resultado = numero1 - numero2;
                } else if (operador == '×') {
                    resultado = numero1 * numero2;
                } else if (operador == '÷') {
                    if (numero2 == 0) {
                        tvDisplay.setText("Error");
                        texto = "0";
                        nuevoNumero = true;
                        return;
                    }

                    resultado = numero1 / numero2;
                }

                texto = String.valueOf(resultado);

                if (texto.endsWith(".0")) {
                    texto = texto.substring(0, texto.length() - 2);
                }

                texto = texto.replace(".", ",");
                tvDisplay.setText(texto);
                nuevoNumero = true;

            } catch (Exception e) {
                tvDisplay.setText("Error");
                texto = "0";
                nuevoNumero = true;
            }
        }
    }
}
