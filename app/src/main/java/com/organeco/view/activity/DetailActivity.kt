package com.organeco.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.organeco.databinding.ActivityDetailBinding
import com.organeco.model.local.fertilizer.DataDummy

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    private lateinit var dataDummy: DataDummy

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataDummy = intent.getParcelableExtra("data_dummy") ?: return

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