package com.viswa.studentapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.viswa.studentapp.R
import com.viswa.studentapp.databinding.FragmentStudentListBinding
import com.viswa.studentapp.viewmodel.ListViewModel


class StudentListFragment : Fragment() {

private lateinit var viewModel: ListViewModel
private lateinit var binding:FragmentStudentListBinding
private val studentListAdapter = StudentListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

     binding = FragmentStudentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = studentListAdapter

       // val swipe = view.findViewById<SwipeRefreshLayout>(R.id.refreshLayout)

        binding.refreshLayout.setOnRefreshListener {
            viewModel.refresh()
            binding.recView.visibility = View.GONE
            binding.txtError.visibility = View.GONE
            binding.progressLoad.visibility = View.VISIBLE
            binding.refreshLayout.isRefreshing = false
        }
        observeViewModel()
    }


    fun observeViewModel(){
        viewModel.studentsLD.observe(viewLifecycleOwner
            , Observer {studentListAdapter.updateStudentList(it)
            Log.d("checkloading", "OK" + it.toString())})
viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
    if(it==true){
binding.recView.visibility= View.GONE
        binding.progressLoad.visibility = View.GONE
    }else{
        binding.recView.visibility = View.VISIBLE
        binding.progressLoad.visibility = View.GONE
    }
})
        viewModel.studentsLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true){
                binding.txtError?.visibility=View.VISIBLE
            }else{
                binding.txtError?.visibility=View.GONE
            }
        })
    }
}