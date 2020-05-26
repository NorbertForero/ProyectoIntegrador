package com.proyectointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityRegistroActualizado extends AppCompatActivity {

    private Button volver;
    private Button seguirBuscando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_actualizado);

        volver = findViewById(R.id.buttonVolver);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        seguirBuscando=findViewById(R.id.buttonSeguirBuscando);
        seguirBuscando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), activityConsultarRegistro.class);
                startActivity(intent);
            }
        });
    }
}
