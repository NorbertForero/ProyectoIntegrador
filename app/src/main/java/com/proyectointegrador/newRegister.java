package com.proyectointegrador;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class newRegister extends AppCompatActivity {

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
    private Button mLimpiar;
    private Button mRegistrarOrden;
    private DatabaseReference fireBaseDatabase;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_register);

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
        mLimpiar = findViewById(R.id.limpiar);
        mRegistrarOrden = findViewById(R.id.registrarOrden);

        fireBaseDatabase = FirebaseDatabase.getInstance().getReference("DataBaseUsers");

        mRegistrarOrden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validar())
                {
                    String strNombre = mNombre.getText().toString();
                    String strApellido = mApellido.getText().toString();
                    String strSerial = msnEquipo.getText().toString();
                    String strCelular = mCelular.getText().toString();
                    String strCorreo = mCorreo.getText().toString();
                    String strCedula = mCedula.getText().toString();
                    String strValorArreglo = mValorArreglo.getText().toString();
                    String strFechaIngreso = mFechaIngreso.getText().toString();
                    String strFechaSalida = mFechaSalida.getText().toString();
                    String strTecnico = mTecnicoAsignado.getText().toString();
                    String strEstado = mEstado.getText().toString();

                    Toast miToast = Toast.makeText(getApplicationContext(), "Datos Registrados", Toast.LENGTH_LONG);

                    orderData datosRegistro = new orderData();
                    datosRegistro.setNombre(strNombre);
                    datosRegistro.setApellido(strApellido);
                    datosRegistro.setSerial(strSerial);
                    datosRegistro.setCelular(strCelular);
                    datosRegistro.setCorreo(strCorreo);
                    datosRegistro.setCedula(strCedula);
                    datosRegistro.setValorArreglo(strValorArreglo);
                    datosRegistro.setFechaIngreso(strFechaIngreso);
                    datosRegistro.setFechaSalida(strFechaSalida);
                    datosRegistro.setTecnico(strTecnico);
                    datosRegistro.setEstado(strEstado);


                    fireBaseDatabase.child(strSerial).setValue(datosRegistro);

                    miToast.show();
                    finish();
                    Intent irMain = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(irMain);
                    finish();
                }

            }
        });

        mLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNombre.setText("");
                mApellido.setText("");
                msnEquipo.setText("");
                mCelular.setText("");
                mCorreo.setText("");
                mCedula.setText("");
                mValorArreglo.setText("");
                mFechaIngreso.setText("");
                mFechaSalida.setText("");
                mTecnicoAsignado.setText("");
                mEstado.setText("");

                Toast miToast = Toast.makeText(getApplicationContext(),"Datos Eliminados",Toast.LENGTH_LONG);

            }
        });
    }

    public boolean validar(){

        boolean validation = true;

        String c1 = mNombre.getText().toString();
        String c2 = mApellido.getText().toString();
        String c3 = msnEquipo.getText().toString();
        String c4 = mCelular.getText().toString();
        String c5 = mCorreo.getText().toString();
        String c6 = mCedula.getText().toString();
        String c7 = mValorArreglo.getText().toString();
        String c8 = mFechaIngreso.getText().toString();
        String c9 = mFechaSalida.getText().toString();
        String c10 = mEstado.getText().toString();

        if (c1.isEmpty()) {
            mNombre.setError("Campo vacio");
            return false;
        }
        if (c2.isEmpty()) {
            mApellido.setError("Campo vacio");
            return false;
        }
        if (c3.isEmpty()) {
            msnEquipo.setError("Campo vacio");
            return false;
        }
        if (c4.isEmpty()) {
            mCelular.setError("Campo vacio");
            return false;
        }
        if (c5.isEmpty()) {
            mCorreo.setError("Campo vacio");
            return false;
        }
        if (c6.isEmpty()) {
            mCedula.setError("Campo vacio");
            return false;
        }
        if (c7.isEmpty()) {
            mValorArreglo.setError("Campo vacio");
            return false;
        }
        if (c8.isEmpty()) {
            mFechaIngreso.setError("Campo vacio");
            return false;
        }
        if (c9.isEmpty()) {
            mFechaSalida.setError("Campo vacio");
            return false;
        }
        if (c10.isEmpty()) {
            mEstado.setError("Campo vacio");
            return false;
        }
        return validation;
    }
}