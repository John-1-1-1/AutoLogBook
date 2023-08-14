package com.example.autologbook.ui.add_entry


import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.autologbook.databinding.ActivityAddNewEntryBinding
import com.example.autologbook.kernel.types.DateTime
import java.util.Calendar


internal class AddNewEntry : AppCompatActivity() {

    lateinit var binding: ActivityAddNewEntryBinding

    val dateTime = DateTime(Calendar.getInstance().time)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddNewEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getDateTimeButton.setOnClickListener { getDateAndTime() }
        binding.addEntryCancelButton.setOnClickListener { this.finish() }

        binding.addEntryDate.text = dateTime.toString()
    }


    fun getDateAndTime() {
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                dateTime.Year = year + 1900
                dateTime.Month = monthOfYear
                dateTime.Day = dayOfMonth

                val timeSetListener =
                    TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                        dateTime.Hour = hour
                        dateTime.Minute = minute
                    }
                TimePickerDialog(
                    this, timeSetListener,
                    dateTime.Hour,
                    dateTime.Minute, true
                ).show()

                binding.addEntryDate.text = dateTime.toString()
            }

        DatePickerDialog(
            this, dateSetListener,
            dateTime.Year,
            dateTime.Month,
            dateTime.Day
        ).show()
    }
}