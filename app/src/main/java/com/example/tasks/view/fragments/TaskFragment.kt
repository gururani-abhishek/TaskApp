package com.example.tasks.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasks.databinding.FragmentTaskBinding
import com.example.tasks.models.TaskDao
import com.example.tasks.models.TaskDatabase
import com.example.tasks.view.adapters.TasksRVAdapter
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
        binding.lifecycleOwner = viewLifecycleOwner

//        binding.rvTasks.layoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.HORIZONTAL, false)
        // attaching an adapter to RV
        val adapterRV = TasksRVAdapter()
        binding.rvTasks.adapter = adapterRV

        // updating data(in adapter RV) if it's tasks is updated.
        viewModel.tasks.observe(viewLifecycleOwner, Observer { liveValueTasks ->
            adapterRV.data = liveValueTasks
        })

// using data binding you can condense all these lines below to :
// android:text = "@{viewModel.tasksString}"
//        viewModel.tasksString.observe(viewLifecycleOwner, Observer { liveValueTasksString ->
//            binding.tvTasksInfo.text = liveValueTasksString
//        })
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}