package com.example.myplan

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
        setContentView(R.layout.activity_plan_add)

        // timeSpinner 설정
        val timeArray = resources.getStringArray(R.array.timeSpinner)
        val timeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, timeArray)
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.timeSpinner.adapter = timeAdapter

        // weekSpinner 설정
        val weekArray = resources.getStringArray(R.array.weekSpinner)
        val weekAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, weekArray)
        weekAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.weekSpinner.adapter = weekAdapter

        binding.timeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

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

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // empty here
                }
            }
    }
}
