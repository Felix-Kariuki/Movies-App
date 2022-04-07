package com.flexcode.movie.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flexcode.movie.databinding.TrailerLayoutItemBinding
import com.flexcode.movie.models.MovieVideo

class TrailerAdapter(
    var trailerList: List<MovieVideo>,
    private val trailerOnClick: (MovieVideo) -> Unit
) : RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder>() {

    class TrailerViewHolder(val binding: TrailerLayoutItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(trailer: MovieVideo, trailerOnClick: (MovieVideo) -> Unit) {
            itemView.setOnClickListener { trailerOnClick(trailer) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {

        val view = TrailerLayoutItemBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return TrailerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return trailerList.size
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        holder.binding.tvTrailerTitle.text = trailerList[position].name
        holder.bind(trailerList[position], trailerOnClick)
    }
}