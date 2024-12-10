package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.listatareas

import net.iesseveroochoa.sabrinebouragba.tareasv01.data.db.entities.Tarea

data class ListaUiState(
    val listaTareas: List<Tarea> = emptyList()
)

data class UiStateDialogo(
    val mostrarDialogoBorrar: Boolean = false,
    val tareaBorrar: Tarea? = null
)

data class UiStateFiltro(
    val filtroEstrado: String
)