package com.example.cinemates.view.ui.adapter

import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.view.ui.adapter.SectionRecyclerViewAdapter.SectionViewHolder
import com.example.cinemates.databinding.ListItemSectionBinding
import com.example.cinemates.model.*
import com.example.cinemates.util.inflater

/**
 * @author Antonio Di Nuzzo
 * Created 15/12/2021 at 16:36
 */
class SectionRecyclerViewAdapter(private val lifecycleOwner: LifecycleOwner) :
    ListAdapter<Section<*>, SectionViewHolder>(asyncDiffConfig) {
    private val dataList: MutableList<Section<*>> = mutableListOf()

    private companion object {
        private const val PERSON = 0
        private const val MOVIE = 1
        private const val TV = 2
        private val diffCallback = object : DiffUtil.ItemCallback<Section<*>>() {
            override fun areItemsTheSame(oldItem: Section<*>, newItem: Section<*>): Boolean =
                oldItem.liveData == newItem.liveData

            override fun areContentsTheSame(oldItem: Section<*>, newItem: Section<*>): Boolean =
                areItemsTheSame(oldItem, newItem)
        }

        private val asyncDiffConfig = AsyncDifferConfig.Builder(diffCallback).build()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        return SectionViewHolder(parent inflater ListItemSectionBinding::inflate)
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        when (holder.itemViewType) {
            MOVIE -> {
                val movieSection = dataList[position] as Section<Movie>
                val sectionItemsMovie = MovieAdapter()
                holder.binding.apply {
                    section = movieSection
                    executePendingBindings()
                    recyclerView.adapter = sectionItemsMovie
                    recyclerView.setEmptyView(holder.binding.emptyView.root)
                }
                movieSection.liveData.observe(lifecycleOwner) { items ->
                    sectionItemsMovie.addItems(
                        items
                    )
                }

            }
            TV -> {
                val tvShowSection = dataList[position] as Section<TvShow>
                val sectionItemsTv = TvShowAdapter()
                holder.binding.apply {
                    section = tvShowSection
                    executePendingBindings()
                    recyclerView.adapter = sectionItemsTv
                    recyclerView.setEmptyView(holder.binding.emptyView.root)
                }
                tvShowSection.liveData.observe(lifecycleOwner) { items ->
                    sectionItemsTv.addItems(
                        items
                    )
                }

            }
            PERSON -> {
                val personSection = dataList[position] as Section<Person>
                val sectionItemsPerson = PersonAdapter()
                holder.binding.apply {
                    section = personSection
                    executePendingBindings()
                    recyclerView.adapter = sectionItemsPerson
                    recyclerView.setEmptyView(holder.binding.emptyView.root)
                }
                personSection.liveData.observe(lifecycleOwner) { items ->
                    sectionItemsPerson.addItems(
                        items
                    )
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    fun addItems(dataList: List<Section<*>>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

    fun addItems(section: Section<*>) {
        dataList.add(section)
        notifyDataSetChanged()
    }

    //Returns the view type of the item at position for the purposes of view recycling.
    override fun getItemViewType(position: Int): Int {
        val value = dataList[position].genericType

        return when (value) {
            Movie::class.java -> MOVIE
            Person::class.java, Cast::class.java -> PERSON
            TvShow::class.java -> TV
            else -> -1
        }
    }

    class SectionViewHolder(val binding: ListItemSectionBinding) :
        RecyclerView.ViewHolder(
            binding.root
        )
}