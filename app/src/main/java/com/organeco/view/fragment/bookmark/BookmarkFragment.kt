package com.organeco.view.fragment.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.organeco.databinding.FragmentBookmarkBinding
import com.organeco.model.local.DummyAdapter
import com.organeco.viewmodel.ViewModelFactory

class BookmarkFragment : Fragment() {

    private var tabName: String? = null
    private lateinit var binding: FragmentBookmarkBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookmarkBinding.inflate(layoutInflater)

        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//        val layoutManager = LinearLayoutManager(requireContext())
//        binding.rvDummy.layoutManager = layoutManager
//        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
//        binding.rvDummy.addItemDecoration(itemDecoration)
//
//        val dummyAdapter = DummyAdapter(ArrayList())
//        binding.rvDummy.adapter = dummyAdapter
//
//    }

//    companion object {
//        const val ARG_TAB = "tab_name"
//        const val TAB_DUMMY = "dummy"
//        const val TAB_BOOKMARK = "bookmark"
//    }
}