package com.proyectointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activityConsultarRegistro extends AppCompatActivity {

    private Button editar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_registro);

        editar = findViewById(R.id.buttonEditar);

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityEditarRegistro.class);
                startActivity(intent);

            }
        });

    }
}
