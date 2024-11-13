package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.vistaTarea

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import net.iesseveroochoa.sabrinebouragba.tareasv01.R
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components.AppBar

@Composable
fun VistaTareaScreen(
    viewModel: VistaTareaViewModel = viewModel(),
    posTarea: Long,
    onVolver: () -> Unit,
    onVolverAInicio: () -> Unit
){
    viewModel.buscarTarea(posTarea)
    val uiStateVistaTarea by viewModel.uiStateVistaTarea.collectAsState()
    Scaffold(
        topBar = {
            AppBar(
                tituloPantallaActual = R.string.label_verTarea.toString() + uiStateVistaTarea.tarea,
                puedeNavegarAtras = true,
                navegaAtras = onVolver
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

        }
    }
}