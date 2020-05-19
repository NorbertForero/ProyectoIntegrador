package com.proyectointegrador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class activityConsultarRegistro extends AppCompatActivity {

    private Button editar;
    private EditText textConsultar;
    private Button search;
    private RecyclerView resultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_registro);

        editar = findViewById(R.id.buttonEditar);
        textConsultar= findViewById(R.id.editTextConsultar);
        search = findViewById(R.id.buttonBuscar);
        resultado = findViewById(R.id.recyclerviewResultado);


        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityEditarRegistro.class);
                startActivity(intent);

            }
        });

    }
}
