package com.viswa.studentapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.viswa.studentapp.model.Student

class ListViewModel:ViewModel() {
    var studentsLD=MutableLiveData<ArrayList<Student>>()
    val studentsLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    fun refresh(){
        //volley
        //bisa cari data dari :
        // query db sqlite
        // xml
        // shared preference
studentsLD.value = arrayListOf(
        Student("001", "John", "1999-05-15", "+123456789", "https://picsum.photos/200"),
        Student("002", "Alice", "2000-02-28", "+987654321", "https://picsum.photos/200"),
        Student("003", "Michael", "1998-11-10", "+111222333", "https://picsum.photos/200"),
        Student("004", "Emily", "1997-08-20", "+444555666", "https://picsum.photos/200"),
        Student("005", "David", "2001-04-03", "+777888999", "https://picsum.photos/200")
)
        studentsLoadErrorLD.value = false
        loadingLD.value = false
    }
}