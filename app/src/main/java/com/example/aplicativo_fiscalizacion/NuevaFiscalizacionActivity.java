package com.example.aplicativo_fiscalizacion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class NuevaFiscalizacionActivity extends AppCompatActivity {

    private EditText edtExpediente;
    private EditText edtAgente;
    private EditText edtCodigoOsinergmin;
    private EditText edtRegistroHidrocarburos;
    private EditText edtFecha;
    private EditText edtHoraApertura;
    private EditText edtHoraCierre;
    private EditText edtDireccion;
    private EditText edtDistrito;
    private EditText edtProvincia;
    private EditText edtDepartamento;
    private EditText edtRucDni;
    private EditText edtTelefono;

    private EditText edtPrecioDiesel;
    private EditText edtPrecioGasolinaRegular;
    private EditText edtPrecioGasolinaPremium;
    private EditText edtPrecioGasoholRegular;
    private EditText edtPrecioGasoholPremium;

    private EditText edtIncumplimientos;
    private EditText edtHechosVerificados;
    private EditText edtOtros;

    private Button btnGuardarFiscalizacion;
    private Button btnGenerarActa;
    private EditText edtIncumplimiento1;
    private EditText edtHechos1;

    private EditText edtIncumplimiento2;
    private EditText edtHechos2;

    private EditText edtIncumplimiento3;
    private EditText edtHechos3;

    private EditText edtIncumplimiento4;
    private EditText edtHechos4;

    private EditText edtIncumplimiento5;
    private EditText edtHechos5;

    private EditText edtIncumplimiento6;
    private EditText edtHechos6;

    private EditText edtOtrasOcurrencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_fiscalizacion);

        // DATOS DEL ESTABLECIMIENTO
        edtExpediente = findViewById(R.id.edtExpediente);
        edtAgente = findViewById(R.id.edtAgente);
        edtCodigoOsinergmin = findViewById(R.id.edtCodigoOsinergmin);
        edtRegistroHidrocarburos = findViewById(R.id.edtRegistroHidrocarburos);
        edtFecha = findViewById(R.id.edtFecha);
        edtHoraApertura = findViewById(R.id.edtHoraApertura);
        edtHoraCierre = findViewById(R.id.edtHoraCierre);
        edtDireccion = findViewById(R.id.edtDireccion);
        edtDistrito = findViewById(R.id.edtDistrito);
        edtProvincia = findViewById(R.id.edtProvincia);
        edtDepartamento = findViewById(R.id.edtDepartamento);
        edtRucDni = findViewById(R.id.edtRucDni);
        edtTelefono = findViewById(R.id.edtTelefono);

        // PRECIOS
        edtPrecioDiesel = findViewById(R.id.edtPrecioDiesel);
        edtPrecioGasolinaRegular = findViewById(R.id.edtPrecioGasolinaRegular);
        edtPrecioGasolinaPremium = findViewById(R.id.edtPrecioGasolinaPremium);
        edtPrecioGasoholRegular = findViewById(R.id.edtPrecioGasoholRegular);
        edtPrecioGasoholPremium = findViewById(R.id.edtPrecioGasoholPremium);

        // INCUMPLIMIENTOS Y OBSERVACIONES
        edtIncumplimiento1 = findViewById(R.id.edtIncumplimiento1);
        edtHechos1 = findViewById(R.id.edtHechos1);

        edtIncumplimiento2 = findViewById(R.id.edtIncumplimiento2);
        edtHechos2 = findViewById(R.id.edtHechos2);

        edtIncumplimiento3 = findViewById(R.id.edtIncumplimiento3);
        edtHechos3 = findViewById(R.id.edtHechos3);

        edtIncumplimiento4 = findViewById(R.id.edtIncumplimiento4);
        edtHechos4 = findViewById(R.id.edtHechos4);

        edtIncumplimiento5 = findViewById(R.id.edtIncumplimiento5);
        edtHechos5 = findViewById(R.id.edtHechos5);

        edtIncumplimiento6 = findViewById(R.id.edtIncumplimiento6);
        edtHechos6 = findViewById(R.id.edtHechos6);

        edtOtrasOcurrencias = findViewById(R.id.edtOtrasOcurrencias);

        // BOTONES
        btnGuardarFiscalizacion = findViewById(R.id.btnGuardarFiscalizacion);
        btnGenerarActa = findViewById(R.id.btnGenerarActa);

        // BOTÓN GUARDAR
        btnGuardarFiscalizacion.setOnClickListener(v -> guardarFiscalizacion());

        // BOTÓN GENERAR ACTA
        btnGenerarActa.setOnClickListener(v -> generarActa());
    }

    private void guardarFiscalizacion() {

        String expediente = edtExpediente.getText().toString().trim();
        String agente = edtAgente.getText().toString().trim();

        if (expediente.isEmpty() || agente.isEmpty()) {

            Toast.makeText(
                    this,
                    "Complete el expediente y el agente fiscalizado",
                    Toast.LENGTH_LONG
            ).show();

            return;
        }

        Toast.makeText(
                this,
                "Fiscalización lista para guardar",
                Toast.LENGTH_SHORT
        ).show();
    }

    private void generarActa() {

        String expediente = edtExpediente.getText().toString().trim();

        if (expediente.isEmpty()) {

            Toast.makeText(
                    this,
                    "Ingrese primero el número de expediente",
                    Toast.LENGTH_LONG
            ).show();

            return;
        }

        try {

            java.io.File archivo = ActaPDFGenerator.generarPDF(

                    this,

                    edtExpediente.getText().toString(),
                    edtAgente.getText().toString(),
                    edtCodigoOsinergmin.getText().toString(),
                    edtRegistroHidrocarburos.getText().toString(),
                    edtFecha.getText().toString(),
                    edtHoraApertura.getText().toString(),
                    edtHoraCierre.getText().toString(),
                    edtDireccion.getText().toString(),
                    edtDistrito.getText().toString(),
                    edtProvincia.getText().toString(),
                    edtDepartamento.getText().toString(),
                    edtRucDni.getText().toString(),
                    edtTelefono.getText().toString(),

                    edtPrecioDiesel.getText().toString(),
                    edtPrecioGasolinaRegular.getText().toString(),
                    edtPrecioGasolinaPremium.getText().toString(),
                    edtPrecioGasoholRegular.getText().toString(),
                    edtPrecioGasoholPremium.getText().toString(),

                    edtIncumplimientos.getText().toString(),
                    edtHechosVerificados.getText().toString(),
                    edtOtros.getText().toString()
            );

            Toast.makeText(
                    this,
                    "PDF generado correctamente:\n" + archivo.getName(),
                    Toast.LENGTH_LONG
            ).show();
            Intent intent = new Intent(Intent.ACTION_VIEW);

            android.net.Uri uri = androidx.core.content.FileProvider.getUriForFile(
                    this,
                    getPackageName() + ".provider",
                    archivo
            );

            intent.setDataAndType(uri, "application/pdf");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            startActivity(intent);

        } catch (Exception e) {

            Toast.makeText(
                    this,
                    "Error al generar PDF: " + e.getMessage(),
                    Toast.LENGTH_LONG
            ).show();
        }
    }
}