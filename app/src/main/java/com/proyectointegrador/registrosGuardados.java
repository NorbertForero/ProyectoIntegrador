package com.proyectointegrador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class registrosGuardados extends ListActivity {

    private List<orderData> listaDeDatosOrder;
    private DatabaseReference firebaseDatabase;
    private Button BotonVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros_guardados);

        listaDeDatosOrder = new ArrayList<>();

        final AdapterArray AdapterDatosOrder = new AdapterArray(this,listaDeDatosOrder);

        setListAdapter(AdapterDatosOrder);

        firebaseDatabase = FirebaseDatabase.getInstance().getReference("DataBaseUsers");
        firebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataNombre) {

                listaDeDatosOrder.clear();
                for (DataSnapshot snapshot : dataNombre.getChildren()){
                    Map<String,Object> mapKV = (Map)snapshot.getValue();

                    String nombre = (String)mapKV.get("nombre");
                    String apellido = mapKV.get("apellido").toString();
                    String serial = (String) mapKV.get("serial");
                    String tecnico = mapKV.get("tecnico").toString();
                    String estado = mapKV.get("estado").toString();

                    orderData datos = new orderData();
                    datos.setNombre(nombre);
                    datos.setApellido(apellido);
                    datos.setSerial(serial);
                    datos.setTecnico(tecnico);
                    datos.setEstado(estado);

                    listaDeDatosOrder.add(datos);
                }
                AdapterDatosOrder.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        BotonVolver = findViewById(R.id.volver);

        BotonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();

            }
        });






    }
}
