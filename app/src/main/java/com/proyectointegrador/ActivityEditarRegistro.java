package com.proyectointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityEditarRegistro extends AppCompatActivity {
    private Button descartar;
    private Button actualizar;
    private TextView mNombre;
    private TextView mApellido;
    private TextView msnEquipo;
    private TextView mCelular;
    private TextView mCorreo;
    private TextView mCedula;
    private TextView mValorArreglo;
    private TextView mFechaIngreso;
    private TextView mFechaSalida;
    private TextView mTecnicoAsignado;
    private TextView mEstado;
    private DatabaseReference fireBaseDatabase;
    private String nombreUsuario,apellidoUsuario,serialUsuario,celularUsuario,correoUsuario,cedulaUsuario,valorarreglo,fechaentrada,fechasalida,tecnico,estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_registro);

        fireBaseDatabase = FirebaseDatabase.getInstance().getReference("DataBaseUsers");

        mNombre = findViewById(R.id.nombres);
        mApellido = findViewById(R.id.apellidos);
        msnEquipo = findViewById(R.id.equipo);
        mCelular = findViewById(R.id.celular);
        mCorreo = findViewById(R.id.correo);
        mCedula = findViewById(R.id.cedula);
        mValorArreglo = findViewById(R.id.valorArreglo);
        mFechaIngreso = findViewById(R.id.fechaIngreso);
        mFechaSalida = findViewById(R.id.fechaSalida);
        mTecnicoAsignado = findViewById(R.id.tecnico);
        mEstado = findViewById(R.id.estado);
        descartar = findViewById(R.id.btnDescartar);
        actualizar = findViewById(R.id.btnActualizar);

        showAllUserData();


        descartar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), activityConsultarRegistro.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void showAllUserData() {
        Intent intent = getIntent();
        nombreUsuario = intent.getStringExtra("nombre");
        apellidoUsuario = intent.getStringExtra("apellido");
        serialUsuario = intent.getStringExtra("serial");
        celularUsuario = intent.getStringExtra("celular");
        correoUsuario = intent.getStringExtra("correo");
        cedulaUsuario = intent.getStringExtra("cedula");
        valorarreglo = intent.getStringExtra("valorArreglo");
        fechaentrada = intent.getStringExtra("fechaIngreso");
        fechasalida = intent.getStringExtra("fechaSalida");
        tecnico = intent.getStringExtra("tecnico");
        estado = intent.getStringExtra("estado");

        mNombre.setText(nombreUsuario);
        mApellido.setText(apellidoUsuario);
        msnEquipo.setText(serialUsuario);
        mCelular.setText(celularUsuario);
        mCorreo.setText(correoUsuario);
        mCedula.setText(cedulaUsuario);
        mValorArreglo.setText(valorarreglo);
        mFechaIngreso.setText(fechaentrada);
        mFechaSalida.setText(fechasalida);
        mTecnicoAsignado.setText(tecnico);
        mEstado.setText(estado);
    }

    public void ActualizarRegistro(View view) {
        if (isNombreChanged() || isApellidoChanged() || isSerialChanged() || isCelularChanged() || isCorreoChanged() || isCedulaChanged() || isValorArregloChanged() || isFechaEntradaChanged() || isFechaSalidaChanged() || isTecnicoChanged() || isEstadoChanged()) {
            Toast.makeText(this, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),ActivityRegistroActualizado.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "No hay datos diferentes para actualizar", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isEstadoChanged() {
        if (!estado.equals(mEstado.getText().toString())){
            fireBaseDatabase.child(serialUsuario).child("estado").setValue(mEstado.getText().toString());
            return true;
        }else {
            return false;
        }
    }

    private boolean isTecnicoChanged() {
        if (!tecnico.equals(mTecnicoAsignado.getText().toString())){
            fireBaseDatabase.child(serialUsuario).child("tecnico").setValue(mTecnicoAsignado.getText().toString());
            return true;
        }else {
            return false;
        }
    }

    private boolean isFechaSalidaChanged() {
        if (!fechasalida.equals(mFechaSalida.getText().toString())){
            fireBaseDatabase.child(serialUsuario).child("fechaSalida").setValue(mFechaSalida.getText().toString());
            return true;
        }else {
            return false;
        }
    }

    private boolean isFechaEntradaChanged() {
        if (!fechaentrada.equals(mFechaIngreso.getText().toString())){
            fireBaseDatabase.child(serialUsuario).child("fechaIngreso").setValue(mFechaIngreso.getText().toString());
            return true;
        }else {
            return false;
        }
    }

    private boolean isValorArregloChanged() {
        if (!valorarreglo.equals(mValorArreglo.getText().toString())){
            fireBaseDatabase.child(serialUsuario).child("valorArreglo").setValue(mValorArreglo.getText().toString());
            return true;
        }else {
            return false;
        }
    }

    private boolean isCedulaChanged() {
        if (!cedulaUsuario.equals(mCedula.getText().toString())){
            fireBaseDatabase.child(serialUsuario).child("cedula").setValue(mCedula.getText().toString());
            return true;
        }else {
            return false;
        }
    }

    private boolean isCorreoChanged() {
        if (!correoUsuario.equals(mCorreo.getText().toString())){
            fireBaseDatabase.child(serialUsuario).child("correo").setValue(mCorreo.getText().toString());
            return true;
        }else {
            return false;
        }
    }

    private boolean isCelularChanged() {
        if (!celularUsuario.equals(mCelular.getText().toString())){
            fireBaseDatabase.child(serialUsuario).child("celular").setValue(mCelular.getText().toString());
            return true;
        }else {
            return false;
        }
    }

    private boolean isSerialChanged() {
        if (!serialUsuario.equals(msnEquipo.getText().toString())){
            fireBaseDatabase.child(serialUsuario).child("serial").setValue(msnEquipo.getText().toString());
            return true;
        }else {
            return false;
        }
    }

    private boolean isApellidoChanged() {
        if (!apellidoUsuario.equals(mApellido.getText().toString())){
            fireBaseDatabase.child(serialUsuario).child("apellido").setValue(mApellido.getText().toString());
            return true;
        }else {
            return false;
        }
    }

    private boolean isNombreChanged() {
        if (!nombreUsuario.equals(mNombre.getText().toString())){
            fireBaseDatabase.child(serialUsuario).child("nombre").setValue(mNombre.getText().toString());
            return true;
        }else {
            return false;
        }
    }
}