package com.example.cinemates.adapter

import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.NavGraphDirections
import com.example.cinemates.databinding.ListItemMovieLongBinding
import com.example.cinemates.databinding.ListItemMovieSmallBinding
import com.example.cinemates.databinding.ListItemPersonLongBinding
import com.example.cinemates.databinding.ListItemPersonSmallBinding
import com.example.cinemates.model.data.Cast
import com.example.cinemates.model.data.Movie
import com.example.cinemates.model.data.Person
import com.example.cinemates.util.MyDiffUtilCallbacks
import com.example.cinemates.util.ViewSize
import com.example.cinemates.util.help

private const val PERSON = 0
private const val MOVIE = 1

/**
 * @author Antonio Di Nuzzo
 * Created 15/12/2021 at 16:36
 */
class ItemsRecyclerViewAdapter<T>(private val viewSize: ViewSize) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var dataList: MutableList<T> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder = when (viewType) {
            MOVIE -> if (viewSize == ViewSize.SMALL) {
                MovieSmallViewHolder(parent help ListItemMovieSmallBinding::inflate)
            } else {
                MovieSmallViewHolder(parent help ListItemMovieLongBinding::inflate)
            }
            PERSON -> if (viewSize == ViewSize.SMALL) {
                PersonViewHolder(parent help ListItemPersonSmallBinding::inflate)
            } else {
                PersonViewHolder(parent help ListItemPersonLongBinding::inflate)
            }
            else -> throw IllegalStateException("Unexpected value: $viewType")
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            MOVIE -> {
                val movie = dataList[position] as Movie
                when (viewSize) {
                    ViewSize.LONG -> {
                        (holder as MovieLongViewHolder<*>).longBinding!!.movie = movie
                        holder.longBinding!!.executePendingBindings()
                        holder.longBinding!!.root.setOnClickListener { view ->
                            navigateToMovieDetails(
                                view,
                                movie
                            )
                        }
                    }
                    ViewSize.SMALL -> {
                        (holder as MovieSmallViewHolder<*>).smallBinding!!.movie = movie
                        holder.smallBinding!!.executePendingBindings()
                        holder.smallBinding!!.root.setOnClickListener { view ->
                            navigateToMovieDetails(
                                view,
                                movie
                            )
                        }
                    }
                }
            }
            PERSON -> {
                val person = dataList[position] as Person
                when (viewSize) {
                    ViewSize.LONG -> {
                        (holder as PersonViewHolder).longBinding!!.actor =
                            dataList[position] as Cast
                        holder.longBinding!!.executePendingBindings()
                        holder.longBinding!!.root.setOnClickListener { view ->
                            navigateToActorDetails(view, person)
                        }
                    }
                    ViewSize.SMALL -> {
                        (holder as PersonViewHolder).smallBinding!!.person =
                            dataList[position] as Person
                        holder.smallBinding!!.executePendingBindings()
                        holder.smallBinding!!.root.setOnClickListener { view ->
                            navigateToActorDetails(view, person)
                        }
                    }
                }
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: List<Any>
    ) {
        if (payloads.isEmpty()) super.onBindViewHolder(holder, position, payloads) else {
            when (holder.itemViewType) {
                MOVIE -> {
                    val movie = dataList[position] as Movie
                    when (viewSize) {
                        ViewSize.LONG -> {
                            (holder as MovieLongViewHolder<*>).longBinding.movie = movie
                            holder.longBinding.executePendingBindings()
                            holder.longBinding.root.setOnClickListener { view ->
                                navigateToMovieDetails(
                                    view,
                                    movie
                                )
                            }
                        }
                        ViewSize.SMALL -> {
                            (holder as MovieSmallViewHolder<*>).smallBinding.movie = movie
                            holder.smallBinding.executePendingBindings()
                            holder.smallBinding.root.setOnClickListener { view ->
                                navigateToMovieDetails(
                                    view,
                                    movie
                                )
                            }
                        }
                    }
                }
                PERSON -> {
                    val person = dataList[position] as Person
                    when (viewSize) {
                        ViewSize.LONG -> {
                            (holder as PersonViewHolder).longBinding!!.actor =
                                dataList[position] as Cast?
                            holder.longBinding!!.executePendingBindings()
                            holder.longBinding!!.root.setOnClickListener { view ->
                                navigateToActorDetails(
                                    view,
                                    person
                                )
                            }
                        }
                        ViewSize.SMALL -> {
                            (holder as PersonViewHolder).smallBinding!!.person =
                                dataList[position] as Person?
                            holder.smallBinding!!.executePendingBindings()
                            holder.smallBinding!!.root.setOnClickListener { view ->
                                navigateToActorDetails(
                                    view,
                                    person
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun navigateToActorDetails(view: View, person: Person) {
        val action = NavGraphDirections.actionGlobalActorDetailsFragment(person)
        findNavController(view).navigate(action)
    }

    private fun navigateToMovieDetails(view: View, movie: Movie) {
        val action = NavGraphDirections.actionGlobalMovieDetailsFragment(movie)
        findNavController(view).navigate(action)
    }

    //Returns the view type of the item at position for the purposes of view recycling.
    override fun getItemViewType(position: Int): Int {
        if (dataList[position] is Movie) {
            return MOVIE
        } else if ((dataList[position] is Person)) {
            return PERSON
        }
        return -1
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun addItems(dataList: MutableList<T>) {
        val diffResult = DiffUtil.calculateDiff(MyDiffUtilCallbacks(this.dataList, dataList))
        diffResult.dispatchUpdatesTo(this)
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }
}