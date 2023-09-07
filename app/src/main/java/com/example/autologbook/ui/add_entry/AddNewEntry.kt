package com.example.autologbook.ui.add_entry

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.autologbook.databinding.ActivityAddNewEntryBinding
import com.example.autologbook.kernel.file_system_handler.DbHelper
import java.util.Calendar

internal class AddNewEntry : AppCompatActivity() {

    lateinit var binding: ActivityAddNewEntryBinding

    var dateTime = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddNewEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addEntryButtonDate.setOnClickListener { getDateAndTime() }
        binding.addEntryCancelButton.setOnClickListener { super.finish() }

        binding.addEntryDate.text =  getDate()

        binding.addEntrySaveButton.setOnClickListener {

            if(binding.addEntryPrice.text.isNotEmpty() and binding.addEntryLitre.text.isNotEmpty()) {
            DbHelper.getInstance().add(
                binding.addEntryLitre.text.toString().toFloat(),
                binding.addEntryPrice.text.toString().toFloat(),
                binding.addEntryComment.text.toString(),
                dateTime.time)
            } else {

            }
        }
    }


    fun getDateAndTime() {
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                val Year = year
                val Month = monthOfYear
                val Day = dayOfMonth

                val timeSetListener =
                    TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                        val calendarInstance = Calendar.getInstance()
                        calendarInstance.set(Year, Month, Day, hour, minute)
                        dateTime = calendarInstance

                        binding.addEntryDate.text = getDate()
                    }
                TimePickerDialog(
                    this, timeSetListener,
                    dateTime.get(Calendar.HOUR_OF_DAY),
                    dateTime.get(Calendar.MINUTE), true
                ).show()
            }

        DatePickerDialog(
            this, dateSetListener,
            dateTime.get(Calendar.YEAR),
            dateTime.get(Calendar.MONTH),
            dateTime.get(Calendar.DATE)
        ).show()
    }

    fun getDate(): String{
        return dateTime.get(Calendar.YEAR).toString().plus(
            "-".plus(dateTime.get(Calendar.MONTH).toString().plus(
                "-".plus(dateTime.get(Calendar.DATE).toString().plus(
                    " ".plus(dateTime.get(Calendar.HOUR_OF_DAY).toString().padStart(2, '0').plus(
                        ":".plus(dateTime.get(Calendar.MINUTE).toString().padStart(2, '0')))))))))
    }
}