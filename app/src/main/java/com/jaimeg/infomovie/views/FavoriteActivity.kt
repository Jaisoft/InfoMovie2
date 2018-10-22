package com.jaimeg.infomovie.views

import android.content.Context
import android.os.AsyncTask
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.jaimeg.infomovie.InfoMovieApp
import com.jaimeg.infomovie.R
import com.jaimeg.infomovie.models.entities.Favorite
import com.jaimeg.infomovie.models.entities.Movie
import com.jaimeg.infomovie.view.FavoritesAdapter
import java.util.ArrayList

class FavoriteActivity : AppCompatActivity() {
    lateinit var recyclerViewFavorite: RecyclerView
    lateinit var swipeRefreshLayout: SwipeRefreshLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        recyclerViewFavorite = findViewById(R.id.favorite_list)
        recyclerViewFavorite.layoutManager = LinearLayoutManager(this)
        getFavoritesMovies()
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener { getFavoritesMovies() }
    }

    fun getFavoritesMovies() {
        GetFavoritesDbAsync(baseContext).execute()
    }

    private inner class GetFavoritesDbAsync internal constructor(
            internal var context: Context) : AsyncTask<Void, Void, MutableList<Favorite>>() {

        override fun doInBackground(vararg params: Void): MutableList<Favorite> {

            return InfoMovieApp.favoriteViewModel.getFavoriteDB(InfoMovieApp.database)
        }

        override fun onPostExecute(result: MutableList<Favorite>) {
            super.onPostExecute(result)
            GetMoviesDbAsync(context, result).execute()
        }
    }

    private inner class GetMoviesDbAsync internal constructor(
            internal var context: Context,
            internal var favorites: MutableList<Favorite>) : AsyncTask<Void, Void, MutableList<Movie>>() {

        override fun doInBackground(vararg args: Void): MutableList<Movie> {
            var idsMovies: MutableList<Int>  = ArrayList()
            for (i in favorites) {
                idsMovies.add(i.favoriteMovie!!)
            }
            return getMovies(idsMovies)
        }

        override fun onPostExecute(result: MutableList<Movie>) {
            super.onPostExecute(result)


            val adapter = FavoritesAdapter(result, context)
            recyclerViewFavorite.adapter = adapter
            swipeRefreshLayout.isRefreshing = false
        }
    }

    fun getMovies(idsList: MutableList<Int>): MutableList<Movie> {
        return InfoMovieApp.movieViewModel.getMoviesFromIdentifiers(InfoMovieApp.database, idsList,InfoMovieApp.movieRepo)
    }


}
