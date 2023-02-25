package com.example.cinemates.view.ui.details.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.cinemates.view.ui.adapter.MovieAdapter
import com.example.cinemates.model.Movie
import com.example.cinemates.view.ui.ListFragment

class MovieSimilarFragment : ListFragment<Movie, MovieAdapter>(MovieAdapter()) {

    private val viewModel: MovieDetailsViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.similarMovies.collect { similar ->
                adapter.addItems(similar)
                binding.counter = similar.size
            }
        }

    }

}