package com.jaimeg.infomovie.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jaimeg.infomovie.R
import com.jaimeg.infomovie.models.entities.Movie


class InfoMovieActivity : AppCompatActivity() {

    private val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    internal lateinit var textViewOverview: TextView
    internal lateinit var imageViewPoster: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_movie)
        textViewOverview = findViewById(R.id.textView_overview)
        imageViewPoster = findViewById(R.id.textView_info_movie_poster)
        val movie = intent.extras!!.getSerializable("movie") as Movie
        textViewOverview.append(movie.overview)
        Glide.with(this)
                .load(IMAGE_BASE_URL + movie.posterPath!!)
                .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                .into(imageViewPoster)

    }
}