package net.iesseveroochoa.sabrinebouragba.tareasv01.data.repository

import kotlinx.coroutines.flow.Flow
import net.iesseveroochoa.sabrinebouragba.tareasv01.TareasApplication
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.db.dao.TareasDao
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.db.database.TareasDataBase
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.db.entities.Tarea

/**
 * Se encarga de manejar la lógica de obtención y almacenamiebto de datos
 */

object Repository {
    private lateinit var tareasDao: TareasDao

    operator fun invoke() {
        tareasDao = TareasDataBase
            .getDatabase(TareasApplication.application.applicationContext)
            .tareasDao()
    }

    suspend fun addTarea(tarea: Tarea) = tareasDao.addTarea(tarea)
    suspend fun delTarea(tarea: Tarea) = tareasDao.delTarea(tarea)
    suspend fun getTarea(id: Long) = tareasDao.getTarea(id)
    fun getAllTareas(): Flow<List<Tarea>> = tareasDao.getAllTareas()
    fun getTareasByEstado(estado: Int) = tareasDao.getTareasPorEstado(estado)
}