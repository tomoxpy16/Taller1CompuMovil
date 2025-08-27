package com.example.taller1.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.taller1.databinding.ActivityRecommendationsBinding
import kotlin.random.Random

class RecommendationsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecommendationsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecommendationsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showRecommendation()
    }

    private fun showRecommendation() {
        val favs = MainActivity.favorites
        if (favs.isEmpty()) {
            binding.TituloRecomendacionNombre.text = "N/A"
            binding.TituloRecomendacionActividad.text = ""
            return
        }

        // Contar cuántos destinos hay en cada categoría
        val freq = favs.groupingBy { it.categoria }.eachCount()

        // Obtener la categoría más frecuente
        val topCategory = freq.maxByOrNull { it.value }?.key

        if (topCategory == null) {
            binding.TituloRecomendacionNombre.text = "N/A"
            binding.TituloRecomendacionActividad.text = ""
            return
        }

        val pool = favs.filter { it.categoria == topCategory }

        val pick = pool[Random.nextInt(pool.size)]

        binding.TituloRecomendacionNombre.text = pick.nombre
        binding.TituloRecomendacionActividad.text = pick.plan
        binding.TituloRecomendacionPaS.text = pick.pais
        binding.TituloRecomendacionCategorA.text = pick.categoria
        binding.TituloRecomendacionPrecio.text = pick.precio.toString()

    }
}
