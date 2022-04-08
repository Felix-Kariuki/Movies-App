package com.flexcode.movie.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.flexcode.movie.databinding.MovieListBinding
import com.flexcode.movie.models.MovieSearch
import com.flexcode.movie.util.Constants.IMAGE_BASE_URL

class SearchAdapter(
    private val searchList: List<MovieSearch>) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    class SearchViewHolder(val binding: MovieListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = MovieListBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return SearchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.itemView.apply {
            holder.binding.tvMovieTitle.text = searchList[position].title
            holder.binding.tvMovieReleaseYear.text = searchList[position].release_date
            holder.binding.tvMovieVote.text = searchList[position].vote_average
            Glide.with(this).load(IMAGE_BASE_URL + searchList[position].poster_path)
                .into(holder.binding.ivMovieImage)
        }
    }
}