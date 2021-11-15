package com.example.desafiom2yandroid.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafiom2yandroid.model.network.SimilarMovies
import com.example.desafiom2yandroid.repository.MoviesRepository
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    companion object {
        const val TAG = "MovieViewModel"
    }

    private val repository = MoviesRepository()

    private val _similarMovies = MutableLiveData<SimilarMovies>()
    private val _movieLikes = MutableLiveData<Any>()
    private val _moviePopularity = MutableLiveData<Any>()


    val similarMovies = _similarMovies
    val movieLikes = _movieLikes
    val moviePopularity = _moviePopularity
    var liked = false

    init {
        getSimilarMovies()
        getMovieDetails()
    }

    private fun getSimilarMovies() {
        viewModelScope.launch {
            val response: SimilarMovies = repository.getSimilarMovies()
            _similarMovies.value = response
            Log.d(TAG, response.toString())
        }
    }

    private fun getMovieDetails() {
        viewModelScope.launch {
            try {
                val response = repository.getMovieDetails().body()
                _movieLikes.value = response?.vote_count
                _moviePopularity.value = response?.popularity
            } catch (e: Exception) {

            }
        }
    }

    fun setLikeButton() {
        liked = !liked
    }
}