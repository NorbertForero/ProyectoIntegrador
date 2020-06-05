package com.proyectointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.proyectointegrador.activityConsultarRegistro;

public class confirmarEliminacionRegistro extends AppCompatActivity {

    private Button botonAceptar;
    private Button botonCancelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_eliminacion_registro);
        botonCancelar = findViewById(R.id.buttonCancelar);
        botonAceptar = findViewById(R.id.buttonAceptar);

        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
