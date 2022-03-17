package me.bkkn.gb_ktl_prj2_movies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import me.bkkn.gb_ktl_prj2_movies.R
import me.bkkn.gb_ktl_prj2_movies.data.AppState
import me.bkkn.gb_ktl_prj2_movies.databinding.FragmentMoviesBinding
import me.bkkn.gb_ktl_prj2_movies.viewmodel.MainViewModel

class MoviesFragment : Fragment() {

    companion object {
        fun newInstance() = MoviesFragment()
    }

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val observer = Observer<AppState> {
            renderData(it)
        }
        viewModel.getLiveData().observe(viewLifecycleOwner, observer)
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {

            }
            is AppState.Loading -> {
                showLoading(true)
            }
            is AppState.Error -> {
                
            }
        }

    }

    private fun showLoading(b: Boolean) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}