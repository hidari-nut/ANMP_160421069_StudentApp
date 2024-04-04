package com.viswa.studentapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.viswa.studentapp.R
import com.viswa.studentapp.databinding.FragmentStudentDetailBinding
import com.viswa.studentapp.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentStudentDetailBinding
    private var studentId: String = ""

      override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val studentId = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(studentId)
        observeViewModel()
    }
    fun observeViewModel(){
        viewModel.studentLD.observe(viewLifecycleOwner
            , Observer {
                Picasso.get().load(it.photoUrl).into(binding.imageView2);
                binding.txtId.setText(it.id)
                binding.txtName.setText(it.name)
                binding.txtDoB.setText(it.DoB)
                binding.txtPhone.setText(it.phone)

                var student = it

                binding.btnUpdate.setOnClickListener{
                    Observable.timer(5, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            Log.d("Messages", "five seconds")
                            MainActivity.showNotif(student.name.toString(),
                                "A new notification created",
                                R.drawable.baseline_person_24)
                        }
                }
})
}
}