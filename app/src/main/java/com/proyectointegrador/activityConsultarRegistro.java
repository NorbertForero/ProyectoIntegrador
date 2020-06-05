package com.proyectointegrador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableRow;
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
    private Button botonEliminar;
    private EditText editTextInsertar;
    private ListView list;
    private orderData personaSelected;
    private TableRow fila;
    private Button  botonEditar;
    private String nombreDB,apellidoDB,serialDB,celularDB,correoDB,cedulaDB,valorArregloDB,fechaIngresoDB,fechaSalidaDB,tecnicoDB,estadoDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_registro);

        firebaseDatabase = FirebaseDatabase.getInstance().getReference("DataBaseUsers");
        botonBuscar = findViewById(R.id.buttonBuscar);
        botonEliminar = findViewById(R.id.eliminar);
        editTextInsertar = findViewById(R.id.editTextConsultar);
        list = findViewById(android.R.id.list);
        fila = findViewById(R.id.tableRow);
        botonEditar = findViewById(R.id.editar);

        listaDeDatosOrder = new ArrayList<>();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                personaSelected = (orderData) adapterView.getItemAtPosition(i);
                final String serialN = personaSelected.getSerial().trim();
                final Query serial = firebaseDatabase.orderByChild("serial").equalTo(serialN);


                serial.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String sNumber = dataSnapshot.child(serialN).child("serial").getValue(String.class);
                        if (sNumber.equals(serialN)){
                            nombreDB = dataSnapshot.child(serialN).child("nombre").getValue(String.class);
                            apellidoDB = dataSnapshot.child(serialN).child("apellido").getValue(String.class);
                            serialDB = dataSnapshot.child(serialN).child("serial").getValue(String.class);
                            celularDB = dataSnapshot.child(serialN).child("celular").getValue(String.class);
                            correoDB = dataSnapshot.child(serialN).child("correo").getValue(String.class);
                            cedulaDB = dataSnapshot.child(serialN).child("cedula").getValue(String.class);
                            valorArregloDB = dataSnapshot.child(serialN).child("valorArreglo").getValue(String.class);
                            fechaIngresoDB = dataSnapshot.child(serialN).child("fechaIngreso").getValue(String.class);
                            fechaSalidaDB = dataSnapshot.child(serialN).child("fechaSalida").getValue(String.class);
                            tecnicoDB = dataSnapshot.child(serialN).child("tecnico").getValue(String.class);
                            estadoDB = dataSnapshot.child(serialN).child("estado").getValue(String.class);

                            botonEditar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
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

                            });
                        }

                        botonEliminar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                /*Intent intent12 = new Intent(getApplicationContext(), confirmarEliminacionRegistro.class);
                                intent12.putExtra("serial",serialDB);
                                startActivity(intent12);*/
                                AlertDialog.Builder builder = new AlertDialog.Builder(activityConsultarRegistro.this);
                                LayoutInflater inflater = getLayoutInflater();
                                View view = inflater.inflate(R.layout.activity_confirmar_eliminacion_registro, null);
                                builder.setView(view);
                                final AlertDialog dialogPop = builder.create();
                                dialogPop.show();
                                Button btAceptar = view.findViewById(R.id.buttonAceptar);
                                btAceptar.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        firebaseDatabase.child(serialDB).removeValue();
                                        dialogPop.cancel();
                                    }
                                });
                                Button btCancelar = view.findViewById(R.id.buttonCancelar);
                                btCancelar.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialogPop.cancel();
                                    }
                                });
                            }
                        });
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
                                    fila.setVisibility(View.VISIBLE);
                                    datos.setNombre(nombre);
                                    datos.setApellido(apellido);
                                    datos.setSerial(serial);

                                    listaDeDatosOrder.add(datos);
                                }else{
                                    fila.setVisibility(View.INVISIBLE);
                                }
                            }else {
                                fila.setVisibility(View.INVISIBLE);
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
