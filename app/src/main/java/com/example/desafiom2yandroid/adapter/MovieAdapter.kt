package com.example.desafiom2yandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.desafiom2yandroid.R
import com.example.desafiom2yandroid.model.network.Result

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var moviesList = mutableListOf<Result>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title:TextView = itemView.findViewById(R.id.text_view_movie_title)
        private val movieInfo: TextView = itemView.findViewById(R.id.text_view_movie_year)
        private val coverImage: ImageView = itemView.findViewById(R.id.image_view_movie_cover)

        fun bind(result: Result) {

            val coverUrl = "https://image.tmdb.org/t/p/original/" + result.poster_path
            title.text = result.original_title
            movieInfo.text = result.release_date.take(4)
            Glide.with(coverImage)
                .load(coverUrl)
                .error(R.drawable.edward)
                .into(coverImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = moviesList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    fun setData(newList: MutableList<Result>) {
        moviesList = newList
        notifyDataSetChanged()
    }

}