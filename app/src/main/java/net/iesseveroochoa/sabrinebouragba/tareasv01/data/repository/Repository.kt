package net.iesseveroochoa.sabrinebouragba.tareasv01.data.repository

import kotlinx.coroutines.flow.Flow
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.db.entities.Tarea
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.tempmodel.TempModelTareas

/**
 * Se encarga de manejar la lógica de obtención y almacenamiebto de datos
 */

object Repository {
    // Inicia el Objeto singleton
    operator fun invoke() {
        // Iniciamos el modelo
        TempModelTareas
    }

    fun addTarea(tarea: Tarea) = TempModelTareas.addTarea(tarea)

    fun delTarea(tarea: Tarea) = TempModelTareas.delTarea(tarea)

    fun getAllTareas(): Flow<List<Tarea>> = TempModelTareas.getAllTareas()

    fun getTarea(id: Long) = TempModelTareas.getTarea(id)

}