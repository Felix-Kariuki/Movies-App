package com.flexcode.movie.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.flexcode.movie.R
import com.flexcode.movie.adapters.FavouriteAdapter
import com.flexcode.movie.databinding.FragmentFavouriteBinding
import com.flexcode.movie.viewmodels.DetailViewModel


class FavouriteFragment : Fragment() {

    private var _binding: FragmentFavouriteBinding?= null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteBinding.inflate(inflater,container,false)


        viewModel.getAllMovies().observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.rvFavorites.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.rvFavorites.adapter = FavouriteAdapter(it) {
                    val bundle = bundleOf("movie_details" to it)
                    Navigation.findNavController(requireView())
                        .navigate(R.id.action_favouriteFragment_to_movieDetailFragment, bundle)
                }
            }

        })

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}