package com.example.tasks.view.adapters

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tasks.R
import com.example.tasks.models.Task

class TasksRVAdapter : RecyclerView.Adapter<TasksRVAdapter.TaskItemViewHolder>() {
    var data = listOf<Task>()
    set(value) { // custom setter,
        field = value
        notifyDataSetChanged()
    }

    class TaskItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTaskInfo : TextView

        init {
            tvTaskInfo = itemView.findViewById(R.id.tv_task_info)
        }

        // passes the responsibility of onCreateViewHolder to ViewHolder class
        companion object {
            fun inflateFrom(parent: ViewGroup) : TaskItemViewHolder {
                // step 1 : inflate your individual view
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
                // step 2 : pass that view to your view holder class, and return the view holder
                return TaskItemViewHolder(view)
            }
        }

        fun bind(item : Task) {
            tvTaskInfo.text = ("Id : ${item.taskId}" + '\n' + "Task : ${item.taskDesc}" + '\n' + "Done : ${item.taskDone}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        return TaskItemViewHolder.inflateFrom(parent)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        holder.bind(data[position])
    }
}