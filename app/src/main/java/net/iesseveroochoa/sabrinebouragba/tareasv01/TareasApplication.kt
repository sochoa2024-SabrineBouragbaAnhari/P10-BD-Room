package net.iesseveroochoa.sabrinebouragba.tareasv01

import android.app.Application
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.repository.Repository

class TareasApplication: Application() {
    companion object{
        lateinit var application: TareasApplication
    }
    override fun onCreate() {
        super.onCreate()
        application = this
        //iniciamos el Repository
        Repository()
    }
}