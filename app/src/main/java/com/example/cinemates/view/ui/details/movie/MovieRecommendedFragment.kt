package com.example.cinemates.view.ui.details.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.cinemates.view.ui.adapter.MovieAdapter
import com.example.cinemates.model.Movie
import com.example.cinemates.view.ui.ListFragment

class MovieRecommendedFragment : ListFragment<Movie, MovieAdapter>(MovieAdapter()) {

    private val viewModel: MovieDetailsViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.recommendedMovies.observe(viewLifecycleOwner) { recommended ->
            adapter.addItems(recommended)
            binding.counter = recommended.size
        }
    }

}