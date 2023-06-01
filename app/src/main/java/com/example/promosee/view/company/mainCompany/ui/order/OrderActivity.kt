package com.example.promosee.view.company.mainCompany.ui.order

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.promosee.databinding.ActivityOrderBinding
import com.example.promosee.model.toShortDateFormat
import com.example.promosee.view.company.mainCompany.ui.detailInfluencer.InfluencerDetailActivity
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class OrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // bacn button
        binding.backButton.setOnClickListener{ finish() }
        setupAction()
    }

    private fun setupAction() {
        binding.btnDetail.setOnClickListener {
            val intentToDetail = Intent(this@OrderActivity, InfluencerDetailActivity::class.java)
            intentToDetail.putExtra("username", binding.username.text.toString())
        }
        binding.btnDate.setOnClickListener {
            // Makes only dates from today forward selectable.
            val constraintsBuilder =
                CalendarConstraints.Builder()
                    .setValidator(DateValidatorPointForward.now())

            // Set default day into today, and input mode to text
            val picker = MaterialDatePicker.Builder.datePicker()
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .setInputMode(MaterialDatePicker.INPUT_MODE_TEXT)
            .setCalendarConstraints(constraintsBuilder.build()).build()


            // Show date picker
            picker.show(supportFragmentManager, "OrderActivity")

            picker.addOnPositiveButtonClickListener {
                val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
                binding.postingDate.text = simpleDateFormat.format(Date(it).time)
            }
        }
        binding.btnOrder.setOnClickListener {
            val dateNew = binding.postingDate.text.toString().toShortDateFormat()
            runValidation()
        }
    }

    private fun runValidation() {

    }
}