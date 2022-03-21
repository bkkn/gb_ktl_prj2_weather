package me.bkkn.gb_ktl_prj2_movies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import me.bkkn.gb_ktl_prj2_movies.R
import me.bkkn.gb_ktl_prj2_movies.data.AppState
import me.bkkn.gb_ktl_prj2_movies.data.Film
import me.bkkn.gb_ktl_prj2_movies.databinding.FragmentMoviesBinding
import me.bkkn.gb_ktl_prj2_movies.viewmodel.MainViewModel
import java.security.cert.CRLException

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
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val observer = Observer<AppState> {
            renderData(it)
        }
        viewModel.getLiveData().observe(viewLifecycleOwner, observer)
        viewModel.getFilmFromLocalSource()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                showLoading(false)
                val filmData = appState.film
                setData(filmData)
            }
            is AppState.Loading -> {
                showLoading(true)
            }
            is AppState.Error -> {
                showLoading(false)
                binding.mainView.createAndShow(
                    R.string.snackbar_text_error,
                    R.string.action_text_reload,
                    { viewModel.getFilmFromLocalSource() })
            }
        }
    }

    private fun View.createAndShow(
        text: String, actionText: String, action: (View) -> Unit,
        length: Int = Snackbar.LENGTH_INDEFINITE
    ) {
        Snackbar.make(this, text, length).setAction(actionText, action).show()
    }

    private fun View.createAndShow(
        textId: Int, actionTextId: Int, action: (View) -> Unit,
        length: Int = Snackbar.LENGTH_INDEFINITE
    ) {
        Snackbar.make(this, getString(textId), length).setAction(actionTextId, action).show()
    }

    private fun setData(filmData: Film) {
        with(binding) {
            filmGenre.text = filmData.genre
            filmName.text = filmData.title
            filmDescription.text = filmData.description
        }
    }

    private fun showLoading(isShow: Boolean) {
        with(binding) {
            loadingLayout.isVisible = isShow
            mainView.isVisible = !isShow
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}