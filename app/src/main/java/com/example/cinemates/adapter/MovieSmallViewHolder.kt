package com.example.cinemates.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.cinemates.databinding.ListItemMovieSmallBinding

/**
 * @author Antonio Di Nuzzo
 * Created 28/07/2022 at 08:51
 */
class MovieSmallViewHolder<T : ViewBinding>(binding: T) : RecyclerView.ViewHolder(binding.root) {
    lateinit var smallBinding: ListItemMovieSmallBinding

    init {
        if (binding is ListItemMovieSmallBinding)
            smallBinding = binding
    }

}