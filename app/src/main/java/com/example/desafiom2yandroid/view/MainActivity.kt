package com.example.desafiom2yandroid.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafiom2yandroid.R
import com.example.desafiom2yandroid.adapter.MovieAdapter
import com.example.desafiom2yandroid.databinding.ActivityMainBinding
import com.example.desafiom2yandroid.model.network.Result
import com.example.desafiom2yandroid.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupViewModel()
        setupObserver()
        initRecyclerView()
        likeMovie()
        Log.d(TAG, viewModel.liked.toString())
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
    }

    private fun initRecyclerView() {
        binding.recyclerViewMovies.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = MovieAdapter()
        }
    }

    private fun setupObserver() {
        viewModel.movieLikes.observe(this) {
            binding.textViewLikesCount.text = getString(R.string.likes_count, it.toString())
        }
        viewModel.moviePopularity.observe(this) {
            binding.textViewWatched.text = getString(R.string.watch_count, it.toString())
        }
        viewModel.similarMovies.observe(this) {
            (binding.recyclerViewMovies.adapter as MovieAdapter).setData(it.results as MutableList<Result>)
        }
    }

    private fun likeMovie() {
        binding.imageViewLike.setOnClickListener {
            viewModel.setLikeButton()
            paintLikeHeart()
            Log.d(TAG, viewModel.liked.toString())
        }
        paintLikeHeart()
    }

    private fun paintLikeHeart() {
        if (viewModel.liked) {
            binding.imageViewLike.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.ic_favorite
                )
            )
        } else {
            binding.imageViewLike.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.ic_favorite_border
                )
            )
        }
    }
}