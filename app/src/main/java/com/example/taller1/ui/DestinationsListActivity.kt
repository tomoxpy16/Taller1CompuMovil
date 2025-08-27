package com.example.taller1.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taller1.data.JsonLoader
import com.example.taller1.databinding.ActivityDestinationsListBinding
import com.example.taller1.model.Destination
import com.example.taller1.ui.adapter.DestinationsAdapter

class DestinationsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDestinationsListBinding

    companion object {
        const val EXTRA_CATEGORY = "categoria_seleccionada"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDestinationsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ListaDestinos.layoutManager = LinearLayoutManager(this)

        val categoryRaw = intent.getStringExtra(EXTRA_CATEGORY) ?: "Todos"

        val itemsAll = JsonLoader.loadDestinations(this)
        val items: List<Destination> = if (categoryRaw.equals("Todos", ignoreCase = true)) {
            itemsAll
        } else {
            itemsAll.filter {
                it.categoria.equals(categoryRaw, ignoreCase = true)
            }
        }

        if (items.isEmpty()) {
            Toast.makeText(this, "Esta categoría no tiene destinos", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        binding.ListaDestinos.adapter = DestinationsAdapter(items) { destino ->
            val intent = Intent(this, DestinationDetailActivity::class.java)
            intent.putExtra("dest_id", destino.id)
            startActivity(intent)
        }
    }
}

