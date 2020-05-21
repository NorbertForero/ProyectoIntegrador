package com.proyectointegrador;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AdapterConsultarRegistro extends ArrayAdapter<orderData> {


    public AdapterConsultarRegistro(@NonNull Context context, @NonNull List<orderData> objects) {
        super(context,  0, objects);
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        orderData datos = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.template_consultar_registro,parent,false);

        TextView baseNombre = convertView.findViewById(R.id.nombret);
        TextView baseApellido = convertView.findViewById(R.id.apellidot);
        TextView baseSEquipo = convertView.findViewById(R.id.serialt);

        baseNombre.setText(datos.getNombre());
        baseApellido.setText(datos.getApellido());
        baseSEquipo.setText(datos.getSerial());

        return convertView;
    }
}
