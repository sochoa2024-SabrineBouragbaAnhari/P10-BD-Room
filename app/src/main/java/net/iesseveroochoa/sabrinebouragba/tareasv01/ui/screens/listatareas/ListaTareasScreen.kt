package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.listatareas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import net.iesseveroochoa.sabrinebouragba.tareasv01.R
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.db.entities.Tarea
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components.AppBar

@Composable
fun ListaTareasScreen(
    modifier: Modifier = Modifier,
    viewModel: ListaViewModel = viewModel(),
    onClickNueva: () -> Unit = {},
    onItemModificarClick: (pos: Long) -> Unit = {},
    onItemVerClick: (pos: Int) -> Unit = {},
) {
    // recuperamos el estado de pantalla
    val uiStateTarea by viewModel.listaTareasUiState.collectAsState()
    Scaffold(
        topBar = {
            AppBar(
                tituloPantallaActual = stringResource(R.string.label_fav),
                puedeNavegarAtras = false
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onClickNueva
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = stringResource(R.string.label_nuevaTarea)
                )
            }
        }
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(uiStateTarea.listaTareas.size) { tarea ->
                    TareaItem(
                        tarea = uiStateTarea.listaTareas[tarea],
                        onClick = { onItemModificarClick(uiStateTarea.listaTareas[tarea].id!!) }
                    )
                }
            }
        }
    }
}

@Composable
fun TareaItem(
    tarea: Tarea,
    onClick: () -> Unit
) {
    Text(
        text = tarea.tecnico,
        modifier = Modifier
            .padding(16.dp)
            .clickable { onClick() }
    )
}
