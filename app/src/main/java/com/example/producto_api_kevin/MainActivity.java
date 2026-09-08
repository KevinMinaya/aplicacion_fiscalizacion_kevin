package com.example.producto_api_kevin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView txtProductos;
    private Button btnCargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtProductos = findViewById(R.id.txtProductos);
        btnCargar = findViewById(R.id.btnCargar);

        btnCargar.setOnClickListener(v -> cargarProductos());
    }

    private void cargarProductos() {

        ApiService apiService = RetrofitClient.getApiService();

        Call<List<Producto>> llamada = apiService.obtenerProductos();

        llamada.enqueue(new Callback<List<Producto>>() {

            @Override
            public void onResponse(Call<List<Producto>> call,
                                   Response<List<Producto>> response) {

                if (response.isSuccessful() && response.body() != null) {

                    List<Producto> productos = response.body();

                    StringBuilder resultado = new StringBuilder();

                    for (Producto producto : productos) {

                        resultado.append("ID: ")
                                .append(producto.getId())
                                .append("\n");

                        resultado.append("Nombre: ")
                                .append(producto.getTitle())
                                .append("\n");

                        resultado.append("Precio: $")
                                .append(producto.getPrice())
                                .append("\n");

                        resultado.append("Categoría: ")
                                .append(producto.getCategory())
                                .append("\n\n");
                    }

                    txtProductos.setText(resultado.toString());

                } else {
                    txtProductos.setText("No se pudieron obtener los productos.");
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {

                Toast.makeText(
                        MainActivity.this,
                        "Error de conexión: " + t.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }
}