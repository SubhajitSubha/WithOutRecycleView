package com.example.recycleviewproject

import android.app.Application
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope
class StudentViewModel(application: Application):AndroidViewModel(application) {
    val findAllData:LiveData<List<Student>>
    val studentList = listOf<Student>(Student(0,"Subha",21,97973242))
    private val repository:StudentRepository
    init {
        val studentInterface = StudentDatabase.getInstance(application).studentInterface()
        repository = StudentRepository(studentInterface)
        findAllData = repository.allStudents
    }
    fun addStudents(student:List<Student>){
        student.forEach {
            viewModelScope.launch(Dispatchers.IO) {
                repository.addStudent(it)
            }
        }

    }

    val studentToString=Transformations.map(findAllData){
        students->formatStudent(students )
    }
    fun formatStudent(student: List<Student>): Spanned {

        val sb = StringBuilder()
        sb.apply {
            append("<h3>Students : </h3>")
            append("<br>")
//        append("${task[index].id} -> ${task}")
            student.forEach{
                append("${it.id} -> ${it.name} -> ${it.age} -> ${it.number }<br><br>")
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
            } else {
                return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
            }
        }
    }
}