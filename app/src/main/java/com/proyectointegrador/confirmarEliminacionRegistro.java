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

    }
}
