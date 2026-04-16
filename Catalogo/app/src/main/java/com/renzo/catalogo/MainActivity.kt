package com.renzo.catalogo

import android.os.Bundle
import android.content.SharedPreferences
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MainActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductoAdapter
    private var listaProductos = mutableListOf<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs = getSharedPreferences("productos_prefs", MODE_PRIVATE)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etCantidad = findViewById<EditText>(R.id.etCantidad)
        val etPrecio = findViewById<EditText>(R.id.etPrecio)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        cargarProductos()

        adapter = ProductoAdapter(listaProductos)
        recyclerView.adapter = adapter

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val cantidad = etCantidad.text.toString().toInt()
            val precio = etPrecio.text.toString().toFloat()

            val producto = Producto(nombre, cantidad, precio)
            listaProductos.add(producto)

            guardarProductos()

            adapter.notifyDataSetChanged()
        }
    }

    private fun guardarProductos() {
        val json = Gson().toJson(listaProductos)
        prefs.edit().putString("productos", json).apply()
    }

    private fun cargarProductos() {
        val json = prefs.getString("productos", null)
        if (json != null) {
            val tipo = object : TypeToken<MutableList<Producto>>() {}.type
            listaProductos = Gson().fromJson(json, tipo)
        }
    }
}