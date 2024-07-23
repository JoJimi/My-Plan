package com.example.myplan

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myplan.databinding.ActivityPlanBinding
import com.example.myplan.databinding.ItemMainBinding

class PlanActivity : AppCompatActivity() {
    lateinit var binding: ActivityPlanBinding
    lateinit var data:MutableList<MutableList<String>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "일정 정리"

        data = mutableListOf()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = MyAdapter(data)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        val requestLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            val name = it.data?.getStringExtra("name")
            val week = it.data?.getStringExtra("week")
            val time = it.data?.getStringExtra("time")

            val resultdata = mutableListOf<String>()
            resultdata.add(name!!)
            resultdata.add(week!!)
            resultdata.add(time!!)
            data.add(resultdata)
            adapter.notifyDataSetChanged()

            MyApplication.prefs.setdata("data[${data.size}][0]",name)
            MyApplication.prefs.setdata("data[${data.size}][1]",week)
            MyApplication.prefs.setdata("data[${data.size}][2]",time)
            MyApplication.prefs.setint("datasize",data.size)
        }

        for( i in 1..MyApplication.prefs.getint("datasize",0)){
            val result = mutableListOf<String>()
            result.add(MyApplication.prefs.getdata("data[$i][0]",""))
            result.add(MyApplication.prefs.getdata("data[$i][1]",""))
            result.add(MyApplication.prefs.getdata("data[$i][2]",""))
            data.add(result)
            adapter.notifyDataSetChanged()
        }

        binding.addBtn.setOnClickListener {
            val intent = Intent(this, PlanAddActivity::class.java)
            requestLauncher.launch(intent)
        }
    }
}
class MyViewHolder(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root)

class MyAdapter(val data: MutableList<MutableList<String>>) :
    RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var binding = (holder as MyViewHolder).binding
        val listposition = data[position]
        holder.binding.workname.text = listposition[0]
        holder.binding.weekname.text = listposition[1]
        holder.binding.timename.text = listposition[2]

        binding.deleteImage.setOnClickListener {
            deleteItem(position)
        }
    }
    private fun deleteItem(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, data.size)

        MyApplication.prefs.setint("datasize", data.size)
        for (i in 0 until data.size) {
            MyApplication.prefs.setdata("data[$i][0]", data[i][0])
            MyApplication.prefs.setdata("data[$i][1]", data[i][1])
            MyApplication.prefs.setdata("data[$i][2]", data[i][2])
        }
    }
}