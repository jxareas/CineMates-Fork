package com.example.cinemates.view.ui.adapter


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.cinemates.NavGraphDirections
import com.example.cinemates.R
import com.example.cinemates.databinding.ListItemCrewLongBinding
import com.example.cinemates.databinding.ListItemCrewSmallBinding
import com.example.cinemates.databinding.ListItemMovieSmallBinding
import com.example.cinemates.databinding.ListItemPersonSmallBinding
import com.example.cinemates.databinding.ListItemPosterBinding
import com.example.cinemates.databinding.ListItemTvSmallBinding
import com.example.cinemates.model.*

class PosterAdapter : BaseAdapter<Image, ListItemPosterBinding>(R.layout.list_item_poster, emptyList()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ListItemPosterBinding>(inflater, itemLayoutResId, parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindItem(binding: ListItemPosterBinding, item: Image) {
        binding.path = item.file_path
        binding.root.setOnClickListener {view->
            /*val action = NavGraphDirections.actionGlobalActorDetailsFragment(item)
            Navigation.findNavController(view).navigate(action)*/
        }

    }
}
