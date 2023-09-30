package com.saif.weather_app.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Response(
    @Json(name = "city")
    var city: City? = null,
    @Json(name = "cnt")
    var cnt: Int? = null,
    @Json(name = "cod")
    var cod: String? = null,
    @Json(name = "list")
    var list_data: List<Data>? = null,
    @Json(name = "message")
    var message: Int? = null
) {
    @JsonClass(generateAdapter = true)
    data class City(
        @Json(name = "coord")
        var coord: Coord? = null,
        @Json(name = "country")
        var country: String? = null,
        @Json(name = "id")
        var id: Int? = null,
        @Json(name = "name")
        var name: String? = null,
        @Json(name = "population")
        var population: Int? = null,
        @Json(name = "sunrise")
        var sunrise: Int? = null,
        @Json(name = "sunset")
        var sunset: Int? = null,
        @Json(name = "timezone")
        var timezone: Int? = null
    ) {
        @JsonClass(generateAdapter = true)
        data class Coord(
            @Json(name = "lat")
            var lat: Double? = null,
            @Json(name = "lon")
            var lon: Double? = null
        )
    }

    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "clouds")
        var clouds: Clouds? = null,
        @Json(name = "dt")
        var dt: Int? = null,
        @Json(name = "dt_txt")
        var dtTxt: String? = null,
        @Json(name = "main")
        var main: Main? = null,
        @Json(name = "sys")
        var sys: Sys? = null,
        @Json(name = "visibility")
        var visibility: Int? = null,
        @Json(name = "weather")
        var weather: List<Weather?>? = null,
        @Json(name = "wind")
        var wind: Wind? = null
    ) {
        @JsonClass(generateAdapter = true)
        data class Clouds(
            @Json(name = "all")
            var all: Int? = null
        )

        @JsonClass(generateAdapter = true)
        data class Main(
            @Json(name = "feels_like")
            var feelsLike: Double? = null,
            @Json(name = "grnd_level")
            var grndLevel: Int? = null,
            @Json(name = "humidity")
            var humidity: Int? = null,
            @Json(name = "pressure")
            var pressure: Int? = null,
            @Json(name = "sea_level")
            var seaLevel: Int? = null,
            @Json(name = "temp")
            var temp: Double? = null,
            @Json(name = "temp_kf")
            var tempKf: Double? = null,
            @Json(name = "temp_max")
            var tempMax: Double? = null,
            @Json(name = "temp_min")
            var tempMin: Double? = null
        )

        @JsonClass(generateAdapter = true)
        data class Sys(
            @Json(name = "pod")
            var pod: String? = null
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
            @Json(name = "gust")
            var gust: Double? = null,
            @Json(name = "speed")
            var speed: Double? = null
        )
    }
}