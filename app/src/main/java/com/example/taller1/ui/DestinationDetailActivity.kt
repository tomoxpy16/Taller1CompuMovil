package com.example.taller1.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taller1.data.JsonLoader
import com.example.taller1.R
import com.example.taller1.databinding.ActivityDestinationDetailBinding

class DestinationDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDestinationDetailBinding
    private var isFavorite = false

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


    }

    private fun botonFavoritos() {
        binding.btnAddToFavorites.setOnClickListener {
            if (!isFavorite) {
                isFavorite = true
                binding.btnAddToFavorites.isEnabled = false
                binding.btnAddToFavorites.text = "Añadido a favoritos"
                Toast.makeText(this, "Añadido a favoritos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


