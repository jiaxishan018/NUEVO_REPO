package com.example.prctica2_gestioncuentabar;

import android.os.Bundle;
import android.graphics.Color;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText etBill;
    AutoCompleteTextView etWaiter;
    CheckBox cbTip;
    SeekBar sbTip;
    RadioGroup rgPayment;
    RatingBar rbRating;
    Button btnCalculate;
    TextView tvResult;
    TextView tvTipPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Buscar los elementos
        etBill = findViewById(R.id.etBill);
        etWaiter = findViewById(R.id.etWaiter);
        cbTip = findViewById(R.id.cbTip);
        sbTip = findViewById(R.id.sbTip);
        rgPayment = findViewById(R.id.rgPayment);
        rbRating = findViewById(R.id.rbRating);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);
        tvTipPercent = findViewById(R.id.tvTipPercent);

        // Lista de camareros
        String[] camareros = {"Ana", "Carlos", "Maria"};

        ArrayAdapter<String> adaptador = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                camareros
        );

        etWaiter.setAdapter(adaptador);
        etWaiter.setThreshold(3);

        // Mostrar propina
        tvTipPercent.setText("Propina: 10%");

        // Activar o desactivar propina
        cbTip.setOnCheckedChangeListener((boton, marcado) -> {
            sbTip.setEnabled(marcado);
        });

        // Cambiar porcentaje
        sbTip.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvTipPercent.setText("Propina: " + progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Boton calcular
        btnCalculate.setOnClickListener(v -> calcular());
    }

    public void calcular() {
        // Obtener importe
        String texto = etBill.getText().toString();
        if (texto.isEmpty()) {
            error("Introduce el importe.");
            return;
        }

        double cuenta;

        try {
            cuenta = Double.parseDouble(texto.replace(',', '.'));
        } catch (Exception e) {
            error("Introduce un numero valido.");
            return;
        }

        if (cuenta <= 0) {
            error("El importe debe ser mayor que cero.");
            return;
        }

        // Obtener camarero
        String camarero = etWaiter.getText().toString();

        if (camarero.isEmpty()) {
            error("Introduce un camarero.");
            return;
        }

        // Calcular propina
        double propina = 0;

        if (cbTip.isChecked()) {
            propina = cuenta * sbTip.getProgress() / 100;
        }

        // Calcular total
        double total = cuenta + propina;

        // Obtener metodo de pago
        int id = rgPayment.getCheckedRadioButtonId();

        if (id == -1) {
            error("Selecciona un metodo de pago.");
            return;
        }

        RadioButton radio = findViewById(id);
        String pago = radio.getText().toString();

        // Obtener estrellas
        float estrellas = rbRating.getRating();

        // Mostrar resultado
        tvResult.setTextColor(Color.BLACK);
        tvResult.setText("Cuenta: " + cuenta + " €\n" + "Propina: " + propina + " €\n" + "Total: " + total + " €\n" +"Pago: " + pago + "\n" + "Camarero: " + camarero + "\n" + "Calificacion: " + estrellas + "/5"
        );
    }

    // Mostrar error
    public void error(String mensaje) {
        tvResult.setTextColor(Color.RED);
        tvResult.setText(mensaje);
    }
}
