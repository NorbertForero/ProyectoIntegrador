package com.proyectointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.proyectointegrador.activityConsultarRegistro;

public class confirmarEliminacionRegistro extends AppCompatActivity {

    private Button botonAceptar;
    private Button botonCancelar;
    private String numSerial;
    private DatabaseReference fireBaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_eliminacion_registro);
        botonCancelar = findViewById(R.id.buttonCancelar);
        botonAceptar = findViewById(R.id.buttonAceptar);

        fireBaseDatabase = FirebaseDatabase.getInstance().getReference("DataBaseUsers");

        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                numSerial = intent.getStringExtra("serial");
                fireBaseDatabase.child(numSerial).removeValue();
                Intent intent2 = new Intent(getApplicationContext(), activityConsultarRegistro.class);
                startActivity(intent2);
                finish();
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
