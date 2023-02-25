package com.example.cinemates.view.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cinemates.databinding.FragmentHomeBinding
import com.example.cinemates.model.*
import com.example.cinemates.view.ui.adapter.SectionAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.observeOn

/**
 *@author Antonio Di Nuzzo (Indisparte)
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val TAG = HomeFragment::class.simpleName
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var sectionMoviePopular: SectionMovie
    private lateinit var sectionMovieTopRated: SectionMovie
    private lateinit var sectionMovieUpcoming: SectionMovie
    private lateinit var sectionTrendingPerson: SectionPersons
    private lateinit var sectionTrendingMovie: SectionMovie
    private lateinit var sectionTrendingTvShow: SectionTvShow
    private lateinit var sectionPopularTvShow: SectionTvShow
    private lateinit var sectionTvShowOnAir: SectionTvShow
    private var sections: MutableList<Section<*>> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sectionMoviePopular = SectionMovie("Movies popular", listOf())
        sectionMovieTopRated = SectionMovie("Movies Top rated", mutableListOf())
        sectionMovieUpcoming = SectionMovie("Movies Upcoming", mutableListOf())
        sectionTrendingPerson = SectionPersons("Trending persons", mutableListOf())
        sectionTrendingMovie = SectionMovie("Trending Movie", mutableListOf())
        sectionTrendingTvShow = SectionTvShow("Trending TvShow", mutableListOf())
        sectionPopularTvShow = SectionTvShow("Popular TvShow", mutableListOf())
        sectionTvShowOnAir = SectionTvShow("TvShow On Air", mutableListOf())
        sections = mutableListOf(
            sectionMoviePopular,
            sectionMovieUpcoming,
            sectionTvShowOnAir,
            sectionMovieTopRated,
            sectionTrendingTvShow,
            sectionTrendingPerson,
            sectionPopularTvShow,
            sectionTrendingMovie
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {


            val adapter = SectionAdapter(sections)
            sectionRv.adapter = adapter
            viewModel.popularMovies.observe(requireActivity()) { popular ->
                sectionMoviePopular.items = popular
                adapter.notifyDataSetChanged()
            }
            viewModel.topRatedMovies.observe(requireActivity()) { topRated ->
                sectionMovieTopRated.items = topRated
                adapter.notifyDataSetChanged()
            }
            viewModel.upcomingMovies.observe(requireActivity()) { upcoming ->
                sectionMovieUpcoming.items = upcoming
                adapter.notifyDataSetChanged()
            }
            viewModel.trendingPerson.observe(requireActivity()) { trendingPersons ->
                sectionTrendingPerson.items = trendingPersons
                adapter.notifyDataSetChanged()
            }
            viewModel.trendingMovies.observe(requireActivity()) { trendingMovies ->
                sectionTrendingMovie.items = trendingMovies
                adapter.notifyDataSetChanged()
            }
            viewModel.trendingTvShow.observe(requireActivity()) { trendingTvShow ->
                sectionTrendingTvShow.items = trendingTvShow
                adapter.notifyDataSetChanged()
            }
            viewModel.popularTvShow.observe(requireActivity()) { popularTvShow ->
                sectionPopularTvShow.items = popularTvShow
                adapter.notifyDataSetChanged()
            }
            viewModel.tvShowOnTheAir.observe(requireActivity()) { onAir ->
                sectionTvShowOnAir.items = onAir
                adapter.notifyDataSetChanged()
            }

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}