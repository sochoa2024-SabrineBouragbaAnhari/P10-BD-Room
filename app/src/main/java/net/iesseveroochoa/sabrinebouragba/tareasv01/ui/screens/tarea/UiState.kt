package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.tarea

import androidx.compose.ui.graphics.Color
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.db.entities.Tarea
import javax.sql.DataSource


data class UiStateTarea(
    val categoria: String = "",
    val prioridad: String = "",
    val pagado: Boolean = false,
    val estado: String = "",
    val valoracion: Int = 0,
    val tecnico: String = "",
    val descripcion: String = "",
    val colorFondo: Color = Color.Transparent,
    val esFormularioValido: Boolean = false,
    val mostrarDialogo: Boolean = false,
    val esTareaNueva: Boolean = true,
    val listaTareas: MutableList<Tarea> = DataSource.iniciaPruebaTareas
)