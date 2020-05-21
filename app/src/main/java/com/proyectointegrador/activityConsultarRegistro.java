package com.proyectointegrador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class activityConsultarRegistro extends ListActivity {

    private List<orderData> listaDeDatosOrder;
    private DatabaseReference firebaseDatabase;
    private Button botonBuscar;
    private EditText editTextInsertar;
    private ListView list;
    private orderData personaSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_registro);

        firebaseDatabase = FirebaseDatabase.getInstance().getReference("DataBaseUsers");
        botonBuscar = findViewById(R.id.buttonBuscar);
        editTextInsertar = findViewById(R.id.editTextConsultar);
        list = findViewById(android.R.id.list);
        listaDeDatosOrder = new ArrayList<>();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                personaSelected = (orderData) adapterView.getItemAtPosition(i);
                final String serialN = personaSelected.getSerial().trim();
                Query serial = firebaseDatabase.orderByChild("serial").equalTo(serialN);
                serial.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String sNumber = dataSnapshot.child(serialN).child("serial").getValue(String.class);
                        if (sNumber.equals(serialN)){
                            String nombreDB = dataSnapshot.child(serialN).child("nombre").getValue(String.class);
                            String apellidoDB = dataSnapshot.child(serialN).child("apellido").getValue(String.class);
                            String serialDB = dataSnapshot.child(serialN).child("serial").getValue(String.class);
                            String celularDB = dataSnapshot.child(serialN).child("celular").getValue(String.class);
                            String correoDB = dataSnapshot.child(serialN).child("correo").getValue(String.class);
                            String cedulaDB = dataSnapshot.child(serialN).child("cedula").getValue(String.class);
                            String valorArregloDB = dataSnapshot.child(serialN).child("valorArreglo").getValue(String.class);
                            String fechaIngresoDB = dataSnapshot.child(serialN).child("fechaIngreso").getValue(String.class);
                            String fechaSalidaDB = dataSnapshot.child(serialN).child("fechaSalida").getValue(String.class);
                            String tecnicoDB = dataSnapshot.child(serialN).child("tecnico").getValue(String.class);
                            String estadoDB = dataSnapshot.child(serialN).child("estado").getValue(String.class);

                            Intent intent = new Intent(getApplicationContext(),ActivityEditarRegistro.class);
                            intent.putExtra("nombre", nombreDB);
                            intent.putExtra("apellido", apellidoDB);
                            intent.putExtra("serial", serialDB);
                            intent.putExtra("celular", celularDB);
                            intent.putExtra("correo", correoDB);
                            intent.putExtra("cedula", cedulaDB);
                            intent.putExtra("valorArreglo", valorArregloDB);
                            intent.putExtra("fechaIngreso", fechaIngresoDB);
                            intent.putExtra("fechaSalida", fechaSalidaDB);
                            intent.putExtra("tecnico", tecnicoDB);
                            intent.putExtra("estado", estadoDB);


                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        final AdapterConsultarRegistro AdapterDatosOrder = new AdapterConsultarRegistro(this,listaDeDatosOrder);

        setListAdapter(AdapterDatosOrder);

        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String searchText = editTextInsertar.getText().toString();

                firebaseDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataNombre) {

                        listaDeDatosOrder.clear();
                        for (DataSnapshot snapshot : dataNombre.getChildren()){
                            Map<String,Object> mapKV = (Map)snapshot.getValue();

                            String nombre = (String)mapKV.get("nombre");
                            String apellido = mapKV.get("apellido").toString();
                            String serial = (String) mapKV.get("serial");

                            orderData datos = new orderData();
                            if(!searchText.isEmpty()){
                                if (serial.toUpperCase().contains(searchText.toUpperCase())){
                                    datos.setNombre(nombre);
                                    datos.setApellido(apellido);
                                    datos.setSerial(serial);

                                    listaDeDatosOrder.add(datos);
                                }
                            }else {
                                Toast miToast = Toast.makeText(getApplicationContext(),"Campo de búsqueda vacío",Toast.LENGTH_LONG);
                                miToast.show();
                            }
                        }
                        AdapterDatosOrder.notifyDataSetChanged();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });

    }
}
