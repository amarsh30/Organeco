package com.organeco

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.organeco.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCalculatorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tanah = resources.getStringArray(R.array.Jenis_tanah)

        val spinnerTanah = binding.spinnerTipeTanah
        val spinnerTanahAdapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, tanah)
        spinnerTanah.adapter = spinnerTanahAdapter

        spinnerTanah.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                Toast.makeText(this@CalculatorActivity,
                getString(R.string.selected_item) + " " + "" + tanah[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        val tanaman = resources.getStringArray(R.array.Jenis_Tanaman)

        val spinnerTanaman = binding.spinnerTipeTanaman
        val spinnerTanamanAdapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, tanaman)
        spinnerTanaman.adapter = spinnerTanamanAdapter

        spinnerTanaman.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                Toast.makeText(this@CalculatorActivity,
                    getString(R.string.selected_item) + " " + "" + tanaman[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    private suspend fun calculate() {
        val temperature = Integer.parseInt(binding.edTemperature.text.toString())
        val humidity = Integer.parseInt(binding.edHumidity.text.toString())
        val moisture = Integer.parseInt(binding.edMoisture.text.toString())
//        val soilType = Integer.parseInt(binding.edTemperature.text.toString())
//        val cropType = Integer.parseInt(binding.edTemperature.text.toString()) spinner beda
        val nitrogen = Integer.parseInt(binding.edNitrogen.text.toString())
        val potassium = Integer.parseInt(binding.edPotassium.text.toString())
        val phosphorous = Integer.parseInt(binding.edPhosphorous.text.toString())
    }
}