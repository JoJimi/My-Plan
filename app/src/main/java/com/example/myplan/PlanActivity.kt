package com.example.myplan

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.myplan.databinding.ActivityPlanBinding

// 시간표 기능 - 대학 시간표 생각
class PlanActivity : AppCompatActivity() {
    lateinit var binding: ActivityPlanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "시간표"



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu1 -> {startActivity(Intent(this, PlanAddActivity::class.java))}
        }
        return super.onOptionsItemSelected(item)
    }
}