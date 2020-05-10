package com.proyectointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityEditarRegistro extends AppCompatActivity {
    private Button descartar;
    private Button actualizar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_registro);

        descartar = findViewById(R.id.limpiar);
        actualizar = findViewById(R.id.buttonActualizar);

        descartar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), activityConsultarRegistro.class);
                startActivity(intent);
                finish();
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityRegistroActualizado.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
