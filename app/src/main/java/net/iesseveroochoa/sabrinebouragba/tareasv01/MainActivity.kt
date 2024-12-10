package net.iesseveroochoa.sabrinebouragba.tareasv01

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.db.database.TareasDataBase
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.repository.Repository
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.navigation.AppNavigation
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.tarea.TareaScreen
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.theme.TareasV01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TareasV01Theme {
                AppNavigation()
            }
        }
    }
}