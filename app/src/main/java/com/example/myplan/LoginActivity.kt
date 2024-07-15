package com.example.myplan

import DBHelper
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myplan.databinding.ActivityLoginBinding

// 로그인 화면
class LoginActivity : AppCompatActivity() {
    var DB:DBHelper?=null
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = ""

        DB = DBHelper(this)

        binding.login.setOnClickListener {
            val user = binding.id.text.toString()
            val password = binding.password.text.toString()

            if(user == "" || password == ""){
                Toast.makeText(this, "아이디와 비밀번호를 모두 입력하세요.", Toast.LENGTH_SHORT).show()
            }
            else{
                val checkUserpass = DB!!.checkUserpass(user, password)

                if (checkUserpass == true) {
                    Toast.makeText(this, "로그인 성공했습니다.", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, TodoActivity::class.java)
                    startActivity(intent)
                }
                else {
                    Toast.makeText(this, "아이디와 비밀번호를 확인해 주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}