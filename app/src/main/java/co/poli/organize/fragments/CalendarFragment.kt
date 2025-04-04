package co.poli.organize.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import co.poli.organize.R
import java.text.SimpleDateFormat
import java.util.*

class CalendarFragment : Fragment() {

    private lateinit var tvCalendarTitle: TextView
    private lateinit var btnPrev: Button
    private lateinit var btnNext: Button
    private lateinit var gridView: GridView
    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvCalendarTitle = view.findViewById(R.id.tvCalendarTitle)
        btnPrev = view.findViewById(R.id.btnPrev)
        btnNext = view.findViewById(R.id.btnNext)
        gridView = view.findViewById(R.id.gridView)

        btnPrev.setOnClickListener {
            calendar.add(Calendar.MONTH, -1)
            actualizarCalendario()
        }

        btnNext.setOnClickListener {
            calendar.add(Calendar.MONTH, 1)
            actualizarCalendario()
        }

        actualizarCalendario()
    }

    private fun actualizarCalendario() {
        val formatoMes = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
        tvCalendarTitle.text = formatoMes.format(calendar.time)

        val diasDelMes = mutableListOf<String>()

        val primerDia = calendar.clone() as Calendar
        primerDia.set(Calendar.DAY_OF_MONTH, 1)
        val diaSemana = primerDia.get(Calendar.DAY_OF_WEEK) - 1

        // Espacios vacíos antes del primer día del mes
        for (i in 0 until diaSemana) {
            diasDelMes.add("")
        }

        // Días del mes
        val maxDias = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        for (i in 1..maxDias) {
            diasDelMes.add(i.toString())
        }

        // Ajustamos el ArrayAdapter para evitar saltos de línea en los números
        val adapter = object : ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, diasDelMes) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent) as TextView
                view.textSize = 16f
                view.textAlignment = View.TEXT_ALIGNMENT_CENTER
                return view
            }
        }

        gridView.adapter = adapter
    }
}
