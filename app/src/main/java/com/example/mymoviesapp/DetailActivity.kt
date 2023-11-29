package com.example.mymoviesapp

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    companion object {
        const val key_movie = "key_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val ivDetailImage: ImageView = findViewById(R.id.iv_detail_image)
        val tvDetailTitle: TextView = findViewById(R.id.tv_detail_title)
        val tvDetailYear: TextView = findViewById(R.id.tv_detail_year)
        val tvDetailDirector: TextView = findViewById(R.id.tv_detail_director)
        val tvDetailStars: TextView = findViewById(R.id.tv_detail_stars)
        val tvDetailStoryline: TextView = findViewById(R.id.tv_detail_storyline)

        val btnShare: Button = findViewById(R.id.btn_share)

        val dataMovie = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Movie>(key_movie, Movie::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Movie>(key_movie)
        }

        if (dataMovie != null) {
            Glide.with(applicationContext)
                .load(dataMovie.image) // URL Gambar
                .into(ivDetailImage) // imageView mana yang akan diterapkan\

            val title = dataMovie.title
            tvDetailTitle.text = title
            val year = dataMovie.year
            tvDetailYear.text = year
            val director = dataMovie.director
            tvDetailDirector.text = director
            val stars = dataMovie.stars
            tvDetailStars.text = stars
            val storyline = dataMovie.storyline
            tvDetailStoryline.text = storyline
        }

        btnShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, (dataMovie?.storyline))
            startActivity(Intent.createChooser(intent, "Send To"))
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#deb522")))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}