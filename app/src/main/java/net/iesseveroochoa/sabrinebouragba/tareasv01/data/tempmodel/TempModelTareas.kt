package net.iesseveroochoa.sabrinebouragba.tareasv01.data.tempmodel

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import net.iesseveroochoa.sabrinebouragba.tareasv01.R
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.db.entities.Tarea
import kotlin.random.Random

object TempModelTareas {
    // Lista de tareas
    val listaTareasDestination = ArrayList<Tarea>()
    // StateFlow observable
    private val _tareasStateFlow = MutableStateFlow<List<Tarea>>(listaTareasDestination)

    // Función para obtener todas las tareas
    fun getAllTareas(): Flow<List<Tarea>> = _tareasStateFlow

    // Función para añadir una tarea
    fun addTarea(tarea: Tarea) {
        if (tarea.id == 0L) {
            // Generamos un id único para la nueva tarea
            tarea.id = Random.nextLong(1L, Long.MAX_VALUE)
        }
        val pos = listaTareasDestination.indexOfFirst { it.id == tarea.id }
        if (pos >= 0) {
            // Si ya existe, reemplaza
            listaTareasDestination[pos] = tarea
        } else {
            // Si no existe, agrega la tarea
            listaTareasDestination.add(tarea)
        }
        // Actualizamos el StateFlow
        _tareasStateFlow.value = listaTareasDestination
    }

    // Función para eliminar una tarea
    fun delTarea(tarea: Tarea) {
        listaTareasDestination.removeIf { it.id == tarea.id }
        _tareasStateFlow.value = listaTareasDestination
    }

    // Función para obtener una tarea a través de su id
    fun getTarea(id: Long): Tarea? = listaTareasDestination.find { it.id == id }

    // Método para inicializar la lista de tareas con datos de ejemplo
    fun iniciaPruebaTareas() {
        val tecnicos = listOf(
            "Pepe Gotero",
            "Sacarino Pómez",
            "Mortadelo Fernández",
            "Filemón López",
            "Zipi Climent",
            "Zape Gómez",
            "Pepito Grillo"
        )

        val fotos = listOf(R.drawable.foto1, R.drawable.foto2, R.drawable.foto3, R.drawable.foto4)

        (1..10).forEach {
            val tarea = Tarea(
                categoria = (0..4).random(),
                prioridad = (0..2).random(),
                img = fotos.random().toString(),
                pagado = Random.nextBoolean(),
                estado = (0..2).random(),
                valoracion = (0..5).random(),
                tecnico = tecnicos.random(),
                descripcion = "Descripción de la tarea $it: Lorem ipsum dolor sit amet."
            )
            listaTareasDestination.add(tarea)
        }
        _tareasStateFlow.value = listaTareasDestination
    }

    // Inicializar el objeto Singleton
    operator fun invoke() {
        iniciaPruebaTareas()
    }
}