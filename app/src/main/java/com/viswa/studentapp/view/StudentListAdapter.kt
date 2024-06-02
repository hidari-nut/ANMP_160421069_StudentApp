package com.viswa.studentapp.view
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.viswa.studentapp.R
import com.viswa.studentapp.databinding.StudentListItemBinding
import com.viswa.studentapp.model.Student
import java.lang.Exception

class StudentListAdapter(val studentList:ArrayList<Student>):RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(), ButtonClickListener {

    class StudentViewHolder(var view: StudentListItemBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<StudentListItemBinding>(inflater, R.layout.student_list_item, parent, false)
        return StudentViewHolder(view)
    }
    override fun getItemCount(): Int {
        return studentList.size
    }
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
          holder.view.student = studentList[position]
        holder.view.listener = this
    }
    override fun onButtonClick(v: View){
        val id = v.tag.toString()
        Log.d("StudentListAdapter", "Button clicked: studentId = $id")
        val action = StudentListFragmentDirections.actionDetailFragment(id)
        Navigation.findNavController(v).navigate(action)
    }
    fun updateStudentList(newStudentList:ArrayList<Student>){
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}
