package com.viswa.studentapp.view
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.viswa.studentapp.R
import com.viswa.studentapp.databinding.StudentListItemBinding
import com.viswa.studentapp.model.Student
import java.lang.Exception

class StudentListAdapter(val studentList:ArrayList<Student>):RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(), ButtonClickListener {

    class StudentViewHolder(val binding: StudentListItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = StudentListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
          holder.binding.student = studentList[position]

        holder.binding.btnDetail.setOnClickListener{
            val studentId = studentList[position].id.toString()
            val action = StudentListFragmentDirections.actionDetailFragment(studentId)
            Navigation.findNavController(it).navigate(action)
        }
    }
    override fun onButtonClick(v: View){
        val id = v.tag.toString()
        val action = StudentListFragmentDirections.actionDetailFragment(id)
        Navigation.findNavController(v).navigate(action)
    }

    fun updateStudentList(newStudentList:ArrayList<Student>){
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}
