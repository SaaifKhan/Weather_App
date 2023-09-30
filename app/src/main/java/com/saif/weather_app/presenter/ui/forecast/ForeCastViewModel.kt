package com.saif.weather_app.presenter.ui.forecast

import androidx.lifecycle.ViewModel
import com.saif.weather_app.data.model.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ForeCastViewModel @Inject constructor() :ViewModel() {
    var mainList = mutableListOf<Response.Data?>()



    // Function to convert MutableList<Response.Data?> to List<Map<String, Any>>
     fun convertToMapList(dataList: MutableList<Response.Data?>): MutableList<Map<String, String?>> {
        val mapList = mutableListOf<Map<String, String?>>()
        for (data in dataList) {
            data?.let {
                val map = mapOf(
                    "dt_txt" to it.dtTxt // Replace 'dt_txt' with the actual property name for date in 'Response.Data'
                    // Add other properties as needed
                )
                mapList.add(map)
            }
        }
        return mapList
    }


    // Function to filter weather data for today's date
    fun filterWeatherDataForToday(apiResponse: List<Map<String, Any>>): List<Map<String, Any>> {
        // Step 1: Get today's date in the desired format (yyyy-MM-dd)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val todayDate = dateFormat.format(Date())

        // Step 2: Filter out items with a date other than today's date
        return apiResponse.filter { item ->
            val itemDate = item["dt_txt"] as? String
            val itemDateFormatted = itemDate?.substring(0, 10)
            itemDateFormatted == todayDate
        }
    }

}