package com.example.cinemates.view.ui.details.movie

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.cinemates.databinding.FragmentMoreBottomsheetBinding
import com.example.cinemates.view.viewmodel.DbViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * @author Antonio Di Nuzzo
 * Created 26/07/2022 at 09:01
 */
class MoreBottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentMoreBottomsheetBinding? = null
    private val binding: FragmentMoreBottomsheetBinding
        get() = _binding!!
    private val dbViewModel: DbViewModel by activityViewModels()
    private val movieViewModel: MovieDetailsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoreBottomsheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        _binding = FragmentMoreBottomsheetBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)

        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}