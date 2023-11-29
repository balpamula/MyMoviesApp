package com.example.mymoviesapp

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvMovies: RecyclerView
    private val list = ArrayList<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMovies = findViewById(R.id.rv_movies)
        rvMovies.setHasFixedSize(true)

        list.addAll(getListMovies())
        showRecyclerList()

        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#deb522")))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list ->{
                val moveIntentActivity = Intent(this@MainActivity, about_page::class.java)
                startActivity(moveIntentActivity)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListMovies(): ArrayList<Movie> {
        val dataTitle = resources.getStringArray(R.array.data_title)
        val dataStars = resources.getStringArray(R.array.data_stars)
        val dataDirector = resources.getStringArray(R.array.data_director)
        val dataYear = resources.getStringArray(R.array.data_year)
        val dataImage = resources.obtainTypedArray(R.array.movie_image)
        val dataStoryline = resources.getStringArray(R.array.data_storyline)
        val listMovie = ArrayList<Movie>()
        for (i in dataTitle.indices) {
            val movie = Movie(dataTitle[i], dataStars[i], dataDirector[i], dataYear[i], dataImage.getResourceId(i, -1), dataStoryline[i])
            listMovie.add(movie)
        }
        return listMovie
    }

    private fun showRecyclerList() {
        rvMovies.layoutManager = LinearLayoutManager(this)
        val listMovieAdapter = ListMovieAdapter(list)
        rvMovies.adapter = listMovieAdapter
    }
}