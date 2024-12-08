package net.iesseveroochoa.sabrinebouragba.tareasv01.data.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.db.dao.TareasDao
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.db.entities.Tarea

@Database(entities = arrayOf(Tarea::class), version = 1, exportSchema = false)
abstract class TareasDataBase  : RoomDatabase() {
    abstract fun tareasDao(): TareasDao

    companion object {
        // Singleton previene que se abran múltiples instancias de la base de datos
        @Volatile
        private var INSTANCE : TareasDataBase? = null

        fun getDatabase(context : Context) : TareasDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TareasDataBase::class.java,
                    "tareas_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }
}