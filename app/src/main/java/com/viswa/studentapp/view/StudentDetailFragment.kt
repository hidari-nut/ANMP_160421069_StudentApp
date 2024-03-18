package com.viswa.studentapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.viswa.studentapp.R
import com.viswa.studentapp.databinding.FragmentStudentDetailBinding
import com.viswa.studentapp.viewmodel.DetailViewModel

class StudentDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentStudentDetailBinding
      override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch()

        observeViewModel()
    }
    fun observeViewModel(){
        viewModel.studentLD.observe(viewLifecycleOwner
            , Observer {
                binding.txtId.setText(it.id)
                binding.txtName.setText(it.name)
                binding.txtDoB.setText(it.DoB)
                binding.txtPhone.setText(it.phone)
            })
}
}