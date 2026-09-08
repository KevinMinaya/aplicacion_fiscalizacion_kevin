package com.example.producto_api_kevin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("products")
    Call<List<Producto>> obtenerProductos();
}