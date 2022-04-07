package com.flexcode.movie.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.flexcode.movie.R
import com.flexcode.movie.databinding.MovieListBinding
import com.flexcode.movie.models.Movie
import com.flexcode.movie.util.Constants.IMAGE_BASE_URL
import com.squareup.picasso.Picasso

class PopularMovieAdapter:
    RecyclerView.Adapter<PopularMovieAdapter.ViewHolder>() {

    inner class  ViewHolder(val binding: MovieListBinding) : RecyclerView.ViewHolder(binding.root)


    private val differCallBack = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem.movieId == newItem.movieId

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem == oldItem
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = MovieListBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val movie = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(IMAGE_BASE_URL + movie.poster_path)
                .into(holder.binding.ivMovieImage)
           // Picasso.get().load(IMAGE_BASE_URL + movie.poster_path).into(holder.binding.ivMovieImage)
            holder.binding.tvMovieTitle.text = movie.title
            holder.binding.tvMovieReleaseYear.text = movie.release_date
            holder.binding.tvMovieVote.text = movie.vote_average.toString()
            setOnClickListener {
                val bundle = bundleOf("movie_details" to movie)
                Navigation.findNavController(it)
                    .navigate(R.id.action_homeFragment_to_movieDetailFragment, bundle)

            }
        }

    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }

}