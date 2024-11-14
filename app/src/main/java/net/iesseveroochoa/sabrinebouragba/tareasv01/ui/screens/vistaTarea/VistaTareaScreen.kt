package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.vistaTarea

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import net.iesseveroochoa.sabrinebouragba.tareasv01.R
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components.AppBar

@Composable
fun VistaTareaScreen(
    viewModel: VistaTareaViewModel = viewModel(),
    posTarea: Long?,
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
            Row(modifier = Modifier) {
                Button(onClick = onVolver) {
                    Text(text = "Volver")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = onVolverAInicio) {
                    Text("Volver a Inicio")
                }
            }
        }
    }
}