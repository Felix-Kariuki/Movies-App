package com.flexcode.movie.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.flexcode.movie.databinding.SavedMovieItemBinding
import com.flexcode.movie.models.Movie
import com.flexcode.movie.util.Constants

class FavouriteAdapter(
    private val movieDetailList: List<Movie>,
    private val onItemClick: (Movie) -> Unit
) :
    RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>() {
    class FavouriteViewHolder(var binding: SavedMovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Movie, onItemClick: (Movie) -> Unit) {
            itemView.setOnClickListener { onItemClick(model) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val view = SavedMovieItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return FavouriteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieDetailList.size
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.itemView.apply {
            holder.binding.tvFavouriteTitle.text = movieDetailList[position].title.toString()
            Glide.with(this).load(Constants.IMAGE_BASE_URL + movieDetailList[position].poster_path)
                .into(holder.binding.ivFavouriteImg)
        }

        holder.bind(movieDetailList[position], onItemClick)
    }
}