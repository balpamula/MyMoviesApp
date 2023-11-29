package com.example.mymoviesapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val title: String,
    val stars: String,
    val director: String,
    val year: String,
    val image: Int,
    val storyline: String,
) : Parcelable