package com.tmdbandroid.view

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.tmdbandroid.R

@BindingAdapter("urlImage")
fun bindUrlImage(view: ImageView, poster_path: String?) {
    if (poster_path != null) {
        Picasso.get()
            .load(com.tmdbandroid.BuildConfig.BASE_URL_IMAGE + poster_path)
            .placeholder(R.drawable.ic_launcher_foreground)
            .fit()
            .into(view)
    } else {
        view.setImageBitmap(null)
    }

}

@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: String?) {
    view.visibility = if (value.isNullOrEmpty()) View.GONE else View.VISIBLE
}