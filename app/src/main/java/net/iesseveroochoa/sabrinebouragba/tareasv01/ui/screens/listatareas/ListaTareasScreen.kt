package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.listatareas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import net.iesseveroochoa.sabrinebouragba.tareasv01.R
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components.AppBar
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components.BasicRadioButton
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components.BasicRadioButtonFiltro
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components.LazyColumnCard
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.tarea.TareaViewModel

@Composable
fun ListaTareasScreen(
    modifier: Modifier = Modifier,
    viewModel: ListaViewModel = viewModel(),
    viewModelTarea: TareaViewModel = viewModel(), // Use a single viewmodel
    onClickNueva: () -> Unit = {},
    onItemModificarClick: (pos: Long?) -> Unit = {},
    onItemVerClick: (pos: Long?) -> Unit = {}
) {
    val uiStateTa by viewModelTarea.uiState.collectAsState()
    val uiStateFiltro by viewModel.uiStateFiltro.collectAsState()

    Scaffold(
        topBar = {
            AppBar(
                tituloPantallaActual = stringResource(R.string.label_fav),
                puedeNavegarAtras = false
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onClickNueva) {
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
                .padding(padding)
        ) {

            // Filtro Estado
            BasicRadioButtonFiltro(
                listaOpciones = viewModel.listaFiltroEstado,
                operacionSeleccionada = uiStateFiltro.filtroEstrado,
                onOptionSelected = { viewModel.onCheckedChangedFiltroEstado(it)}
            )

            LazyColumnCard(
                modifier = Modifier.fillMaxWidth(),
                viewModel = viewModel,
                onItemModificarClick = onItemModificarClick,
                onItemEliminarClick = { tarea ->
                    viewModelTarea.onMostrarDialogoBorrar(tarea)
                }
            )

            // Dialogo de confirmación
            if (uiStateTa.mostrarDialogoBorrar) {
                AlertDialog(
                    onDismissRequest = { viewModelTarea.cancelarDialogo() },
                    title = { Text(text = stringResource(R.string.label_atencion)) },
                    text = { Text(text = stringResource(R.string.label_confirmacionBorrar)) },
                    confirmButton = {
                        TextButton(onClick = {
                            viewModelTarea.aceptarDialogo()
                        }) {
                            Text(text = stringResource(R.string.aceptar))
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { viewModelTarea.cancelarDialogo() }) {
                            Text(text = stringResource(R.string.cancelar))
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListaTareasScreenPreview() {
    ListaTareasScreen()
}