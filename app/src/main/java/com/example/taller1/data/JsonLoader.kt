package com.example.taller1.data

import android.content.Context
import com.example.taller1.model.Destination
import org.json.JSONObject

object JsonLoader {

    fun loadDestinations(context: Context): List<Destination> {
        val jsonString = context.assets.open("destinos.json").bufferedReader().use {
            it.readText()
        }
        val jsonObject = JSONObject(jsonString)
        val destinosArray = jsonObject.getJSONArray("destinos")

        val destinations = mutableListOf<Destination>()

        for (i in 0 until destinosArray.length()) {
            val o = destinosArray.getJSONObject(i)
            val destination = Destination(
                id = o.getInt("id"),
                nombre = o.getString("nombre"),
                categoria = o.getString("categoria"),
                pais = o.getString("pais"),
                plan = o.getString("plan"),
                precio = o.getInt("precio")
                //imagenUrl = o.getString("imagenUrl"),
                //lat = o.optDouble("lat", 0.0),
                //lon = o.optDouble("lon", 0.0)
            )
            destinations.add(destination)
        }
        return destinations
    }
}
