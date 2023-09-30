package com.saif.weather_app.presenter.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import com.saif.weather_app.R
import com.saif.weather_app.base.getCurrentDateWithDay
import com.saif.weather_app.base.hideKeyboard
import com.saif.weather_app.data.model.SearchAreaResponse
import com.saif.weather_app.databinding.FragmentLocationBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class LocationFragment : Fragment(R.layout.fragment_location) {

    private val viewModel: MyLocationViewModel by viewModels()
    private var _binding: FragmentLocationBinding? = null
    private val binding get() = _binding
    private val adapter = TopRecyclerView()
    private val timelyWeatherAdapter = TimelyWeatherAdapter()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLocationBinding.bind(view)
        _binding?.apply {
            viewModel = this@LocationFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
            recyclerView1.adapter = adapter
            pastTempRv.adapter = timelyWeatherAdapter
        }
        viewModel.getInitData()



        startObservers()
        setupListener()
    }

    private fun setupLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        if (isLocationPermissionGranted()) {
            fetchCurrentLocation()
        } else {
            requestLocationPermission()
        }
    }

    // Check if location permission is granted
    private fun isLocationPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }



    private fun fetchCurrentLocation() {
        if (isLocationPermissionGranted()) {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) {
                        val latitude = location.latitude
                        val longitude = location.longitude
                        viewModel.getCurrentLocation()
                    } else {
                        Log.d("Location", "null")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.e("location", "${exception.message}")
                }
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                fetchCurrentLocation()
            } else {
                // Handle permission denied
                Snackbar.make(
                    requireView(),
                    "Location permission is required for this feature.",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

    private fun requestLocationPermission() {
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }


    private fun setupListener() {
        val calendar = Calendar.getInstance()
        val currentDateWithDay = calendar.getCurrentDateWithDay()
        binding?.tvToday?.text = currentDateWithDay
    }

    private fun startObservers() {

        viewModel.event.observe(viewLifecycleOwner) {
            when (val event = it.getContentIfNotHandled()) {

                is LocationEvents.GetTimelyWeatherUpdate -> {
                    event.list?.let { list ->
                        timelyWeatherAdapter.addItems(list)
                        sharedViewModel.setPastWeatherData(list)
                    }
                }

                is LocationEvents.OnWeatherDataLoader -> {

                }

                is LocationEvents.GetSavedLatLon -> {
                    val firstElement = event.areaResponse?.getOrNull(0)
                    if (firstElement is SearchAreaResponse && firstElement.isHandled == true) {
                        viewModel.getCurrentWeather(latitude = firstElement.lat, longitude = firstElement.lon)
                    } else {
                        setupLocation()

                    }
                }

                is LocationEvents.CurrentLatLonOnException -> {
                    setupLocation()
                }


                else -> {

                }


            }

        }

        sharedViewModel.event.observe(viewLifecycleOwner){
            when(val event = it.getContentIfNotHandled()){
                is SharedEvents.onPopBackStackAfterLocationUpdate -> {
                    hideKeyboard()
                    viewModel.getCurrentWeather(latitude = event.response?.lat, longitude = event.response?.lon)
                }

                else -> {

                }
            }
        }



    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 123
    }






}