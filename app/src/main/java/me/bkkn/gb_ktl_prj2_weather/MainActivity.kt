package me.bkkn.gb_ktl_prj2_weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.bkkn.gb_ktl_prj2_weather.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}