package com.example.movieseachsd.ui.main

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
import com.example.movieseachsd.ui.adapters.MainFragmentAdapter
import com.example.movieseachsd.ui.details.DetailsFragment
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private var adapter: MainFragmentAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            mainFragmentRecyclerView.adapter = adapter
            val observer = Observer<AppState> { renderData(it) }
            viewModel.liveData.observe(viewLifecycleOwner, observer)
            viewModel.getDetailsFromLocalSource()
        }
    }

    private fun renderData(appState: AppState) = with(binding) {


        when (appState) {
            is AppState.Success -> {
                progressBar.visibility = View.GONE
                adapter = MainFragmentAdapter(object : OnItemViewClickListener {
                    override fun onItemViewClick(details: Details) {
                        val manager = activity?.supportFragmentManager
                        manager?.let { manager ->
                            val bundle = Bundle().apply {
                                putParcelable(DetailsFragment.BUNDLE_EXTRA, details)
                            }
                            manager.beginTransaction()
                                .add(R.id.container, DetailsFragment.newInstance(bundle))
                                .addToBackStack("")
                                .commitAllowingStateLoss()
                        }
                    }
                }).apply {
                    setDetails(appState.detailsData)
                }
                mainFragmentRecyclerView.adapter = adapter
            }

            is AppState.Loading -> {
                progressBar.visibility = View.VISIBLE
            }

            is AppState.Error -> {
                progressBar.visibility = View.GONE

                Snackbar
                    .make(mainView, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.reload)) { viewModel.getDetailsFromLocalSource() }
                    .show()
            }

        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(details: Details)
    }


    companion object {
        fun newInstance() = MainFragment()
    }

}