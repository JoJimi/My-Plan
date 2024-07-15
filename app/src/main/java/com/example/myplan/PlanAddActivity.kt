package com.example.myplan

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.myplan.databinding.ActivityPlanAddBinding

class PlanAddActivity : AppCompatActivity() {
    lateinit var binding: ActivityPlanAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var selected1: String
        var selected2: String

        var weekdata = listOf("-선택하세요-", "월요일", "화요일", "수요일", "목요일", "금요일")
        var adapter1 = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, weekdata)

        var timedata = listOf("-선택하세요-", "09:00~10:00", "10:00~11:00", "11:00~12:00", "12:00~13:00",
            "13:00~14:00", "14:00~15:00", "15:00~16:00", "16:00~17:00", "17:00~18:00", "18:00~19:00", "19:00~20:00", "20:00~21:00")
        var adapter2 = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, timedata)

        binding.weekSpinner.adapter = adapter1
        binding.timeSpinner.adapter = adapter2

        binding.weekSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selected1 = weekdata.get(position)
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // empty here
                }
            }

        binding.timeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selected2 = timedata.get(position)
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // empty here
                }
            }

        binding.completeBtn.setOnClickListener {
            val intent = Intent(this, PlanActivity::class.java)
            startActivity(intent)
        }
    }
}