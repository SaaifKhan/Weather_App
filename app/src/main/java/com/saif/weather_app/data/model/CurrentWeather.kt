package com.saif.weather_app.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity
data class CurrentWeather(
    @PrimaryKey
    @Json(name = "id")
    var id: Int? = null,
    @Json(name = "base")
    var base: String? = null,
    @Json(name = "clouds")
    var clouds: Clouds? = null,
    @Json(name = "cod")
    var cod: Int? = null,
    @Json(name = "coord")
    var coord: Coord? = null,
    @Json(name = "dt")
    var dt: Int? = null,
    @Json(name = "main")
    var main: Main? = null,
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "sys")
    var sys: Sys? = null,
    @Json(name = "timezone")
    var timezone: Int? = null,
    @Json(name = "visibility")
    var visibility: Int? = null,
    @Json(name = "weather")
    var weather: List<Weather?>? = null,
    @Json(name = "wind")
    var wind: Wind? = null,
) {
    @JsonClass(generateAdapter = true)
    data class Clouds(
        @Json(name = "all")
        var all: Int? = null
    )

    @JsonClass(generateAdapter = true)
    data class Coord(
        @Json(name = "lat")
        var lat: Double? = null,
        @Json(name = "lon")
        var lon: Double? = null
    )

    @JsonClass(generateAdapter = true)
    data class Main(
        @Json(name = "feels_like")
        var feelsLike: Double? = null,
        @Json(name = "humidity")
        var humidity: Int? = null,
        @Json(name = "pressure")
        var pressure: Int? = null,
        @Json(name = "temp")
        var temp: Double? = null,
        @Json(name = "temp_max")
        var tempMax: Double? = null,
        @Json(name = "temp_min")
        var tempMin: Double? = null
    )

    @JsonClass(generateAdapter = true)
    data class Sys(
        @Json(name = "country")
        var country: String? = null,
        @Json(name = "id")
        var id: Int? = null,
        @Json(name = "sunrise")
        var sunrise: Int? = null,
        @Json(name = "sunset")
        var sunset: Int? = null,
        @Json(name = "type")
        var type: Int? = null
    )

    @JsonClass(generateAdapter = true)
    data class Weather(
        @Json(name = "description")
        var description: String? = null,
        @Json(name = "icon")
        var icon: String? = null,
        @Json(name = "id")
        var id: Int? = null,
        @Json(name = "main")
        var main: String? = null
    )

    @JsonClass(generateAdapter = true)
    data class Wind(
        @Json(name = "deg")
        var deg: Int? = null,
        @Json(name = "speed")
        var speed: Double? = null
    )
}