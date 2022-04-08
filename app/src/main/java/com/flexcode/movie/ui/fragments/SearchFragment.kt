package com.flexcode.movie.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.flexcode.movie.R
import com.flexcode.movie.adapters.SearchAdapter
import com.flexcode.movie.databinding.FragmentSearchBinding
import com.flexcode.movie.viewmodels.SearchViewModel


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater,container,false)

        val view = binding.root

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (p0 != null && p0.isNotEmpty()){
                    viewModel.getSearchMovies(p0)

                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
               return false
            }

        })

        viewModel.searchMovies.observe(viewLifecycleOwner, Observer {searchMovie ->
            searchMovie?.let {
                binding.recyclerviewSearch.layoutManager = LinearLayoutManager(requireContext())
                binding.recyclerviewSearch.adapter = SearchAdapter(searchMovie)
            }

        })

        return view
    }

}