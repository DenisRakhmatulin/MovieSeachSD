package com.example.movieseachsd.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.movieseachsd.databinding.ItemHistoryListBinding
import com.example.movieseachsd.model.entites.Details

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.RecyclerItemViewHolder>() {
    private var data: List<Details> = arrayListOf()

    fun setData(data: List<Details>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            ItemHistoryListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class RecyclerItemViewHolder(private val binding: ItemHistoryListBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Details) = with(binding) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                recyclerViewItem.text =
                    String.format("%s, вышел %s, продолжительность: %d мин.", data.movie.movie_title, data.release_date, data.runtime)
                root.setOnClickListener {
                    Toast.makeText(
                        itemView.context,
                        "on click: ${data.movie.movie_title}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}