package com.udc.actividad1app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tituloTextView;
    private EditText salarioBaseEditText;
    private EditText horasExtrasEditText;
    private EditText bonificacionEditText;
    private TextView resultadoTextView;
    private Button calcularButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tituloTextView = findViewById(R.id.titulounoTextView);
        salarioBaseEditText = findViewById(R.id.sbaseEditText);
        horasExtrasEditText = findViewById(R.id.hextrasEditText);
        bonificacionEditText = findViewById(R.id.bonificacionnEditText);
        resultadoTextView = findViewById(R.id.rsultadoTextView);
        calcularButton = findViewById(R.id.calculadorButton);

        calcularButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularSalario();
            }
        });
    }

    private void calcularSalario() {
        try {
            double salarioBase = Double.parseDouble(salarioBaseEditText.getText().toString());
            int horasExtras = Integer.parseInt(horasExtrasEditText.getText().toString());
            int bonificacion = Integer.parseInt(bonificacionEditText.getText().toString());

            double valorHoraNormal = salarioBase / 192;
            double valorHorasExtras = horasExtras * (valorHoraNormal * 1.25);
            double valorBonificaciones = bonificacion == 1 ? 0.05 * salarioBase : 0;
            double salarioTotalAntesDescuentos = salarioBase + valorHorasExtras + valorBonificaciones;

            double descuentoSalud = 0.035 * salarioTotalAntesDescuentos;
            double descuentoPension = 0.04 * salarioTotalAntesDescuentos;
            double descuentoCajaCompensacion = 0.01 * salarioTotalAntesDescuentos;

            double salarioTotalDespuesDescuentos = salarioTotalAntesDescuentos - (descuentoSalud + descuentoPension + descuentoCajaCompensacion);


            resultadoTextView.setText("Salario total después de descuentos: " + salarioTotalDespuesDescuentos);
        } catch (NumberFormatException e) {

            Toast.makeText(this, "Error en la entrada. Asegúrate de ingresar números válidos.", Toast.LENGTH_SHORT).show();
        }
    }

}