package com.example.rupeekchallenge

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PlacesAdapter (private val listener: NewsItemClicked) : RecyclerView.Adapter<PlaceViewHolder>() {

    private val placesList:ArrayList<Place> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)

        return PlaceViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val currPlace =placesList[position]

        holder.address.text = currPlace.address
        holder.name.text = currPlace.name

        Glide.with(holder.itemView.context).load(currPlace.imageUrl).into(holder.image)
    }

    override fun getItemCount(): Int {
        return placesList.size
    }

    fun updateNews(updatedNews: ArrayList<Place>) {
        placesList.clear()
        placesList.addAll(updatedNews)

        notifyDataSetChanged()
    }
}

class PlaceViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

    val name: TextView = itemView.findViewById<TextView>(R.id.name)
    val image: ImageView = itemView.findViewById<ImageView>(R.id.image)
    val address: TextView = itemView.findViewById<TextView>(R.id.address)
}

interface NewsItemClicked {
    fun onItemClicked(item: Place)
}