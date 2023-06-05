package com.organeco.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.organeco.R
import com.organeco.databinding.ActivityDetailBinding
import com.organeco.model.local.fertilizer.DataDummy

class DetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataDummy = intent.getParcelableExtra<DataDummy>("data_dummy")

        if (dataDummy != null) {
            binding.apply {
                tvDetailName.text = dataDummy.name
                tvDetailTypePlant.text = dataDummy.plantType
                tvDetailDescription.text = dataDummy.description
                ivDetailImg.setImageResource(dataDummy.image)
            }
        }
    }
}