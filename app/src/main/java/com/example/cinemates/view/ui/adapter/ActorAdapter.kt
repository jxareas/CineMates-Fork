package com.example.cinemates.view.ui.adapter


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
import androidx.databinding.ViewDataBinding
import androidx.navigation.Navigation
import com.example.cinemates.NavGraphDirections
import com.example.cinemates.R
import com.example.cinemates.databinding.ListItemPersonLongBinding
import com.example.cinemates.databinding.ListItemPersonSmallBinding
import com.example.cinemates.model.Cast

class ActorAdapter :
    MultipleViewSizeAdapter<Cast, ListItemPersonLongBinding, ListItemPersonSmallBinding>(
        R.layout.list_item_person_long,
        R.layout.list_item_person_small,
        emptyList()
    ) {


    private fun navigateToDetails(binding: ViewDataBinding, item: Cast) {
        binding.root.setOnClickListener { view ->
            val action = NavGraphDirections.actionGlobalActorDetailsFragment(item)
            Navigation.findNavController(view).navigate(action)
        }

    }

    override fun onBindLongItem(binding: ListItemPersonLongBinding, item: Cast) {
        binding.actor = item
        navigateToDetails(binding, item)
    }

    override fun onBindSmallItem(binding: ListItemPersonSmallBinding, item: Cast) {
        binding.person = item
        navigateToDetails(binding, item)
    }
}
