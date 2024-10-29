package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.tarea

import androidx.compose.ui.graphics.Color


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
    val mostrarDialogo: Boolean = false
)