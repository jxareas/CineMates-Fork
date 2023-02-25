package com.example.cinemates.view.ui.details.tvShow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.cinemates.view.ui.adapter.MovieAdapter
import com.example.cinemates.model.Movie
import com.example.cinemates.model.TvShow
import com.example.cinemates.view.ui.ListFragment
import com.example.cinemates.view.ui.adapter.TvShowAdapter

class TvSimilarFragment : ListFragment<TvShow, TvShowAdapter>(TvShowAdapter()) {

    private val viewModel: TvDetailsViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.similarTvShow.collect { similar ->
                adapter.addItems(similar)
                binding.counter = similar.size
            }
        }

    }

}