package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.navigation

import kotlinx.serialization.Serializable

object ListaTareasDestination

@Serializable
data class TareaDestination(val posTarea: Long? = null)

@Serializable
data class VistaTareasDestination(val posTarea: Long? = null)

const val LISTA_TAREAS_ROUTE = "lista_tareas"
const val TAREA_ROUTE = "tarea"
const val VISTA_TAREA_ROUTE = "vista_tarea"