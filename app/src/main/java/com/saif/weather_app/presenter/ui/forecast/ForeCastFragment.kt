package com.saif.weather_app.presenter.ui.forecast

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.saif.weather_app.R
import com.saif.weather_app.base.getCurrentDate
import com.saif.weather_app.base.getCurrentDateWithDay
import com.saif.weather_app.data.model.Response
import com.saif.weather_app.databinding.FragmentForeCastBinding
import com.saif.weather_app.databinding.FragmentLocationBinding
import com.saif.weather_app.presenter.ui.MyLocationViewModel
import com.saif.weather_app.presenter.ui.SharedViewModel
import com.saif.weather_app.presenter.ui.TimelyWeatherAdapter
import com.saif.weather_app.presenter.ui.TopRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@AndroidEntryPoint
class ForeCastFragment : Fragment(R.layout.fragment_fore_cast) {


    private var _binding: FragmentForeCastBinding? = null
    private val binding get() = _binding
    private val viewModel: ForeCastViewModel by viewModels()
    private val timelyWeatherAdapter = TimelyWeatherAdapter()
    private val nextForeCastAdapter = NextForeCastAdapter()

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentForeCastBinding.bind(view)
        binding?.viewModel = viewModel
        binding?.lifecycleOwner = viewLifecycleOwner
        binding?.recyclerViewTodayWeather?.adapter = timelyWeatherAdapter
        binding?.recyclerViewNextForecast?.adapter = nextForeCastAdapter

        startObservers()
        setupListener()

    }

    private fun setupListener() {
        val calendar = Calendar.getInstance()
        val currentDateWithDay = calendar.getCurrentDateWithDay()
        binding?.tvTodayDate?.text = currentDateWithDay
    }

    private fun startObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            sharedViewModel.pastWeather.collectLatest { weatherList ->

                timelyWeatherAdapter.addItems(weatherList)

                val filteredList = filterDataForNextDays(weatherList,6)
                Log.d("forecastFragment","$filteredList")
                nextForeCastAdapter.addItems(filteredList)

            }
        }

    }



    private fun filterDataForNextDays(weatherList: List<Response.Data?>, numDays: Int): List<Response.Data?> {
        val filteredList = mutableListOf<Response.Data?>()

        for (i in 1..numDays) {
            val nextDayDate = Calendar.getInstance().apply {
                add(Calendar.DAY_OF_MONTH, i) // Add i days to the current date
            }.getCurrentDate()

            val dataForNextDay = weatherList.find { weatherItem ->
                weatherItem?.dtTxt?.startsWith(nextDayDate) ?: false
            }

            dataForNextDay?.let {
                filteredList.add(it)
            }
        }

        return filteredList
    }


}