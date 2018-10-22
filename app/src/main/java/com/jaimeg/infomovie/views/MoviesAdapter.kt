package com.jaimeg.infomovie.views

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jaimeg.infomovie.R
import com.jaimeg.infomovie.models.db.InfoMovieDB
import com.jaimeg.infomovie.models.entities.Favorite
import com.jaimeg.infomovie.models.entities.Movie
import com.jaimeg.infomovie.viewsModels.FavoriteViewModel


class MoviesAdapter(

        private val movies: MutableList<Movie>,
        private val context: Context,
        private val favoriteViewModel: FavoriteViewModel,
        private val infoMovieDB: InfoMovieDB) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {


    private val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)

        return MovieViewHolder(view)
    }

    fun toSeeInfoMovie( movie: Movie) {
        val intent = Intent(context, InfoMovieActivity::class.java)
        intent.putExtra("movie", movie)
        context.startActivity(intent)
    }

    fun addFavoriteMovie( movie: Movie) {
        val favorite = Favorite()
        favorite.favoriteMovie = movie.id
        favoriteViewModel.setFavoriteDB(favorite, infoMovieDB)
        val toast = Toast.makeText(context, "Guardado en favoritos", Toast.LENGTH_LONG)
        toast.show()
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])

    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewReleaseDate: TextView
        var textViewTitle: TextView
        var textViewRating: TextView
        var textViewGenres: TextView
        var imageViewPoster: ImageView
        var cardViewMovie: CardView
        var imageViewFavoriteMovie: ImageView

        init {
            textViewReleaseDate = itemView.findViewById(R.id.textView_movie_release_date)
            textViewTitle = itemView.findViewById(R.id.textView_movie_title)
            textViewRating = itemView.findViewById(R.id.textView_movie_rating)
            textViewGenres = itemView.findViewById(R.id.textView_movie_genre)
            imageViewPoster = itemView.findViewById(R.id.textView_movie_poster)
            cardViewMovie = itemView.findViewById(R.id.cardview_movies)
            imageViewFavoriteMovie = itemView.findViewById(R.id.imageView_favorite_movie)

        }

        fun bind(movie: Movie) {
            textViewReleaseDate.setText(movie.releaseDate!!.split("-".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()[0])
            textViewTitle.text = movie.title
            textViewRating.text = movie.voteAverage.toString()
            textViewGenres.text = ""
            cardViewMovie.setOnClickListener { v -> toSeeInfoMovie( movie) }
            imageViewFavoriteMovie.setOnClickListener { v -> addFavoriteMovie(movie) }
            Glide.with(itemView)
                    .load(IMAGE_BASE_URL + movie.posterPath!!)
                    .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                    .into(imageViewPoster)


        }
    }
}