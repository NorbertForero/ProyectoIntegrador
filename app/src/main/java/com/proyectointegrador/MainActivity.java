package com.proyectointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.proyectointegrador.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {
    private Button flujo1;
    private Button flujo2;
    private Button flujo3;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        flujo1 = findViewById(R.id.registrarOrden);
        flujo2 = findViewById(R.id.consultarRegistro);
        flujo3 = findViewById(R.id.registrosGuardados);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }

        flujo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), newRegister.class);
                startActivity(intent);
            }
        });




    }
}
