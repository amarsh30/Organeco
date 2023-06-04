package com.organeco.view.fragment.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.organeco.databinding.FragmentBookmarkBinding
import com.organeco.viewmodel.UserPreferencesVM
import com.organeco.viewmodel.ViewModelFactory

class BookmarkFragment : Fragment() {

    private lateinit var binding: FragmentBookmarkBinding
    private val prefViewModel: UserPreferencesVM by viewModels {
        ViewModelFactory.getInstance(
            requireContext()
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookmarkBinding.inflate(layoutInflater)

        return binding.root
    }

}