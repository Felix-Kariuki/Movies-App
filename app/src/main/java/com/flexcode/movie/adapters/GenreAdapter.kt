package com.flexcode.movie.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flexcode.movie.databinding.GenreItemBinding
import com.flexcode.movie.models.MovieGenre

class GenreAdapter(
    var genreList: List<MovieGenre>
) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {
    class GenreViewHolder(val binding: GenreItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = GenreItemBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return GenreViewHolder(view)
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.binding.tvGenre.text = genreList[position].name.toString()
    }

}