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
import com.viswa.studentapp.model.Lecturer
import com.viswa.studentapp.model.Student

class ListViewModel(application: Application): AndroidViewModel(application) {
    var studentsLD=MutableLiveData<ArrayList<Student>>()
    val studentsLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    var lecturerLD=MutableLiveData<ArrayList<Lecturer>>()
    val lecturerLoadErrorLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue?=null

    fun refresh(){

        studentsLoadErrorLD.value = false
        lecturerLoadErrorLD.value = false

        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url ="http://adv.jitusolution.com/student.php"
        //val urlLect ="http://10.0.2.2/anmp/lecturer.json"

        val stringRequest = StringRequest(
            Request.Method.GET,
            url, {
                val sType = object:TypeToken<List<Student>>(){}.type

                //val sType = object:TypeToken<List<Lecturer>>(){}.type

                val result = Gson().fromJson<List<Student>>(it, sType)

                //val result = Gson().fromJson<List<Lecturer>>(it, sType)

                studentsLD.value = result as ArrayList<Student>?
                //lecturerLD.value = result as ArrayList<Lecturer>?

                loadingLD.value = false
                Log.d("show volley", it)
                 },
            {
               loadingLD.value = false
                studentsLoadErrorLD.value = false
                lecturerLoadErrorLD.value = false
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