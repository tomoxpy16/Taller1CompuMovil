package com.example.taller1.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taller1.databinding.ItemDestinationBinding
import com.example.taller1.model.Destination

class DestinationsAdapter(
    private val items: List<Destination>,
    private val onItemClick: (Destination) -> Unit
) : RecyclerView.Adapter<DestinationsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemDestinationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(destino: Destination) {
            binding.ItemNombre.text = destino.nombre
            binding.ItemPais.text = destino.pais
            binding.root.setOnClickListener { onItemClick(destino) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDestinationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
