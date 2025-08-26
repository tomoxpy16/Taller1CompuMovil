package com.example.taller1.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taller1.data.JsonLoader
import com.example.taller1.R
import com.example.taller1.model.Destination

class DestinationDetailActivity : AppCompatActivity() {

    private var isFavorite = false
    private var currentDestination: Destination? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination_detail)

        val id = intent.getIntExtra("dest_id", -1)
        val destinations = JsonLoader.loadDestinations(this)
        val destination = destinations.find {
            it.id == id
        }

        if (destination == null) {
            Toast.makeText(this, "No existe ese destino", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        currentDestination = destination

        isFavorite = MainActivity.favorites.any { it.id == destination.id }

        val tituloNombre = findViewById<TextView>(R.id.NombreDestino)
        val tituloPais = findViewById<TextView>(R.id.TítuloPaís)
        val tituloActividad = findViewById<TextView>(R.id.TítuloActividad)
        val tituloPrecio = findViewById<TextView>(R.id.TítuloPrecio)
        val tituloCategoria = findViewById<TextView>(R.id.TítuloCategoría)

        tituloNombre.text = destination.nombre
        tituloPais.text = destination.pais
        tituloActividad.text = destination.plan
        tituloPrecio.text = destination.precio.toString()
        tituloCategoria.text = destination.categoria

        botonFavoritos()
    }

    private fun botonFavoritos() {
        val btnFavoritos = findViewById<Button>(R.id.btnAddToFavorites)

        updateButtonState(btnFavoritos)

        btnFavoritos.setOnClickListener {
            currentDestination?.let { destination ->
                if (!isFavorite) {
                    // Agregar a favoritos
                    MainActivity.favorites.add(destination)
                    isFavorite = true
                    Toast.makeText(this, "Añadido a Favoritos", Toast.LENGTH_SHORT).show()
                } else {
                    MainActivity.favorites.removeAll { it.id == destination.id }
                    isFavorite = false
                    Toast.makeText(this, "Removido de Favoritos", Toast.LENGTH_SHORT).show()
                }
                updateButtonState(btnFavoritos)
            }
        }
    }

    private fun updateButtonState(button: Button) {
        if (isFavorite) {
            button.text = "Remover de Favoritos"
            button.isEnabled = true
        } else {
            button.text = "Añadir a Favoritos"
            button.isEnabled = true
        }
    }
}


