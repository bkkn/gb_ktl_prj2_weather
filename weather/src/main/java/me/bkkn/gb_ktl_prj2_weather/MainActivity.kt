package me.bkkn.gb_ktl_prj2_weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.bkkn.gb_ktl_prj2_weather.view.MainFragment
import me.bkkn.gb_ktl_prj2_weather.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}