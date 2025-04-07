import java.io.Serializable
import java.util.Date

data class Task(
    val id: String,
    var title: String,
    var description: String,
    var dueDate: Date,
    var priority: Int,
    var status: String
) : Serializable
