package com.organeco.view.fragment.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.organeco.databinding.FragmentHomeBinding
import com.organeco.model.local.DummyAdapter
import com.organeco.model.local.fertilizer.DataDummySource
import com.organeco.view.activity.calculator.CalculatorActivity
import com.organeco.viewmodel.UserPreferencesVM
import com.organeco.viewmodel.ViewModelFactory

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var dummyAdapter: DummyAdapter
    private val prefViewModel: UserPreferencesVM by viewModels {
        ViewModelFactory.getInstance(
            requireContext()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.mainHomeLayout.fullScroll(ScrollView.FOCUS_UP)
        dummyAdapter = DummyAdapter(DataDummySource.getDummyDataList())
        binding.rvFertilizer.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFertilizer.adapter = dummyAdapter
        // intent ke fragment
//        binding.cardIot.setOnClickListener {
//            val navController = findNavController()
//            navController.navigate(R.id.calculatorFragment)
//        }

        // inetnt ke activity
        binding.cardIot.setOnClickListener {
            startActivity(Intent(requireContext(), CalculatorActivity::class.java))
        }

        return binding.root
    }

}