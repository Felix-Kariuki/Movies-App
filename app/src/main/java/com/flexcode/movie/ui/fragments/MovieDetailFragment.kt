package com.flexcode.movie.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.flexcode.movie.R
import com.flexcode.movie.adapters.GenreAdapter
import com.flexcode.movie.adapters.TrailerAdapter
import com.flexcode.movie.databinding.FragmentMovieDetailBinding
import com.flexcode.movie.models.Movie
import com.flexcode.movie.util.Constants.IMAGE_BASE_URL
import com.flexcode.movie.util.Constants.YOUTUBE_URL
import com.flexcode.movie.util.showToast
import com.flexcode.movie.viewmodels.DetailViewModel


class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() =  _binding!!
    private val viewModel: DetailViewModel by viewModels()
    val args: MovieDetailFragmentArgs by navArgs()

    private var movie: Movie? = null
    private var isFavourite: Boolean? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)

        val view = binding.root

        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_movieDetailFragment_to_homeFragment)
        }

        val movieDetails = args.movieDetails

        arguments?.let {
            movie = args.movieDetails

            binding.tvMovieTitle.text = movie?.title
            Glide.with(this).load(IMAGE_BASE_URL + movie?.poster_path)
                .into(binding.imgBackgorund)
            binding.tvReleaseDate.text = movie?.release_date
            binding.tvVote.text = movie?.vote_count.toString()
            binding.tvMovieRate.text = movie?.vote_average.toString()
            binding.tvDescription.text = movie?.overview.toString()

            //check if favourite
            isSaved()
            binding.ivFavourite.setOnClickListener {
                save()
            }

            val movieDetailResponse = args.movieDetails
            Log.d("RESPONSE" ,"$movieDetailResponse")

            viewModel.getMovieDetails(movieDetailResponse?.movieId!!)

            viewModel.movieDetails.observe(viewLifecycleOwner, Observer {
                it?.let {
                    val genreAdapter =
                        GenreAdapter(it.genres!!)
                    binding.recyclerviewGenres.adapter = genreAdapter

                }
            })

            viewModel.getTrailers(movieDetailResponse.movieId)

            viewModel.movieTrailers.observe(viewLifecycleOwner, Observer {
                it?.let {
                    binding.recyclerviewTrailer.adapter = TrailerAdapter(it) {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(YOUTUBE_URL + it.key)
                        startActivity(intent)
                    }
                }
            })

            viewModel.loading.observe(viewLifecycleOwner, Observer {
                it?.let {
                    if (it) {
                        binding.progressBarTrailer.visibility = View.VISIBLE
                        binding.recyclerviewTrailer.visibility = View.GONE
                        binding.progressBarDetail.visibility = View.VISIBLE
                        binding.lytDetail.visibility = View.GONE
                    } else {
                        binding.progressBarTrailer.visibility = View.GONE
                        binding.recyclerviewTrailer.visibility = View.VISIBLE
                        binding.progressBarDetail.visibility = View.GONE
                        binding.lytDetail.visibility = View.VISIBLE
                    }
                }
            })
        }


        return view

    }

    private fun isSaved() {
        viewModel.getSingleMovie(movie?.movieId!!).observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.ivFavourite.setImageResource(R.drawable.ic_favorite)
                isFavourite = true
            } else {
                binding.ivFavourite.setImageResource(R.drawable.ic_favorite_border)
                isFavourite = false
            }
        })
    }

    private fun save() {
        if (isFavourite != true){
            viewModel.insertMovie(movie!!)
            showToast("Added to favourites")
        }else {
            viewModel.deleteMovie(movie!!)
            showToast("Removed")
        }
    }

}