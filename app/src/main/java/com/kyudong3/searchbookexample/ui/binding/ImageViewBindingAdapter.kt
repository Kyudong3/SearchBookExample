package com.kyudong3.searchbookexample.utils

import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


@BindingAdapter(
    "imageUrl",
    "errorImage",
    "fallBackImage",
    requireAll = false
)
fun AppCompatImageView.setImageView(
    uri: String?,
    errorImage: Drawable?,
    fallBackImage: Drawable?
) {
    Glide.with(context)
        .load(uri)
        .error(errorImage)
        .fallback(fallBackImage)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .into(this)
}

@BindingAdapter("tint")
fun AppCompatImageView.setImageTint(@ColorInt color: Int) {
    setColorFilter(color)
}
