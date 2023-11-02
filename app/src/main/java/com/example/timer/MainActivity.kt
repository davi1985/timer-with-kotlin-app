package com.example.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    private var timer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText: EditText = findViewById(R.id.edit_value)
        val buttonStart: Button = findViewById(R.id.btn_start)
        val buttonStop: Button = findViewById(R.id.btn_stop)
        val result: TextView = findViewById(R.id.text_result)

        buttonStart.setOnClickListener {
            try {
                val number = editText.text.toString().toLong()

                timer = object : CountDownTimer(number * 60 * 1000, 100) {
                    override fun onTick(millisUntilFinished: Long) {
                        var seconds = millisUntilFinished / 1000
                        var minutes = seconds / 60
                        seconds %= 60

                        result.text = String.format("%02d:%02d", minutes, seconds)
                    }

                    override fun onFinish() {
                        result.text = "Timer end"
                    }
                }

                timer?.start()
            }catch (e: NumberFormatException) {
                Toast.makeText(this, "Write any text in field", Toast.LENGTH_SHORT).show()
            }

            buttonStop.setOnClickListener {
                timer?.cancel()
            }
        }
    }


}