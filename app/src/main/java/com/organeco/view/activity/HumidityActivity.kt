package com.organeco.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.organeco.databinding.ActivityHumidityBinding
import com.organeco.view.activity.calculator.CalculatorActivity

class HumidityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHumidityBinding
    private val database = FirebaseDatabase.getInstance()
    private val sensorRef = database.getReference("sensor/kelembaban")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHumidityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        sensorRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val humidity = dataSnapshot.getValue(Double::class.java)
                    humidity?.let {
                        binding.tvTemperature.text = humidity.toString()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Failed to read humidity: ${error.message}")
            }
        })

        binding.btnSend.setOnClickListener {
            val humidity = binding.tvTemperature.text.toString().toDouble()
            val intent = Intent(this@HumidityActivity, CalculatorActivity::class.java)
            intent.putExtra("sensor/kelembaban", humidity)
            startActivity(intent)
        }

    }
}