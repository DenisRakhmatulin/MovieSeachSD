package com.example.movieseachsd.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movieseachsd.R
import com.example.movieseachsd.databinding.FragmentMainRecycleItemBinding
import com.example.movieseachsd.model.entites.Details
import com.example.movieseachsd.model.entites.rest_entites.SearchDTO
import com.example.movieseachsd.ui.adapters.MainFragmentAdapter.*
import com.example.movieseachsd.ui.main.MainFragment

class MainFragmentAdapter(private val itemClickListener: MainFragment.OnItemViewClickListener) :
    RecyclerView.Adapter<MainViewHolder>() {

    private var detailsData: List<Details> = listOf()
    private lateinit var binding: FragmentMainRecycleItemBinding
    private var pageNum = 1

    @SuppressLint("NotifyDataSetChanged")
    fun setDetails(data: List<Details>) {
        detailsData = data
        notifyDataSetChanged()
    }

    fun addData(page: Int, callback: AddDataCallback) {
        if (page != 0) {
            callback.onSuccess()
        } else {
            callback.ofFailure("Error")
        }
    }

    interface AddDataCallback {
        fun onSuccess()
        fun ofFailure(error: String)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        binding = FragmentMainRecycleItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MainViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        /*if (position > itemCount - 3) {
            pageNum++
            addData(pageNum, object : AddDataCallback {
                override fun onSuccess() {
                    detailsData +=
                }

                override fun ofFailure(error: String) {
                    TODO("Not yet implemented")
                }

            })

        }*/
        holder.bind(detailsData[position])
    }

    override fun getItemCount() = detailsData.size


    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(details: Details) = with(binding) {
            movieTitle.text = details.movie.movie_title
            movieRating.text = details.movie.vote_average.toString()
            movieYear.text = details.release_date
            movieIcon.load(R.drawable.movie_sample_pic)
            movieIcon.load("https://image.tmdb.org/t/p/w500${details.movie.poster_path}") {
                crossfade(true)
                placeholder(R.drawable.movie_sample_pic)
            }

            root.setOnClickListener { itemClickListener.onItemViewClick(details) }

        }
    }


}


