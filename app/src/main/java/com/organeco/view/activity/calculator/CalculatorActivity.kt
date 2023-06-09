package com.organeco.view.activity.calculator

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.organeco.R
import com.organeco.view.result.ResultActivity
import com.organeco.databinding.ActivityCalculatorBinding
import com.organeco.model.remote.utils.MediatorResult
import com.organeco.viewmodel.CalculatorViewModel
import com.organeco.viewmodel.ViewModelFactory

class CalculatorActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCalculatorBinding

    private val calculatorViewModel : CalculatorViewModel by viewModels { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tanahDisplay = resources.getStringArray(R.array.Jenis_tanah)
        val tanahValue = listOf(1, 2, 3)
        lateinit var tanahSelectedValue : Number

        val spinnerTanah = binding.spinnerTipeTanah
        val spinnerTanahAdapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, tanahDisplay)
        spinnerTanah.adapter = spinnerTanahAdapter

        spinnerTanah.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                tanahSelectedValue = tanahValue[position]

                Toast.makeText(this@CalculatorActivity,
                    getString(R.string.selected_item) + " " + tanahDisplay[position] + "nilai rill tanah adalah " + tanahSelectedValue, Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        val tanamanDisplay = resources.getStringArray(R.array.Jenis_Tanaman)
        val tanamanValue = listOf(1, 2, 3)
        lateinit var tanamanSelectedValue : Number

        val spinnerTanaman = binding.spinnerTipeTanaman
        val spinnerTanamanAdapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, tanamanDisplay)
        spinnerTanaman.adapter = spinnerTanamanAdapter

        spinnerTanaman.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                tanamanSelectedValue = tanamanValue[position]

                Toast.makeText(this@CalculatorActivity,
                    getString(R.string.selected_item) + " " + tanamanDisplay[position] + "nilai rill tanaman adalah" + tanamanSelectedValue, Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        binding.btnCalculate.setOnClickListener{
            calculate(tanahSelectedValue, tanamanSelectedValue)
        }


    }

    private fun calculate(tipeTanah : Number ,tipeTanaman : Number) {
        val temperature = Integer.parseInt(binding.edTemperature.text.toString())
        val humidity = Integer.parseInt(binding.edHumidity.text.toString())
        val moisture = Integer.parseInt(binding.edMoisture.text.toString())
        val soilType = tipeTanah
        val cropType = tipeTanaman
        val nitrogen = Integer.parseInt(binding.edNitrogen.text.toString())
        val potassium = Integer.parseInt(binding.edPotassium.text.toString())
        val phosphorous = Integer.parseInt(binding.edPhosphorous.text.toString())

        calculatorViewModel.postCalculate(temperature, humidity, moisture, soilType, cropType, nitrogen, potassium, phosphorous).observe(this){
            when(it) {
                is MediatorResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is MediatorResult.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val intentResult = Intent(this@CalculatorActivity, ResultActivity::class.java)
                    intentResult.putExtra(ResultActivity.EXTRA_RESULT, it.data.predictions)

                    val input = CalculatorInput(
                        temperature, humidity, moisture, soilType, cropType, nitrogen, potassium, phosphorous
                    )
                    intentResult.putExtra(ResultActivity.EXTRA_INPUT, input)

                    startActivity(intentResult)
                }
                else -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this@CalculatorActivity, "Gagal", Toast.LENGTH_LONG).show()

                }
            }
        }

    }
}