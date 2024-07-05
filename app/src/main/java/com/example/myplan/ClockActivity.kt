package com.example.myplan

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myplan.databinding.ActivityClockBinding
import java.util.Timer

// 스톱워치 기능
class ClockActivity : AppCompatActivity() {
    lateinit var binding:ActivityClockBinding
    private var time = 0
    private var isRunning = false
    private var timerTask: Timer? = null
    private var index :Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClockBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "스톱워치"

        binding.startBtn.setOnClickListener {
            isRunning = !isRunning
            if (isRunning) {
                if(binding.startBtn.text == "시작"){
                    binding.startBtn.text = "중지"
                }
                else{
                    binding.startBtn.text = "중지"
                    binding.labBtn.text = "구간 기록"
                }
                start()
            }
            else {
                binding.startBtn.text = "계속"
                binding.labBtn.text = "초기화"
                pause()
            }
        }

        binding.labBtn.setOnClickListener {
            if(time!=0) lapTime()
            if(binding.labBtn.text == "초기화"){
                binding.startBtn.text = "시작"
                binding.labBtn.text = "구간 기록"

                reset()
            }
        }
    }

    private fun start() {

        timerTask = kotlin.concurrent.timer(period = 10) {
            time++
            val sec = time / 100
            val milli = time % 100

            runOnUiThread {
                binding.secText.text = "$sec"
                binding.milliText.text = "$milli"
            }
        }
    }

    private fun pause() {
        timerTask?.cancel();
    }

    private fun reset() {
        timerTask?.cancel()

        time = 0
        isRunning = false
        binding.secText.text = "0"
        binding.milliText.text = "00"

        binding.lapLayout.removeAllViews()
        index = 1
    }

    private fun lapTime() {
        val lapTime = time
        val textView = TextView(this).apply {
            setTextSize(20f)
        }
        textView.text = "${lapTime / 100}.${lapTime % 100}"

        binding.lapLayout.addView(textView,0)
        index++
    }
}