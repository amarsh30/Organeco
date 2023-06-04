package com.organeco.view.activity.calculator

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.organeco.R
import com.organeco.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculatorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val callback = supportActionBar
        callback?.title = getString(R.string.calculator)
        callback?.setDisplayHomeAsUpEnabled(true)

        val tanah = resources.getStringArray(R.array.Jenis_tanah)

        val spinnerTanah = binding.spinnerTipeTanah
        val adapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, tanah
        )
        spinnerTanah.adapter = adapter

        spinnerTanah.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                Toast.makeText(
                    this@CalculatorActivity,
                    getString(R.string.selected_item) + " " + "" + tanah[position],
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}