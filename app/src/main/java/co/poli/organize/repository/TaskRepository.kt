package co.poli.organize.data

import Task
import java.text.SimpleDateFormat

object TaskRepository {
    val tasks = mutableListOf(
        Task(
            "1",
            "Estudiar Kotlin",
            "Revisar funciones, clases y manejo de listas",
            SimpleDateFormat("dd/MM/yyyy").parse("16/04/2025")!!,
            2,
            "Pendiente"
        ),
        Task(
            "2",
            "Practicar Android",
            "Implementar navegación entre fragments con ViewBinding",
            SimpleDateFormat("dd/MM/yyyy").parse("20/04/2025")!!,
            1,
            "En progreso"
        ),
        Task(
            "3",
            "Aprender Git",
            "Practicar comandos básicos como commit, push y pull",
            SimpleDateFormat("dd/MM/yyyy").parse("22/04/2025")!!,
            2,
            "Pendiente"
        ),
        Task(
            "4",
            "Revisar principios SOLID",
            "Estudiar cada principio y buscar ejemplos en Java y Kotlin",
            SimpleDateFormat("dd/MM/yyyy").parse("25/04/2025")!!,
            3,
            "Pendiente"
        )
    )
}
