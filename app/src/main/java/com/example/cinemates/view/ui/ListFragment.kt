package com.example.cinemates.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cinemates.view.ui.adapter.MultipleViewSizeAdapter
import com.example.cinemates.databinding.FragmentListingItemsBinding

/**
 * A fragment in which a list of items having multiple types of views.
 * @param T The type of objects contained in the recyclerview
 * @param A The adapter must extend [MultipleViewSizeAdapter]
 * @param adapter The adapter constructor
 * @author Antonio Di Nuzzo (Indisparte)
 */
open class ListFragment<T, A : MultipleViewSizeAdapter<T>>(var adapter: A) : Fragment() {
    private var _binding: FragmentListingItemsBinding? = null
    protected val binding: FragmentListingItemsBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListingItemsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.setEmptyView(emptyView.root)
            counter = adapter.itemCount
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}