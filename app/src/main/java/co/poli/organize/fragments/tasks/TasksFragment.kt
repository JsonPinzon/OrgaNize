package co.poli.organize.fragments.tasks

import Task
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import co.poli.organize.R
import co.poli.organize.data.TaskRepository
import co.poli.organize.databinding.FragmentTasksBinding
import java.text.SimpleDateFormat
import java.util.*

class TasksFragment : Fragment() {

    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: TaskAdapter
    private val taskList = TaskRepository.tasks


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SimpleDateFormat", "NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TaskAdapter(
            taskList,
            onEdit = { task ->
                task.title += " (Edited)"
                adapter.notifyDataSetChanged()
            },
            onDelete = { task ->
                val index = taskList.indexOf(task)
                if (index != -1) {
                    taskList.removeAt(index)
                    adapter.notifyItemRemoved(index)
                }
            },
            onComplete = {
                it.status = "Completo"
                adapter.notifyDataSetChanged()
            }
        )

        binding.recyclerTasks.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerTasks.adapter = adapter

        binding.fabAddTask.setOnClickListener {
            val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_new_task, null)
            val inputTitle = dialogView.findViewById<EditText>(R.id.inputTitle)
            val inputDescription = dialogView.findViewById<EditText>(R.id.inputDescription)
            val inputDate = dialogView.findViewById<EditText>(R.id.inputDate)
            val inputPriority = dialogView.findViewById<EditText>(R.id.inputPriority)

            inputTitle.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
            inputDescription.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
            inputDate.inputType = InputType.TYPE_CLASS_DATETIME or InputType.TYPE_DATETIME_VARIATION_DATE
            inputPriority.inputType = InputType.TYPE_CLASS_NUMBER

            AlertDialog.Builder(requireContext())
                .setTitle("Nueva Tarea")
                .setView(dialogView)
                .setPositiveButton("Agregar") { _, _ ->
                    try {
                        val task = Task(
                            id = UUID.randomUUID().toString(),
                            title = inputTitle.text.toString().trim(),
                            description = inputDescription.text.toString().trim(),
                            dueDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(inputDate.text.toString().trim())!!,
                            priority = inputPriority.text.toString().trim().toInt(),
                            status = "Pendiente"
                        )
                        taskList.add(0, task)
                        adapter.notifyItemInserted(0)
                        binding.recyclerTasks.scrollToPosition(0)
                    } catch (e: Exception) {
                        Toast.makeText(requireContext(), "Invalid data", Toast.LENGTH_SHORT).show()
                    }
                }
                .setNegativeButton("Cancelar", null)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
