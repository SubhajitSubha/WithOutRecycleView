package com.example.recycleviewproject

import androidx.lifecycle.LiveData
import com.example.recycleviewproject.Student
import com.example.recycleviewproject.StudentInterface

class StudentRepository(private val studentInterface: StudentInterface) {
    val allStudents:LiveData<List<Student>> = studentInterface.findStudents()

    fun addStudent(student: Student){
        studentInterface.insertStudent(student)
    }
}