package com.jaimeg.infomovie.view

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
import com.jaimeg.infomovie.InfoMovieApp
import com.jaimeg.infomovie.R
import com.jaimeg.infomovie.models.entities.Favorite
import com.jaimeg.infomovie.models.entities.Movie
import com.jaimeg.infomovie.views.InfoMovieActivity

class FavoritesAdapter(private val movies: List<Movie>, private val context: Context)
    : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    private val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

    fun toSeeInfoMovie( movie: Movie) {
        val intent = Intent(context, InfoMovieActivity::class.java)
        intent.putExtra("movie", movie)
        context.startActivity(intent)
    }

    fun deleteFavoriteMovie( movie: Movie) {
        val favorite = Favorite()
        favorite.favoriteMovie = movie.id
        InfoMovieApp.favoriteViewModel.deleteFavoriteDB(favorite, InfoMovieApp.database)
        val toast = Toast.makeText(context, "Borrando de favoritos", Toast.LENGTH_LONG)
        toast.show()


    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): FavoritesViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.favorite_list_item, viewGroup, false)

        return FavoritesViewHolder(view)
    }

    override fun onBindViewHolder(favoritesViewHolder: FavoritesViewHolder, position: Int) {
        favoritesViewHolder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class FavoritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewReleaseDate: TextView
        var textViewTitle: TextView
        var textViewRating: TextView
        var textViewRatingGenres: TextView
        var imageViewFavoritePoster: ImageView
        var cardViewFavorite: CardView
        var imageViewFavorite: ImageView

        init {
            textViewReleaseDate = itemView.findViewById(R.id.textView_favorite_release_date)
            textViewTitle = itemView.findViewById(R.id.textView_favorite_title)
            textViewRating = itemView.findViewById(R.id.textView_favorite_rating)
            textViewRatingGenres = itemView.findViewById(R.id.textView_favorite_genre)
            imageViewFavoritePoster = itemView.findViewById(R.id.textView_favorite_poster)
            cardViewFavorite = itemView.findViewById(R.id.cardview_favorites)
            imageViewFavorite = itemView.findViewById(R.id.imageView_favorite_favorite)

        }

        fun bind(movie: Movie) {
            textViewReleaseDate.setText(movie.releaseDate!!.split("-".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()[0])
            textViewTitle.text = movie.title
            textViewRating.text = movie.voteAverage.toString()
            textViewRatingGenres.text = ""
            cardViewFavorite.setOnClickListener { v -> toSeeInfoMovie( movie) }
            imageViewFavorite.setOnClickListener { v -> deleteFavoriteMovie(movie) }
            Glide.with(itemView)
                    .load(IMAGE_BASE_URL + movie.posterPath!!)
                    .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                    .into(imageViewFavoritePoster)


        }
    }
}
