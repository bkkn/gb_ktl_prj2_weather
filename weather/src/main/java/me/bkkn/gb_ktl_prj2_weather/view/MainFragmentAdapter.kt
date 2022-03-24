package me.bkkn.gb_ktl_prj2_weather.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import me.bkkn.gb_ktl_prj2_weather.R
import me.bkkn.gb_ktl_prj2_weather.data.Weather


class MainFragmentAdapter(private var onItemViewClickListener: MainFragment.OnItemViewClickListener?)  :
    RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {

    private var weatherData: List<Weather> = listOf()

    fun setWeather(data: List<Weather>) {
        weatherData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_city, parent, false) as View
        )
    }

    fun removeListener() {
        onItemViewClickListener = null
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(weatherData[position])
    }

    override fun getItemCount() = weatherData.size

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(weather: Weather) {
            itemView.apply {
                findViewById<TextView>(R.id.mainFragmentRecyclerItemTextView).text = weather.city?.city
                setOnClickListener {
                    onItemViewClickListener?.onItemClick(weather)
                }
            }

        }
    }
}
