package com.jaimeg.infomovie.views


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.jaimeg.infomovie.InfoMovieApp
import com.jaimeg.infomovie.R
import com.jaimeg.infomovie.models.api.ImpGetMoviesCallback
import com.jaimeg.infomovie.models.entities.Movie
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: MoviesAdapter
    lateinit var movieList: MutableList<Movie>
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout_main)
        swipeRefreshLayout.setOnRefreshListener { initializer() }

        initializer()
    }

    fun initializer(){

        movieList = ArrayList()
        getMovies()

    }

    fun getMovies() {
        doAsync {
            InfoMovieApp.movieViewModel.fetchMoviesApi(
                    ImpGetMoviesCallback(InfoMovieApp.movieViewModel, this@MainActivity, InfoMovieApp.database, InfoMovieApp.movieRepo),
                    InfoMovieApp.movieRepo,
                    InfoMovieApp.impNetwork)

            movieList = InfoMovieApp.database.movieDao().getAllMovies()


            uiThread {
                setUpRecyclerView(movieList)
            }
        }
    }

    private fun setUpRecyclerView(moviesList: MutableList<Movie>) {

        adapter = MoviesAdapter(moviesList,this,InfoMovieApp.favoriteViewModel, InfoMovieApp.database)
        recyclerView = findViewById(R.id.rvMovies)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }



}
