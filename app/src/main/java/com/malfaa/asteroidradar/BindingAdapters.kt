package com.malfaa.asteroidradar

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.apply {
            setImageResource(R.drawable.ic_status_potentially_hazardous)
            contentDescription = context.getString(R.string.potentially_hazardous_asteroid_image)
        }
    } else {

        imageView.apply {
            setImageResource(R.drawable.ic_status_normal)
            contentDescription =  context.getString(R.string.not_hazardous_asteroid_image)
        }
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.apply {
            setImageResource(R.drawable.asteroid_hazardous)
            contentDescription = context.getString(R.string.potentially_hazardous_asteroid_image)
        }
    } else {
        imageView.apply { setImageResource(R.drawable.asteroid_safe)
        contentDescription = context.getString(R.string.not_hazardous_asteroid_image)
        }
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.apply {
        text = String.format(context.getString(R.string.astronomical_unit_format), number)
        contentDescription = String.format(context.getString(R.string.astronomical_unit_format), number)
    }

}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.apply {
        text = String.format(context.getString(R.string.km_unit_format), number)
        contentDescription = String.format(context.getString(R.string.km_unit_format), number)
    }
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.apply {
        text = String.format(context.getString(R.string.km_s_unit_format), number)
        contentDescription = String.format(context.getString(R.string.km_s_unit_format), number)
    }
}

@BindingAdapter("imagePictureOfDay")
fun bindPictureOfDayImageView(imageView: ImageView, imgUrl : PictureOfDay?){
    imgUrl?.let {
        if (it.mediaType == "image") {
            Picasso.with(imageView.context)
                .load(it.url)
                .into(imageView)

            val strFormat = imageView.resources.getString(
                R.string.nasa_picture_of_day_content_description_format)
            imageView.contentDescription = String.format(strFormat, it.title)
        }
    }
}
