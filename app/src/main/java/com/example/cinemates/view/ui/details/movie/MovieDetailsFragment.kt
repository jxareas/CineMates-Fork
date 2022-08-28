package com.example.cinemates.view.ui.details.movie

import com.example.cinemates.adapter.ViewPagerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.cinemates.databinding.FragmentMovieDetailsBinding
import com.google.android.material.tabs.TabLayoutMediator

/**
 * @author Antonio Di Nuzzo
 * Created 26/05/2022 at 15:44
 */
class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: MovieDetailsFragmentArgs by navArgs()
    private lateinit var mMovieInfoFragment: MovieInfoFragment
    private lateinit var mMovieCastFragment: MovieCastFragment
    private lateinit var mMovieImagesFragment: MovieImagesFragment
    private lateinit var mViewPagerAdapter: ViewPagerAdapter
    private lateinit var mBundle: Bundle
    private val viewModel: MovieDetailsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewPagerAdapter = ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        mMovieInfoFragment = MovieInfoFragment()
        mMovieCastFragment = MovieCastFragment()
        mMovieImagesFragment = MovieImagesFragment()
        mBundle = Bundle()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        viewModel.setSelectedMovie(args.movie)

        viewModel.selectedMovie.observe(viewLifecycleOwner) { selectedMovie ->
            binding.movie = selectedMovie
        }
        initializeViewPager()

    }

    private fun initializeViewPager() {
        mViewPagerAdapter.addFragment(mMovieInfoFragment)
        mViewPagerAdapter.addFragment(mMovieCastFragment)
        mViewPagerAdapter.addFragment(mMovieImagesFragment)
        binding.viewPager.adapter = mViewPagerAdapter
        binding.viewPager.isUserInputEnabled = false
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Info"
                1 -> tab.text = "Cast"
                2 -> tab.text = "Images"
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}