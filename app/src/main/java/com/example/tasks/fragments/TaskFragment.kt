package com.example.tasks.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.tasks.databinding.FragmentTaskBinding
import com.example.tasks.models.TaskDao
import com.example.tasks.models.TaskDatabase
import com.example.tasks.viewmodels.TasksViewModel
import com.example.tasks.viewmodels.factories.TaskViewModelFactory

class TaskFragment : Fragment() {
    private var _binding : FragmentTaskBinding ?= null
    private val binding get() = _binding!!

    lateinit var viewModel : TasksViewModel
    lateinit var viewModelFactory : TaskViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // this will refer to TaskFragment class
        // this.activity will give the fragment activity
        val application = requireNotNull(this.activity).application
        val dao = TaskDatabase.getInstance(application).taskDao
        viewModelFactory = TaskViewModelFactory(dao)
        viewModel = ViewModelProvider(this, viewModelFactory)[TasksViewModel::class.java]

        binding.taskViewModel = viewModel
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}