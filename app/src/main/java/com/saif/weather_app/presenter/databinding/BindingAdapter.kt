package com.saif.weather_app.presenter.databinding

import android.os.Build
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.saif.weather_app.R
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@BindingAdapter("formattedDateTime")
fun setFormattedDateTime(view: TextView, timestamp: Long) {
    val format = "yyyy-MM-dd HH:mm:ss"
    val formattedDate = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        timestamp.toDateTime(format, null)
    } else {
        TODO("VERSION.SDK_INT < O")
    }
    view.text = formattedDate
}

@RequiresApi(Build.VERSION_CODES.O)
fun Long.toDateTime(format: String, zoneId: Int? = null): String {
    val date = Date(this)
    val language = Locale.getDefault().language
    val locale = Locale(language)
    val dateFormat = SimpleDateFormat(format, locale)
    if (zoneId != null) {
        val timeZone = TimeZone.getTimeZone(ZoneId.of(zoneId.toString()))
        dateFormat.timeZone = timeZone
    }
    return dateFormat.format(date)
}

@BindingAdapter("src")
fun setListener(image: ImageView, @DrawableRes imageRes: Int) {
    if (imageRes != 0) {
        val ImgRes = ContextCompat.getDrawable(image.context, imageRes)
        image.setImageDrawable(ImgRes)
    }
}

@BindingAdapter("dateFormat")
fun TextView.setDateWithDay(calendar: Calendar) {
    val sdf = SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH)
    text = sdf.format(calendar.time)
}

@BindingAdapter("time12HourFormat")
fun TextView.setTime12HourFormat(dateTime: String?) {
    if (dateTime != null) {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())

        try {
            val date = inputFormat.parse(dateTime)
            val formattedTime = outputFormat.format(date)
            text = formattedTime
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

@BindingAdapter("dateToDay")
fun TextView.setDateToDay(dateString: String?) {
    dateString?.let {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("EEEE", Locale.getDefault())
        val date = inputFormat.parse(dateString)
        val dayOfWeek = outputFormat.format(date)
        text = dayOfWeek
    }
}
@BindingAdapter("dateToShow")
fun TextView.setDateToShow(dateString: String?) {
    dateString?.let {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("MMMM dd", Locale.getDefault())
        val date = inputFormat.parse(dateString)
        val formattedDate = outputFormat.format(date)
        text = formattedDate
    }
}

@BindingAdapter("bind:load")
fun ImageView.load(path: String) {
    Glide.with(context).load(path)
        .placeholder(R.drawable.thumbnail)
        .transform(MultiTransformation(CenterCrop(), RoundedCorners(12)))
        .sizeMultiplier(0.5f).into(this)
}

@BindingAdapter("bind:loadDrawable")
fun ImageView.loadDrawable(drawableResId: Int) {
    Glide.with(context).load(drawableResId)
        .placeholder(R.drawable.thumbnail)
        .transform(MultiTransformation(CenterCrop(), RoundedCorners(12)))
        .sizeMultiplier(0.5f).into(this)
}

@BindingAdapter("bind:loadDrawable")
fun ImageView.loadDrawableCoil(drawableResId: Int) {
    load(drawableResId) {
        placeholder(R.drawable.thumbnail)
        error(R.drawable.baseline_error_24)
        size(50, 50) // Set your desired size here
        transformations(RoundedCornersTransformation(12f))
    }
}
