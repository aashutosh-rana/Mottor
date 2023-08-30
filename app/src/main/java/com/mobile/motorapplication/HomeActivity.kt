package com.mobile.motorapplication

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar
import java.util.concurrent.TimeUnit

class HomeActivity : AppCompatActivity() {

    private lateinit var tvTimer: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        tvTimer = findViewById(R.id.tv_timer)
        openTimePickerDialog()
    }

    private fun openTimePickerDialog() {
        val calendar: Calendar = Calendar.getInstance()
        val timePickerDialog = TimePickerDialog(
            this,
            onTimeSetListener,
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )
        timePickerDialog.setTitle("Set Sleep Time")
        timePickerDialog.show()
    }

    private var onTimeSetListener = OnTimeSetListener { _, hourOfDay, minute ->
        CounterClass((minute * 60 * 1000 + hourOfDay * 60 * 60 * 1000).toLong(), 1000)
        val millis = (minute * 60 * 1000 + hourOfDay * 60 * 60 * 1000).toLong()
        val hms = String.format(
            "%02d:%02d:%02d",
            TimeUnit.MILLISECONDS.toHours(millis),
            TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(
                TimeUnit.MILLISECONDS.toHours(millis)
            ),
            TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(
                TimeUnit.MILLISECONDS.toMinutes(
                    millis
                )
            )
        )
        println(hms)
        tvTimer.text = hms
    }
}

class CounterClass(millisInFuture: Long, countDownInterval: Long) :
    CountDownTimer(millisInFuture, countDownInterval) {
    override fun onTick(millisUntilFinished: Long) {
        String.format(
            "%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(
                millisUntilFinished
            ), TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                TimeUnit.MILLISECONDS.toHours(
                    millisUntilFinished
                )
            ), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                TimeUnit.MILLISECONDS.toMinutes(
                    millisUntilFinished
                )
            )
        )
    }

    override fun onFinish() {}
}