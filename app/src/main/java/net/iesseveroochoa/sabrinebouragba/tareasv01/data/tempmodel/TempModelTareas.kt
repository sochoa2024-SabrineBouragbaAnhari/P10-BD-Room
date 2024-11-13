package net.iesseveroochoa.sabrinebouragba.tareasv01.data.tempmodel

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import net.iesseveroochoa.sabrinebouragba.tareasv01.R
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.db.entities.Tarea
import kotlin.random.Random

object TempModelTareas {
    // Lista de tareas
    val listaTareas = ArrayList<Tarea>()
    // StateFlow observable
    private val _tareasStateFlow = MutableStateFlow<List<Tarea>>(listaTareas)

    // Funcion obtiene todas las tareas
    fun getAllTareas(): Flow<List<Tarea>> {
        return _tareasStateFlow
    }

    // Funcion añade una tarea
    fun addTarea(tarea: Tarea) {
        val pos = listaTareas.indexOf(tarea)
        if (pos < 0) { // Si no existe se crea
            listaTareas.add(tarea)
        } else { // Si existe se sustituye
            listaTareas.set(pos, tarea)
        }
        // Actualizamos el StateFlow
        _tareasStateFlow.value = listaTareas
    }

    // Funcion elimina una tarea
    fun delTarea(tarea: Tarea) {
        listaTareas.remove(tarea)
        // Actualizamos el StateFlow
        _tareasStateFlow.value = listaTareas
    }

    // Obtener tarea atraves de su id
    fun getTarea(id: Long): Tarea? {
        return listaTareas.find { it.id == id }
    }

    // Método iniciar tarea con datos de ejemplo
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
        lateinit var tarea: Tarea
        (1..10).forEach {
            tarea = Tarea(
                (0..4).random(),
                (0..2).random(),
                fotos.random().toString(),
                Random.nextBoolean(),
                (0..2).random(),
                (0..5).random(),
                tecnicos.random(),
                "Descripción de la tarea $it: Lorem \\n ipsum dolor sit amet, consectetur adipiscing elit.\n" +
                        "Mauris consequat ligula et vehicula mattis. \\n Etiam tristique ornare lacinia.\n" +
                        "\\nVestibulum lacus magna, dignissim et tempor id, convallis sed augue"
            )
            listaTareas.add(tarea)
        }
        _tareasStateFlow.value = listaTareas
    }

    // Iniciar el objeto Singleton
    operator fun invoke() {
        iniciaPruebaTareas()
    }
}