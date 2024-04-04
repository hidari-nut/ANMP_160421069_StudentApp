package com.viswa.studentapp.viewmodel

import android.app.Application
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.viswa.studentapp.model.Student
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.google.gson.reflect.TypeToken
import org.json.JSONException

class DetailViewModel(application: Application): AndroidViewModel(application)  {
        val TAG = "volleyTag"
        private val gson = Gson()
        private var requestQueue: RequestQueue?=null

        val studentLD = MutableLiveData<Student>()
        fun fetch (studentId : String){

            val url ="http://adv.jitusolution.com/student.php?id=$studentId"
            requestQueue = Volley.newRequestQueue(getApplication())
            val stringRequest = StringRequest(
                Request.Method.GET,
                url, {
                    val sType = object:TypeToken<Student>(){}.type
                    val result = Gson().fromJson<Student>(it, sType)
                    studentLD.value = result
                    Log.d("show volley", it)
                },
                {
                    Log.d("show volley", it.toString())
                })

            stringRequest.tag = TAG

            requestQueue?.add(stringRequest)
        }

    }


