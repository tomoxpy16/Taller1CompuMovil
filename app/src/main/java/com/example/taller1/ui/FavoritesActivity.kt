package com.example.taller1.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.taller1.R
import com.example.taller1.databinding.ActivityFavoritesBinding

class FavoritesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = getString(R.string.btn_favorites_text)

        val names = MainActivity.favorites.map {
            it.nombre
        }
        binding.ListaFavoritos.adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, names)
    }
}


