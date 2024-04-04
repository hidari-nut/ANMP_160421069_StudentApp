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

class StudentListAdapter(val studentList:ArrayList<Student>):RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() {

    class StudentViewHolder(var binding: StudentListItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = StudentListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
      //  Picasso.get().load(studentList[position].photoUrl).into(holder.binding.imageView)

        holder.binding.txtId.text = studentList[position].id
        holder.binding.txtName.text = studentList[position].name

        holder.binding.btnDetail.setOnClickListener{
            val studentId = studentList[position].id.toString()
            val action = StudentListFragmentDirections.actionDetailFragment(studentId)
            Navigation.findNavController(it).navigate(action)
        }

        Log.d("picasso", studentList[position].photoUrl.toString() )

        val picasso = Picasso.Builder(holder.itemView.context)
        picasso.listener { _, _, exception ->
            Log.e("picasso error", exception.printStackTrace().toString())
            exception.printStackTrace()
        }
        picasso.build().load(studentList[position].photoUrl.toString()).into(holder.binding.imageView, object:Callback{
            override fun onSuccess() {
                Log.d("picasso", "success")
                holder.binding.progressImg.visibility = View.INVISIBLE
                holder.binding.imageView.visibility = View.VISIBLE
            }

            override fun onError(e: Exception?) {
                Log.e("picasso error", e.toString())
            }

        })
    }

    fun updateStudentList(newStudentList:ArrayList<Student>){
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}
