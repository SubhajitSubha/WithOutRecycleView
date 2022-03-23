package com.example.recycleviewproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleviewproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){


    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: StudentViewModel
    var studentList = mutableListOf<Student>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        studentList.addAll(listOf(Student(1,"Subha",21,9883641737),Student(2,"Subhajit",21,7431985966),Student(3,"Akhil",20,7349062408),
            Student(4,"Chinmay",18,8377969923)
        ))
        viewModel = ViewModelProvider(this).get(StudentViewModel::class.java)

        binding.item.text = viewModel.formatStudent(studentList)
        viewModel.addStudents(studentList)

        //binding.itemNumber.text = viewModel.findAllData[2].toString()
    }
}