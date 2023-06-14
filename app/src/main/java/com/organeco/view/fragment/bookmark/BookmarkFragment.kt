package com.organeco.view.fragment.bookmark

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.organeco.BookmarkAdapter
import com.organeco.Recommendation
import com.organeco.RecommendationViewModel
import com.organeco.databinding.FragmentBookmarkBinding
import com.organeco.view.result.ResultActivity
import com.organeco.viewmodel.RecommendationViewModelFactory
import com.organeco.viewmodel.ViewModelFactory

class BookmarkFragment : Fragment() {

    private var tabName: String? = null
    private lateinit var binding: FragmentBookmarkBinding
    private lateinit var adapter: BookmarkAdapter
    private val recommendationViewModel: RecommendationViewModel by viewModels {
        RecommendationViewModelFactory.getInstance(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookmarkBinding.inflate(layoutInflater)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listBookmark()
        
    }

private fun listBookmark() {
    recommendationViewModel.getAllRecommendation().observe(viewLifecycleOwner) {
        adapter = BookmarkAdapter(it)
        binding.rvBookmark.adapter = adapter
        binding.rvBookmark.layoutManager = LinearLayoutManager(requireActivity())
        adapter.setOnItemClickCallback(object : BookmarkAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Recommendation) {
                Intent(requireActivity(), ResultActivity::class.java).also {

                }
            }
        })
    }
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