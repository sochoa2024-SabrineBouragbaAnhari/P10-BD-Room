package net.iesseveroochoa.sabrinebouragba.tareasv01.data.repository

import kotlinx.coroutines.flow.Flow
import net.iesseveroochoa.sabrinebouragba.tareasv01.TareasApplication
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.db.dao.TareasDao
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.db.database.TareasDataBase
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.db.entities.Tarea
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.tempmodel.TempModelTareas

/**
 * Se encarga de manejar la lógica de obtención y almacenamiebto de datos
 */

object Repository {
    // modelo de datos
    private lateinit var modelTareas : TareasDao

    // inicialización del modelo con la BD
    operator fun invoke() {
        modelTareas = TareasDataBase
            .getDatabase(TareasApplication.application.applicationContext)
            .tareasDao()
    }

    //Métodos CRUD a la base de datos
    suspend fun addTarea(tarea: Tarea)= modelTareas.addTarea(tarea)

    suspend fun delTarea(tarea: Tarea)= modelTareas.deleteTarea(tarea)

    suspend fun getTarea(id:Long)= modelTareas.getTarea(id)

    fun getAllTareas()= modelTareas.getTareas()
}