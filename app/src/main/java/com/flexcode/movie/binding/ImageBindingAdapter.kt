package com.flexcode.movie.binding


import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.flexcode.movie.util.Constants.IMAGE_BASE_URL


@BindingAdapter("android:imageUrl")
fun loadImage(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
        .load(IMAGE_BASE_URL  + url)
        .into(imageView)
}


