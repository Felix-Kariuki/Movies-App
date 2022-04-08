package com.flexcode.movie.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.flexcode.movie.R
import com.flexcode.movie.databinding.MovieListBinding
import com.flexcode.movie.databinding.UpcomingMovieItemBinding
import com.flexcode.movie.models.Movie
import com.flexcode.movie.util.Constants
import com.squareup.picasso.Picasso

class UpcomingMovieAdapter : RecyclerView.Adapter<UpcomingMovieAdapter.UpcomingViewHolder>() {
    inner class UpcomingViewHolder(val binding: UpcomingMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem.poster_path == newItem.poster_path

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem == oldItem
    }

    val differ = AsyncListDiffer(this, differCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        val view = UpcomingMovieItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UpcomingViewHolder(view)
    }

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        val upcoming = differ.currentList[position]

        holder.itemView.apply {
            holder.binding.tvMovieTitle.text = upcoming.title
            Picasso.get().load(Constants.IMAGE_BASE_URL + upcoming.poster_path)
                .into(holder.binding.ivMovieImage)
            //Glide.with(this).load(Constants.IMAGE_BASE_URL + upcoming.poster_path).into(holder.binding.ivMovieImage)
            holder.binding.tvMovieTitle.text = upcoming.title
            setOnClickListener {
                val bundle = bundleOf("movie_details" to upcoming)
                Navigation.findNavController(it)
                    .navigate(R.id.action_homeFragment_to_movieDetailFragment, bundle)

            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}