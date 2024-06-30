package com.example.myplan

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myplan.databinding.ActivityTodoBinding
import java.io.FileInputStream
import java.io.FileOutputStream

// 날짜 선택 후 일정 입력 기능
class TodoActivity : AppCompatActivity() {
    var userID: String = "userID"
    lateinit var fname: String
    lateinit var str: String
    lateinit var binding:ActivityTodoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            binding.diaryDate.visibility = View.VISIBLE
            binding.saveBtn.visibility = View.VISIBLE
            binding.TodoEditText.visibility = View.VISIBLE
            binding.textView.visibility = View.INVISIBLE
            binding.modifyBtn.visibility = View.INVISIBLE
            binding.delBtn.visibility = View.INVISIBLE
            binding.diaryDate.text = String.format("%d / %d / %d", year, month + 1, dayOfMonth)
            binding.TodoEditText.setText("")
            checkDay(year, month, dayOfMonth, userID)
        }

        binding.saveBtn.setOnClickListener {
            saveDiary(fname)
            binding.TodoEditText.visibility = View.INVISIBLE
            binding.saveBtn.visibility = View.INVISIBLE
            binding.modifyBtn.visibility = View.VISIBLE
            binding.delBtn.visibility = View.VISIBLE
            str = binding.TodoEditText.text.toString()
            binding.textView.text = str
            binding.textView.visibility = View.VISIBLE
        }
    }

    // 달력 내용 조회, 수정
    fun checkDay(cYear: Int, cMonth: Int, cDay: Int, userID: String) {
        //저장할 파일 이름설정
        fname = "" + userID + cYear + "-" + (cMonth + 1) + "" + "-" + cDay + ".txt"

        var fileInputStream: FileInputStream
        try {
            fileInputStream = openFileInput(fname)
            val fileData = ByteArray(fileInputStream.available())
            fileInputStream.read(fileData)
            fileInputStream.close()
            str = String(fileData)
            binding.TodoEditText.visibility = View.INVISIBLE
            binding.textView.visibility = View.VISIBLE
            binding.textView.text = str
            binding.saveBtn.visibility = View.INVISIBLE
            binding.modifyBtn.visibility = View.VISIBLE
            binding.delBtn.visibility = View.VISIBLE
            binding.modifyBtn.setOnClickListener {
                binding.TodoEditText.visibility = View.VISIBLE
                binding.textView.visibility = View.INVISIBLE
                binding.TodoEditText.setText(str)
                binding.saveBtn.visibility = View.VISIBLE
                binding.modifyBtn.visibility = View.INVISIBLE
                binding.delBtn.visibility = View.INVISIBLE
                binding.textView.text = binding.TodoEditText.text
            }
            binding.delBtn.setOnClickListener {
                binding.textView.visibility = View.INVISIBLE
                binding.modifyBtn.visibility = View.INVISIBLE
                binding.delBtn.visibility = View.INVISIBLE
                binding.TodoEditText.setText("")
                binding.TodoEditText.visibility = View.VISIBLE
                binding.saveBtn.visibility = View.VISIBLE
                removeDiary(fname)
            }
            if (binding.textView.text == null) {
                binding. textView.visibility = View.INVISIBLE
                binding.modifyBtn.visibility = View.INVISIBLE
                binding.delBtn.visibility = View.INVISIBLE
                binding.diaryDate.visibility = View.VISIBLE
                binding.saveBtn.visibility = View.VISIBLE
                binding.TodoEditText.visibility = View.VISIBLE
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    // 달력 내용 제거
    @SuppressLint("WrongConstant")
    fun removeDiary(readDay: String?) {
        var fileOutputStream: FileOutputStream
        try {
            fileOutputStream = openFileOutput(readDay, MODE_NO_LOCALIZED_COLLATORS)
            val content = ""
            fileOutputStream.write(content.toByteArray())
            fileOutputStream.close()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }


    // 달력 내용 추가
    @SuppressLint("WrongConstant")
    fun saveDiary(readDay: String?) {
        var fileOutputStream: FileOutputStream
        try {
            fileOutputStream = openFileOutput(readDay, MODE_NO_LOCALIZED_COLLATORS)
            val content = binding.TodoEditText.text.toString()
            fileOutputStream.write(content.toByteArray())
            fileOutputStream.close()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
}