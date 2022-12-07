package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*


class MainActivity : AppCompatActivity() {

    var tvSelectedDate : TextView? = null
    var tvAgeInMinutes : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)

        btnDatePicker.setOnClickListener{
            clickDatePicker()

        }
    }
    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{_, selectedYear, SelectedMonth, selectedDayOfMonth->
                Toast.makeText(this,
                    "Year: $selectedYear, Month: ${SelectedMonth+1}, day: $selectedDayOfMonth",
                    Toast.LENGTH_LONG).show()
                val selectedDate = "$selectedDayOfMonth/${SelectedMonth+1}/$selectedYear"
                tvSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd/mm/yyyy",Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)

                theDate?.let{
                    val selecteDateInMinutes = theDate.time /60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time/60000
                        val differenceInMinutes = currentDateInMinutes - selecteDateInMinutes
                        tvAgeInMinutes?.text = differenceInMinutes.toString()
                    }

                }


            },
            year,month,day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis()-86400000
        dpd.show()



    }

}




