package co.poli.organize.fragments.tasks

import Task
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.poli.organize.databinding.ItemTaskBinding

import java.text.SimpleDateFormat
import java.util.*

class TaskAdapter(
    private val tasks: MutableList<Task>,
    private val onEdit: (Task) -> Unit,
    private val onDelete: (Task) -> Unit,
    private val onComplete: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(task: Task) {
            binding.textTitle.text = task.title
            binding.textDescription.text = task.description
            binding.textPriority.text = task.priority.toString()
            binding.textStatus.text = task.status
            binding.textDate.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(task.dueDate)

            binding.checkboxCompleted.setOnCheckedChangeListener(null)
            binding.checkboxCompleted.isChecked = task.status == "Completo"

            binding.checkboxCompleted.setOnCheckedChangeListener { _, isChecked ->
                task.status = if (isChecked) "Completo" else "Pendiente"
                binding.textStatus.text = task.status
                onComplete(task)
            }

            binding.btnDelete.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onDelete(task)
                    notifyItemRemoved(position)
                }
            }

            binding.root.setOnClickListener {
                onEdit(task)
                notifyItemChanged(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }
}
