package me.bkkn.gb_ktl_prj2_weather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import me.bkkn.gb_ktl_prj2_weather.R
import me.bkkn.gb_ktl_prj2_weather.data.AppState
import me.bkkn.gb_ktl_prj2_weather.data.Weather
import me.bkkn.gb_ktl_prj2_weather.databinding.FragmentMainBinding
import me.bkkn.gb_ktl_prj2_weather.hide
import me.bkkn.gb_ktl_prj2_weather.show
import me.bkkn.gb_ktl_prj2_weather.showSnackBar
import me.bkkn.gb_ktl_prj2_weather.viewModel.MainViewModel


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private val adapter = MainFragmentAdapter(object : OnItemViewClickListener {
        override fun onItemClick(weather: Weather) {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, DetailsFragment().also { fragment ->
                    fragment.arguments =
                        Bundle().also { bundle ->
                            bundle.putParcelable(
                                DetailsFragment.BUNDLE_EXTRA,
                                weather
                            )
                        }
                })?.addToBackStack("")?.commit()
        }

    })
    private var isDataSetRus: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainFragmentRecyclerView.adapter = adapter
        binding.mainFragmentFAB.setOnClickListener { changeWeatherDataSet() }
        viewModel.liveData.observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getWeatherFromLocalSourceRus()
    }

    override fun onDestroy() {
        adapter.removeListener()
        super.onDestroy()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun changeWeatherDataSet() {
        if (isDataSetRus) {
            viewModel.getWeatherFromLocalSourceWorld()
            //binding.mainFragmentFAB.setImageResource(R.drawable.ic_earth)
        } else {
            viewModel.getWeatherFromLocalSourceRus()
            //binding.mainFragmentFAB.setImageResource(R.drawable.ic_russia)
        }
        isDataSetRus = !isDataSetRus
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.mainFragmentLoadingLayout.hide()
                // binding.mainFragmentLoadingLayout.hideIf {appState.weatherData.isNotEmpty() }
                adapter.setWeather(appState.weatherData)
            }
            is AppState.Loading -> {
                binding.mainFragmentLoadingLayout.show()
            }
            is AppState.Error -> {
                binding.mainFragmentLoadingLayout.hide()
                binding.mainFragmentFAB.showSnackBar(
                    text = getString(R.string.error),
                    actionText = getString(R.string.reload),
                    action = { viewModel.getWeatherFromLocalSourceRus() }
                )
            }
        }
    }

    interface OnItemViewClickListener {
        fun onItemClick(weather: Weather)
    }

    companion object {
        fun newInstance() =
            MainFragment()
    }
}