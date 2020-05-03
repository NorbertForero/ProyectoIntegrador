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

    private List<String> listaDeNombre;
    private DatabaseReference firebaseDatabase;
    private Button BotonVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros_guardados);

        listaDeNombre = new ArrayList<>();

        final ArrayAdapter<String> AdapterApellido = new ArrayAdapter<String>(
                this,
                R.layout.fila_template_listview,
                //R.id.txtNombreFila,
                R.id.nombret,
                listaDeNombre
        );
        setListAdapter(AdapterApellido);

        firebaseDatabase = FirebaseDatabase.getInstance().getReference("DataBaseUsers");
        firebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataNombre) {

                listaDeNombre.clear();
                for (DataSnapshot snapshot : dataNombre.getChildren()){
                    Map<String,Object> mapKV = (Map)snapshot.getValue();

                    String nombre = (String)mapKV.get("nombre");
                    //String apellido = mapKV.get("apellido").toString();

                    listaDeNombre.add(nombre);
                }
                AdapterApellido.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        BotonVolver = findViewById(R.id.volver);

        BotonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });






    }
}
