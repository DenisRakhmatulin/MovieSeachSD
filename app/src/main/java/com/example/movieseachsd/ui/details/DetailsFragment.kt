package com.example.movieseachsd.ui.details


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import coil.load
import com.example.movieseachsd.R
import com.example.movieseachsd.databinding.DetailsFragmentBinding
import com.example.movieseachsd.model.AppState
import com.example.movieseachsd.model.entites.Details
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {

    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Details>(BUNDLE_EXTRA)?.let {
            with(binding) {
                val id = it.movie.movie_id
                viewModel.movieLiveData.observe(viewLifecycleOwner) { appState ->
                    when (appState) {
                        is AppState.Error -> {
                            mainView.visibility = View.INVISIBLE
                            progressBar.visibility = View.GONE
                            errorTV.visibility = View.VISIBLE
                        }
                        AppState.Loading -> {
                            mainView.visibility = View.INVISIBLE
                            progressBar.visibility = View.VISIBLE
                        }
                        is AppState.Success -> {
                            progressBar.visibility = View.GONE
                            mainView.visibility = View.VISIBLE

                            movieIcon.load("https://image.tmdb.org/t/p/w500${appState.detailsData[0].movie.poster_path}") {
                                crossfade(true)
                                placeholder(R.drawable.movie_sample_pic)
                            }
                            movieTitle.text = appState.detailsData[0].movie.movie_title
                            movieOverview.text = appState.detailsData[0].overview
                            movieGenre.text = appState.detailsData[0].genre
                            movieRuntime.text = String.format(
                                getString(R.string.min),
                                appState.detailsData[0].runtime.toString()
                            )
                            movieYear.text = appState.detailsData[0].release_date
                            movieRating.text = appState.detailsData[0].movie.vote_average.toString()
                        }
                    }
                }
                if (id != null) {
                    viewModel.loadData(id)
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val BUNDLE_EXTRA = "details"

        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

}