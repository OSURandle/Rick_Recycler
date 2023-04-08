package com.example.rick_recycler

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RickAdapter(private val rickList: List<String>) : RecyclerView.Adapter<RickAdapter.ViewHolder>(){
    class ViewHolder(view:View) : RecyclerView.ViewHolder(view) {
         val rickImage: ImageView
         val rickName: TextView
         val rickSpecies: TextView

        init {
            rickImage = view.findViewById(R.id.rick_image)
            rickName = view.findViewById(R.id.rick_name)
            rickSpecies = view.findViewById(R.id.rick_species)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rick_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = rickList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(holder.itemView)
            .load(rickList[position])
            .centerCrop()
            .into(holder.rickImage)


    }
}