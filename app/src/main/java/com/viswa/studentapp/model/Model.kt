package com.viswa.studentapp.model

import com.google.gson.annotations.SerializedName

data class Student(var id:String?,
                   @SerializedName("student_name")
                   var name:String?,
                   @SerializedName("birth_of_date")
                   var DoB:String?,
                   var phone:String?,
                   @SerializedName("student_photo")
                   var photoUrl:String?)
data class Lecturer(var id: String?,
    var name: String?,
    var department: String?,
    var coursesTaught: List<String>?,
    var office: Office?,
    var images: String?
)

data class Office(
    var building: String?,
    var roomNumber: Int?
)