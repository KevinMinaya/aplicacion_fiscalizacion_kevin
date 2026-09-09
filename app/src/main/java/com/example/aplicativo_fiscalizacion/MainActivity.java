package com.example.aplicativo_fiscalizacion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnNuevaFiscalizacion;
    private Button btnVerFiscalizaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNuevaFiscalizacion = findViewById(R.id.btnNuevaFiscalizacion);
        btnVerFiscalizaciones = findViewById(R.id.btnVerFiscalizaciones);

        btnNuevaFiscalizacion.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    NuevaFiscalizacionActivity.class
            );
            startActivity(intent);
        });

        btnVerFiscalizaciones.setOnClickListener(v -> {
            // Lo programaremos después.
        });
    }
}