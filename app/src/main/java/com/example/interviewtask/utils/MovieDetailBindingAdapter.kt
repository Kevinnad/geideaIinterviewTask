package com.example.interviewtask.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

class MovieDetailBindingAdapter {


    companion object {
        @JvmStatic
        @BindingAdapter("setImage")
        fun setImage(view: ImageView, imagePath: String?) {
            imagePath?.let {
                Picasso.get().load(imagePath).into(view)
            }
        }
    }
}