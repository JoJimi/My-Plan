package com.example.myplan

import DBHelper
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myplan.databinding.ActivityRegisterBinding
import java.util.regex.Pattern

// 회원가입 화면
class RegisterActivity : AppCompatActivity() {
    var DB:DBHelper?=null
    lateinit var binding: ActivityRegisterBinding
    var CheckId:Boolean=false
    var CheckNick:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = ""

        DB = DBHelper(this)

        // 아이디 중복 확인
        binding.checkId.setOnClickListener {
            val id = binding.editId.text.toString()
            // 아이디는 6~15자 영문/숫자 조합으로 입력
            val idPattern = "^[A-Za-z[0-9]]{6,15}$"

            if(id == ""){
                Toast.makeText(this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            else{
                if(Pattern.matches(idPattern, id)) {
                    val checkId = DB!!.checkUser(id)

                    if (checkId == false) {
                        CheckId = true
                        Toast.makeText(this, "사용 가능한 아이디입니다.", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this, "올바른 아이디 형식을 입력하세요.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // 닉네임 중복 확인
        binding.checkNickname.setOnClickListener {
            val nickname = binding.editNickname.text.toString()
            // 4~15자 한글/영문/슷자 조합으로 입력
            val nickPattern = "^[a-zA-Z0-9ㄱ-ㅣ가-힣]{4,15}$"

            if(nickname == ""){
                Toast.makeText(this, "닉네임을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            else{
                if (Pattern.matches(nickPattern, nickname)) {
                    val checkNickname = DB!!.checkNick(nickname)

                    if(checkNickname == false){
                        CheckNick = true
                        Toast.makeText(this, "사용 가능한 닉네임입니다.", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(this, "이미 존재하는 닉네임입니다.", Toast.LENGTH_SHORT).show()
                    }
                }
                else {
                    Toast.makeText(this, "올바른 닉네임 형식을 입력하세요.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // 완료 버튼 클릭시
        binding.complete.setOnClickListener {
            val id = binding.editId.text.toString()
            val password = binding.editPassword.text.toString()
            val repass = binding.rePassword.text.toString()
            val nickname = binding.editNickname.text.toString()
            val phoneNum = binding.editPhone.text.toString()

            // 비밀번호는 8~15자 영문/숫자 조합으로 입력 + 적어도 하나의 숫자 및 영문 포함
            val pwPattern = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{8,15}$"
            // 폰 번호는 - 제외하고 10~15자 숫자 조합으로 입력
            // 국제 전화번호 코드(플러스 기호와 하나 이상의 숫자)가 있을 수도 있고 없을 수도 있음
            // 국제 전화번호 형식(예: +1234567890)
            val phonePattern = "^(\\+[0-9]+)?[0-9]{10,15}$"

            if (id == "" || password == "" || repass == "" || nickname == "" || phoneNum == "") {
                Toast.makeText(this, "회원정보를 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            else{
                if(CheckId == true){
                    if(Pattern.matches(pwPattern, password)){
                        if(password == repass){
                            if(CheckNick == true){
                                if(Pattern.matches(phonePattern, phoneNum)){
                                    val insert = DB!!.insertData(id, password, nickname, phoneNum)

                                    if(insert == true){
                                        Toast.makeText(this, "회원가입 완료했습니다.", Toast.LENGTH_SHORT).show()

                                        val intent = Intent(applicationContext, LoginActivity::class.java)
                                        startActivity(intent)
                                    }
                                    else{
                                        Toast.makeText(this, "회원가입 실패했습니다.", Toast.LENGTH_SHORT).show()
                                    }

                                }
                                else{
                                    Toast.makeText(this, "전화번호 형식이 옳지 않습니다.", Toast.LENGTH_SHORT).show()
                                }
                            }
                            else{
                                Toast.makeText(this, "닉네임 중복을 확인해주세요.", Toast.LENGTH_SHORT).show()
                            }
                        }
                        else{
                            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        Toast.makeText(this, "비밀번호 형식이 옳지 않습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this, "아이디 중복을 확인해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}