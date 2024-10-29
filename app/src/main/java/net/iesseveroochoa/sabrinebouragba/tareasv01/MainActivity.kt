package net.iesseveroochoa.sabrinebouragba.tareasv01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.tarea.TareaScreen
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.theme.TareasV01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TareasV01Theme {
                TareaScreen()
            }
        }
    }
}