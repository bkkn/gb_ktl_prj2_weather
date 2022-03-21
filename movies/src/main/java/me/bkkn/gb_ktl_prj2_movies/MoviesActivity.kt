package me.bkkn.gb_ktl_prj2_movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.bkkn.gb_ktl_prj2_movies.view.MoviesFragment

class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_movies, MoviesFragment.newInstance())
                .commitNow()
        }
    }
}