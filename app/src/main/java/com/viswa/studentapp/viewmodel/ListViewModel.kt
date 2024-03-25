package com.viswa.studentapp.viewmodel

import android.app.Application

import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.viswa.studentapp.model.Student

class ListViewModel(application: Application): AndroidViewModel(application) {
    var studentsLD=MutableLiveData<ArrayList<Student>>()
    val studentsLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue?=null

    fun refresh(){
        //volley
        //bisa cari data dari :
        // query db sqlite
        // xml
        // shared preference
//studentsLD.value = arrayListOf(
//        Student("001", "John", "1999-05-15", "+123456789", "https://picsum.photos/200"),
//        Student("002", "Alice", "2000-02-28", "+987654321", "https://picsum.photos/200"),
//        Student("003", "Michael", "1998-11-10", "+111222333", "https://picsum.photos/200"),
//        Student("004", "Emily", "1997-08-20", "+444555666", "https://picsum.photos/200"),
//        Student("005", "David", "2001-04-03", "+777888999", "https://picsum.photos/200")
//)
        studentsLoadErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url ="http://adv.jitusolution.com/student.php"
  
        val stringRequest = StringRequest(
            Request.Method.GET,
            url, {
                val sType = object:TypeToken<List<Student>>(){}.type
                val result = Gson().fromJson<List<Student>>(it, sType)
                studentsLD.value = result as ArrayList<Student>?

                loadingLD.value = false
                Log.d("show volley", it)
                 },
            {
               loadingLD.value = false
            studentsLoadErrorLD.value = false
            Log.d("showvolley", it.toString())
        })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}