package com.example.cinemates.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.databinding.ListItemMovieLongBinding
import com.example.cinemates.databinding.ListItemMovieSmallBinding

/**
 * @author Antonio Di Nuzzo
 * Created 28/07/2022 at 08:51
 */
class MovieViewHolder : RecyclerView.ViewHolder {
    var mMovieLongBinding: ListItemMovieLongBinding? = null
    var mMovieSmallBinding: ListItemMovieSmallBinding? = null

    constructor(listItemMovieLongBinding: ListItemMovieLongBinding) : super(listItemMovieLongBinding.root) {
        mMovieLongBinding = listItemMovieLongBinding
    }

    constructor(listItemMovieSmallBinding: ListItemMovieSmallBinding) : super(
        listItemMovieSmallBinding.root
    ) {
        mMovieSmallBinding = listItemMovieSmallBinding
    }
}