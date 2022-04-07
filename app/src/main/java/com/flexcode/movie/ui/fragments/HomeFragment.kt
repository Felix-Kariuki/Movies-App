package com.flexcode.movie.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flexcode.movie.adapters.PopularMovieAdapter
import com.flexcode.movie.adapters.UpcomingMovieAdapter
import com.flexcode.movie.databinding.FragmentHomeBinding
import com.flexcode.movie.util.Resource
import com.flexcode.movie.viewmodels.HomeViewModel


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding?= null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var popularMovieAdapter: PopularMovieAdapter
    lateinit var upcomingMovieAdapter: UpcomingMovieAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)

        viewModel.getPopularMovies()
        viewModel.getUpcomingMovies()

        setUpRecyclerView()

        viewModel.popularMovie.observe(viewLifecycleOwner, Observer { popularMovie ->
            popularMovie?.let { movies ->
                binding.lytHome.visibility = View.VISIBLE

                popularMovieAdapter.differ.submitList(movies)
                //Log.d("MOVIES", popularMovie.toString())

            }
        })

        viewModel.upcomingMovie.observe(viewLifecycleOwner, Observer { upcomingMovie ->
            upcomingMovie?.let { upcoming ->
                binding.lytHome.visibility = View.VISIBLE

                upcomingMovieAdapter.differ.submitList(upcoming)

            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { loadingMovies ->
            loadingMovies?.let {
                if (it){
                    binding.homeProgressBar.visibility = View.VISIBLE
                    binding.lytHome.visibility = View.GONE


                }else{
                    binding.homeProgressBar.visibility = View.GONE
                }
            }
        })

        return binding.root
    }

    private fun setUpRecyclerView() {
        popularMovieAdapter = PopularMovieAdapter()
        upcomingMovieAdapter = UpcomingMovieAdapter()
        binding.rvPopularMovies.apply {
            adapter = popularMovieAdapter
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        }
        binding.rvUpcomingMovies.apply {
            adapter = upcomingMovieAdapter
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}