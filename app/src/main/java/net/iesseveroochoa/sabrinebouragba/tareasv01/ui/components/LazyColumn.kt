package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.listatareas.ItemCard
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.listatareas.ListaViewModel
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.listatareas.getPrioridad

@Composable
fun LazyColumnCard(
    modifier: Modifier = Modifier,
    viewModel: ListaViewModel = viewModel(),
    onItemModificarClick: (pos: Long?) -> Unit = {}
) {
    val uiStateTarea by viewModel.listaTareasUiState.collectAsState()
    //val listaTareas = uiStateTarea.listaTareas

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(uiStateTarea.listaTareas) { tarea ->
            ItemCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .clickable { onItemModificarClick(tarea.id) },
                color = getPrioridad(tarea.prioridad),
                tarea = tarea,
            )
        }
    }
}

@Preview
@Composable
fun LazyColumnCardPreview() {
    LazyColumnCard()
}