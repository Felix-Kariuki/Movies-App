package com.flexcode.movie.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.flexcode.movie.Base
import com.flexcode.movie.BaseViewModel
import com.flexcode.movie.R
import com.flexcode.movie.databinding.FragmentHomeBinding
import com.flexcode.movie.viewmodels.HomeViewModel


class HomeFragment : BaseViewModel<HomeViewModel>() {


    private var _binding: FragmentHomeBinding?= null
    private val binding get() = _binding!!

    override fun getViewModel(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.getPopularMovies()


        viewModel.popularMovie.observe(viewLifecycleOwner, Observer { popularMovie ->
            popularMovie?.let {
                binding.lytHome.visibility = View.VISIBLE

                Log.d("MOVIES", popularMovie.toString())

                //binding.pagerPopularMovie.adapter = PopularPagerAdapter(popularMovie)
                //binding.pagerPopularMovie.setPageTransformer(true, DepthPageTransformer())
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
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}