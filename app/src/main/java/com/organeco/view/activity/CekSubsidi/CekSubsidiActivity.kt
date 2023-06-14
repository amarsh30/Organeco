package com.organeco.view.activity.CekSubsidi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import com.organeco.R
import com.organeco.databinding.ActivityCekSubsidiBinding
import com.organeco.view.activity.MainActivity

class CekSubsidiActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCekSubsidiBinding
    private var kartu_tani: Boolean = false
    private var kelompok_tani: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCekSubsidiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val komoditasDisplay = resources.getStringArray(R.array.Jenis_Komoditas)
        val komoditasValue = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        lateinit var komoditasSelectedValue : Number

        val spinnerKomoditas = binding.spinnerTanaman
        val spinnerKomoditasAdapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, komoditasDisplay)
        spinnerKomoditas.adapter = spinnerKomoditasAdapter

        spinnerKomoditas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                komoditasSelectedValue = komoditasValue[position]

                Toast.makeText(this@CekSubsidiActivity,
                    getString(R.string.selected_item) + " " + komoditasDisplay[position] + "nilai rill komoditas adalah" + komoditasDisplay, Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        binding.cbKelompokTani.setOnCheckedChangeListener { buttonView, isChecked ->
            kelompok_tani = isChecked
        }

        binding.cbKartuTani.setOnCheckedChangeListener { buttonView, isChecked ->
            kartu_tani = isChecked
        }

        binding.btnCalculate.setOnClickListener {
            val luas = binding.edLuasLahan.text.toString().toInt()
            calculateResult(luas, kelompok_tani, kartu_tani)
        }

    }

    private fun calculateResult(luas_lahan: Int, kelompok_tani: Boolean, kartu_tani: Boolean) {
        if (luas_lahan < 20000 && kelompok_tani && kartu_tani) {
            Toast.makeText(this@CekSubsidiActivity,
                    "Anda layak mendapatkan subsidi pupuk, Anda bisa mendapatkannya pada kios pupuk resmi terdekat", Toast.LENGTH_LONG).show()
        } else if(luas_lahan < 2000 && !kelompok_tani && kartu_tani) {
            Toast.makeText(this@CekSubsidiActivity,
                    "Anda layak mendapatkan subsidi pupuk, Anda bisa mendapatkannya pada kios pupuk resmi terdekat dengan membawa KTP", Toast.LENGTH_LONG).show()
        } else if(!kartu_tani) {
            Toast.makeText(this@CekSubsidiActivity,
                    "Maaf anda harus memiliki kartu tani untuk mendapatkan subsidi, Silahkan daftar terlebih dahulu di website simulthan", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this@CekSubsidiActivity,
                    "Maaf anda belum bisa mendapatkan subsidi", Toast.LENGTH_LONG).show()
        }

    }


}