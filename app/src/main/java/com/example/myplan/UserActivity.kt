package com.example.myplan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myplan.databinding.ActivityUserBinding

// 사용자 정보 기능
class UserActivity : AppCompatActivity() {
    lateinit var binding:ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "사용자 정보"




    }
}