package net.iesseveroochoa.sabrinebouragba.tareasv01.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.db.entities.Tarea

@Dao
interface TareasDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTarea(tarea: Tarea)

    @Delete
    suspend fun delTarea(tarea: Tarea)

    @Query("SELECT * FROM tareas")
    fun getAllTareas(): Flow<List<Tarea>>

    @Query("SELECT * FROM tareas WHERE id = :id")
    suspend fun getTarea(id: Long): Tarea

    @Query("SELECT * FROM tareas WHERE estado = :estado")
    fun getTareasPorEstado(estado: Int): Flow<List<Tarea>>
}