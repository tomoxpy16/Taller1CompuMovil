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
            binding.TituloRecomendacionNombre.text = "NA";
            binding.TituloRecomendacionActividad.text = "";
            return
        }
        val freq = favs.groupingBy {
            it.categoria
        }.eachCount()
        val topCategory = freq.maxByOrNull { it.value }?.key
        13
        val pool = favs.filter { it.categoria == topCategory }
        val pick = pool[Random.nextInt(pool.size)]
        binding.TituloRecomendacionNombre.text = pick.nombre
        binding.TituloRecomendacionActividad.text = pick.plan
    }
}