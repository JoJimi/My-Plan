package com.example.myplan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class PlanAddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_add)
        title = "수업 추가"
    }
}