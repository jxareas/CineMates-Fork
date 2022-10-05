package com.example.cinemates.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.cinemates.databinding.ListItemMovieLongBinding

/**
 * @author Antonio Di Nuzzo
 * Created 28/07/2022 at 08:51
 */
class MovieLongViewHolder<T : ViewBinding>(binding: T) : RecyclerView.ViewHolder(binding.root) {
    lateinit var longBinding: ListItemMovieLongBinding

    init {
        if (binding is ListItemMovieLongBinding)
            longBinding = binding
    }

}