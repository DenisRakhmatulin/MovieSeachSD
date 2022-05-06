package com.example.movieseachsd.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
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

    private val startPage = 1
    private var queryText = "поиск"
    private val includeAdult = false

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
            //adultCheck.setOnCheckedChangeListener()
            searchText.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    queryText = p0.toString()
                    viewModel.getListFromServer(startPage, queryText, includeAdult)
                }

                override fun afterTextChanged(p0: Editable?) {
                }

            })
            mainFragmentRecyclerView.adapter = adapter
            mainFragmentRecyclerView.layoutManager = GridLayoutManager(context,2)
            val observer = Observer<AppState> { renderData(it) }
            viewModel.liveData.observe(viewLifecycleOwner, observer)
            //viewModel.getDetailsFromLocalSource()

            viewModel.getListFromServer(startPage, queryText, includeAdult)
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
                mainView.createAndShow(getString(R.string.error), getString(R.string.reload), viewModel.getDetailsFromLocalSource())

                /*Snackbar
                    .make(mainView, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.reload)) { viewModel.getDetailsFromLocalSource() }
                    .show()*/
            }

        }

    }
    fun View.createAndShow(
        text: String, actionText: String, action: Unit,
        length: Int = Snackbar.LENGTH_INDEFINITE) {
        Snackbar.make(this, text, length).setAction(actionText) { action }.show()
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