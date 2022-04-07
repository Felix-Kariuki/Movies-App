package com.flexcode.movie.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.flexcode.movie.R
import com.flexcode.movie.adapters.GenreAdapter
import com.flexcode.movie.adapters.TrailerAdapter
import com.flexcode.movie.databinding.FragmentMovieDetailBinding
import com.flexcode.movie.models.Movie
import com.flexcode.movie.util.Constants
import com.flexcode.movie.util.Constants.IMAGE_BASE_URL
import com.flexcode.movie.util.Constants.YOUTUBE_URL
import com.flexcode.movie.viewmodels.DetailViewModel


class MovieDetailFragment : Fragment() {

    private var binding: FragmentMovieDetailBinding?=null

    private  val  viewModel:DetailViewModel by viewModels()
    private  var movie: Movie? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater,container,false)

        val view = binding!!.root

        arguments?.let {
            movie = it?.getParcelable("movie_details")

            binding!!.tvMovieTitle.text = movie?.title
            Glide.with(this).load(IMAGE_BASE_URL + movie?.poster_path)
                .into(binding!!.ivMovieBg)
            Glide.with(this).load(IMAGE_BASE_URL + movie?.poster_path)
                .into(binding!!.imgBackgorund)
            binding!!.tvReleaseDate.text = movie?.release_date
            binding!!.tvVote.text = movie?.vote_count.toString()
            binding!!.tvMovieRate.text = movie?.vote_average.toString()
            binding!!.tvDescription.text = movie?.overview.toString()

            //see the details
            Log.d("MOVIEDETAILS ", movie.toString())

            binding!!.ivFavourite.setOnClickListener {
                favourite()
            }

            val movieDetailResponse = arguments?.getParcelable<Movie>("movie_details")

            viewModel.getMovieDetails(movieDetailResponse?.movieId!!)

            viewModel.movieDetails.observe(viewLifecycleOwner, Observer {
                it?.let {
                    //dataBinding.content = it

                    val genreAdapter =
                        GenreAdapter(it.genres!!)
                    binding!!.recyclerviewGenres.adapter = genreAdapter

                }
            })

            viewModel.getTrailers(movieDetailResponse.movieId)

            viewModel.movieTrailers.observe(viewLifecycleOwner, Observer{
                it?.let {
                   binding!!.recyclerviewTrailer.adapter =
                        TrailerAdapter(
                            it
                        ) {
                            val intent = Intent(Intent.ACTION_VIEW)
                            intent.data = Uri.parse(YOUTUBE_URL + it.key)
                            startActivity(intent)
                        }
                }
            })

            viewModel.loading.observe(viewLifecycleOwner, Observer {
                it?.let {
                    if (it) {
                        binding!!.progressBarTrailer.visibility = View.VISIBLE
                        binding!!.recyclerviewTrailer.visibility = View.GONE
                        binding!!.progressBarDetail.visibility = View.VISIBLE
                        binding!!.lytDetail.visibility = View.GONE
                    } else {
                        binding!!.progressBarTrailer.visibility = View.GONE
                        binding!!.recyclerviewTrailer.visibility = View.VISIBLE
                        binding!!.progressBarDetail.visibility = View.GONE
                        binding!!.lytDetail.visibility = View.VISIBLE
                    }
                }
            })
        }


        return view

    }

    private fun favourite() {

    }


}