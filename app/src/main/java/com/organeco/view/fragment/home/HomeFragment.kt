package com.organeco.view.fragment.home

import android.content.Intent
import android.os.Build
import android.os.Bundle
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
import com.organeco.view.activity.CekSubsidi.CekSubsidiActivity
import com.organeco.view.activity.calculator.CalculatorActivity
import com.organeco.view.viewpager.ImageData
import com.organeco.view.viewpager.ImageSliderAdapter
import com.organeco.viewmodel.UserPreferencesVM
import com.organeco.viewmodel.ViewModelFactory
import kotlin.math.abs
import kotlin.math.min

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private lateinit var dummyAdapter: DummyAdapter
    private lateinit var adapterSlider: ImageSliderAdapter
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
            binding.tvNameHome.text = it
        }

        // intent ke activity
        binding.cardCalculator.setOnClickListener {
            startActivity(Intent(requireContext(), CalculatorActivity::class.java))
        }
        binding.cardEligibility.setOnClickListener {
            startActivity(Intent(requireContext(), CekSubsidiActivity::class.java))
        }

        // image slider
        list.add(ImageData(R.drawable.slide1))
        list.add(ImageData(R.drawable.slide2))
        list.add(ImageData(R.drawable.slide3))

        // adapter slide
        adapterSlider = ImageSliderAdapter(list)
        binding.viewPager.adapter = adapterSlider
        dots = ArrayList()
        setIndicator()

        // indicator
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

    // memberikan margin ke slider
    private fun setUpTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }

        binding.viewPager.setPageTransformer(transformer)
    }

    //indicator start
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
    // indicator end
}