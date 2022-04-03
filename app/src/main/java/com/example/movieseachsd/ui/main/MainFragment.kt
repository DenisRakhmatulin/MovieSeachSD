package com.example.movieseachsd.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.movieseachsd.R
import com.example.movieseachsd.databinding.MainFragmentBinding
import com.example.movieseachsd.model.AppState
import com.example.movieseachsd.model.entites.Details
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val observer = Observer<AppState> { renderData(it) }
        viewModel.liveData.observe(viewLifecycleOwner, observer)
        viewModel.getDetails()
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                val detailsData = appState.detailsData
                progressBar.visibility = View.GONE
                mainFragmentRecyclerView.visibility = View.VISIBLE
                setData(detailsData)
            }
            is AppState.Loading -> {
                progressBar.visibility = View.VISIBLE
                mainFragmentRecyclerView.visibility = View.INVISIBLE
            }
            is AppState.Error -> {
                progressBar.visibility = View.GONE
                mainFragmentRecyclerView.visibility = View.INVISIBLE
                Snackbar
                    .make(mainView, "Error", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload") {viewModel.getDetails()}
                    .show()
            }

        }

    }

    private fun setData(detailsData: Details) = with(binding){

        TODO() //тут сетим данные во вьюхи

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        fun newInstance() = MainFragment()
    }

}