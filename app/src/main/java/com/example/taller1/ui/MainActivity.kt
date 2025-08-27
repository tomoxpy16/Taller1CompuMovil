package com.example.taller1.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.taller1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        const val EXTRA_CATEGORY = "categoria_seleccionada"
        val favorites = mutableListOf<com.example.taller1.model.Destination>()


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinner()

        binding.BotonExplorar.setOnClickListener {
            val selectedCategory = binding.SpinnerDestinos.selectedItem.toString()
            val intent = Intent(this, DestinationsListActivity::class.java)
            intent.putExtra(EXTRA_CATEGORY, selectedCategory)
            startActivity(intent)
        }

        binding.BotonFavoritos.setOnClickListener {
            startActivity(Intent(this, FavoritesActivity::class.java))
        }

        binding.BotonRecomendaciones.setOnClickListener {
            startActivity(Intent(this, RecommendationsActivity::class.java))
        }
    }

    private fun setupSpinner() {
        val categories = listOf("Todos", "Playas", "Montañas", "Ciudades Históricas", "Selvas", "Maravillas del Mundo")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.SpinnerDestinos.adapter = adapter
    }
}
