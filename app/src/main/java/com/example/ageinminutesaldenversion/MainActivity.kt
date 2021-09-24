package com.example.ageinminutesaldenversion

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker) as Button
        btnDatePicker.setOnClickListener {view ->
            clickDatePicker(view)
            }
    }
    fun clickDatePicker(view: View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(
                    this,
                    "The chosen year is $selectedYear, the month is ${selectedMonth+1} and the day is $selectedDayOfMonth",
                    Toast.LENGTH_LONG
                ).show()

                val selectedDate = "${selectedMonth + 1}/$selectedDayOfMonth/$selectedYear"
                var tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)
                tvSelectedDate.setText(selectedDate)
                val sdf = SimpleDateFormat("MM/dd/yyyy")
                val theDate = sdf.parse(selectedDate)
                val selectedDateInMinutes = theDate!!.time / 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes = currentDate!!.time / 60000
                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                var tvSelectedDateInMinutes = findViewById<TextView>(R.id.tvSelectedDateInMinutes)
                tvSelectedDateInMinutes.setText(differenceInMinutes.toString())

            }
            , year
            , month
            , day).show()
    }
}