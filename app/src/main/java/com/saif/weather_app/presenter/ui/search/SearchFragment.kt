package com.saif.weather_app.presenter.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.saif.weather_app.R
import com.saif.weather_app.databinding.FragmentSearchBinding
import com.saif.weather_app.presenter.ui.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel: SearchLocationViewModel by viewModels()
    private val sharedViewModel:SharedViewModel by activityViewModels()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding
    private val adapter = SearchLocationAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)
        binding?.viewModel = viewModel
        binding?.lifecycleOwner = viewLifecycleOwner
        setupAdapter()
        viewModel.getSavedLastWeather()

        binding?.search?.setOnFocusChangeListener { view, hasfocus ->
            if (!hasfocus) {
                val enteredText = binding?.search?.text.toString()
                if (enteredText.isNotEmpty() && enteredText.trim().isNotEmpty()) {
                    viewModel.onAreaNameTextChanged(enteredText)
                    lifecycleScope.launch {
                        viewModel.getSearchLatLong()
                    }

                }
            }
        }

        setupObserver()

    }

    private fun setupObserver() {
        val action = SearchFragmentDirections.actionSearchFragmentToLocationFragment()

        viewModel.event.observe(viewLifecycleOwner) {
            when (val event = it.getContentIfNotHandled()) {
                is SearchFragmentEvents.AfterSearch -> {
                    sharedViewModel.updateShouldFetchCurrentLocationWeather(false)
                    sharedViewModel.onPopBackLocationUpdate(event.response?.getOrNull(0))
                    findNavController().navigate(action)

                }

                is SearchFragmentEvents.GetSavedWeatherLast -> {
                    event.list.let {
                        if (event.list?.isNotEmpty() == true) {
                             it?.let { it1 -> adapter.addItems(it1) }
                        }else{

                        }
                    }
                }


                else -> {}
            }
        }


    }

    private fun setupAdapter() {
        binding?.rvLocations?.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        binding?.rvLocations?.adapter = adapter
    }


}