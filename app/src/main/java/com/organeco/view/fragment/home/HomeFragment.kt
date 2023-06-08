package com.organeco.view.fragment.home

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.organeco.R
import com.organeco.databinding.FragmentHomeBinding
import com.organeco.model.local.DummyAdapter
import com.organeco.model.local.fertilizer.DataDummySource
import com.organeco.view.activity.calculator.CalculatorActivity
import com.organeco.view.viewpager.ImageData
import com.organeco.view.viewpager.ImageSliderAdapter
import com.organeco.viewmodel.UserPreferencesVM
import com.organeco.viewmodel.ViewModelFactory
import me.relex.circleindicator.CircleIndicator3
import kotlin.math.abs
import kotlin.math.min

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private lateinit var dummyAdapter: DummyAdapter
    private lateinit var adapterSlider : ImageSliderAdapter
    private val list = ArrayList<ImageData>()
    private lateinit var dots: ArrayList<TextView>

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

        prefViewModel.getUserName().observe(viewLifecycleOwner) {
            binding.tvNameHome .text = it
        }

        // inetnt ke activity
        binding.cardCalculator.setOnClickListener {
            startActivity(Intent(requireContext(), CalculatorActivity::class.java))
        }

        list.add(
            ImageData(
                "https://images.unsplash.com/photo-1557234195-bd9f290f0e4d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80"
            )
        )

        list.add(
            ImageData(
                "https://images.unsplash.com/photo-1510844355160-2fb07bf9af75?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80"
            )
        )

        list.add(
            ImageData(
                "https://plus.unsplash.com/premium_photo-1678653651678-31bf05a58350?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1171&q=80"
            )
        )

        // adapter slide
        adapterSlider = ImageSliderAdapter(list)
        binding.viewPager.adapter = adapterSlider
        dots = ArrayList()
        setIndicator()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                selectedDot(position)
                super.onPageSelected(position)
            }
        })


            return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpTransformer()

    }


    private fun setUpTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }

        binding.viewPager.setPageTransformer(transformer)
    }

    private fun selectedDot(position: Int) {
        val dotCount = min(list.size, 3) // Batasi dotCount menjadi maksimum 3
        for (i in 0 until dotCount) {
            if (i == position)
                dots[i].setTextColor(ContextCompat.getColor(requireContext(), R.color.green_700))
            else
                dots[i].setTextColor(ContextCompat.getColor(requireContext(), R.color.green_200))
        }
    }

    private fun setIndicator() {
        val dotCount = min(list.size, 3) // Batasi dotCount menjadi maksimum 3
        for (i in 0 until dotCount) {
            dots.add(TextView(requireContext()))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                dots[i].text = Html.fromHtml("&#9679", Html.FROM_HTML_MODE_LEGACY).toString()
            } else {
                dots[i].text = Html.fromHtml("&#9679")
            }
            dots[i].textSize = 18f
            binding.dotsIndicator.addView(dots[i])
        }
    }
}