package com.viswa.studentapp.view
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.viswa.studentapp.databinding.LecturerListItemBinding
import com.viswa.studentapp.model.Lecturer
import com.viswa.studentapp.model.Student

class LecturerListAdapter(val lecturerList:ArrayList<Lecturer>):RecyclerView.Adapter<LecturerListAdapter.LecturerViewHolder>(){
    class LecturerViewHolder(var binding: LecturerListItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LecturerViewHolder {
        val binding = LecturerListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LecturerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return lecturerList.size
    }

    override fun onBindViewHolder(holder: LecturerViewHolder, position: Int) {
        holder.binding.txtIdLect.text = lecturerList[position].id
        holder.binding.txtNameLect.text = lecturerList[position].name
    }

    fun updateLecturerList(newLecturerList:ArrayList<Lecturer>){
        lecturerList.clear()
        lecturerList.addAll(newLecturerList)
        notifyDataSetChanged()
    }
}