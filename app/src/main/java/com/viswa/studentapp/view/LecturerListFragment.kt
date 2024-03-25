package com.viswa.studentapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.viswa.studentapp.databinding.FragmentLecturerListBinding
import com.viswa.studentapp.viewmodel.ListViewModel


class LecturerListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private lateinit var binding: FragmentLecturerListBinding
    private val lecturerListAdapter = LecturerListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLecturerListBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        binding.recViewLect.layoutManager = LinearLayoutManager(context)
        binding.recViewLect.adapter = lecturerListAdapter


        binding.refreshLayoutLect.setOnRefreshListener {
            viewModel.refresh()
            binding.recViewLect.visibility = View.GONE
            binding.txtErrorLect.visibility = View.GONE
            binding.progressLoadLect.visibility = View.VISIBLE
            binding.refreshLayoutLect.isRefreshing = false
        }
        observeViewModel()
    }
    fun observeViewModel(){
        viewModel.lecturerLD.observe(viewLifecycleOwner
            , Observer {lecturerListAdapter.updateLecturerList(it)
                Log.d("checkloading", "OK" + it.toString())})
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it==true){
                binding.recViewLect.visibility= View.GONE
                binding.progressLoadLect.visibility = View.GONE
            }else{
                binding.recViewLect.visibility = View.VISIBLE
                binding.progressLoadLect.visibility = View.GONE
            }
        })
        viewModel.lecturerLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true){
                binding.txtErrorLect?.visibility=View.VISIBLE
            }else{
                binding.txtErrorLect?.visibility=View.GONE
            }
        })
    }
}