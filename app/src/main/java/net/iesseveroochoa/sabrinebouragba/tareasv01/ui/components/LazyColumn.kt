package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.serialization.json.JsonNull.content
import net.iesseveroochoa.sabrinebouragba.tareasv01.R
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.db.entities.Tarea
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.listatareas.ItemCard
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.listatareas.ListaViewModel
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.listatareas.getPrioridad
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.theme.ColorPrioridadAlta

@Composable
fun LazyColumnCard(
    modifier: Modifier = Modifier,
    viewModel: ListaViewModel = viewModel(),
    onItemModificarClick: (pos: Long?) -> Unit = {},
    onItemVerClick: (pos: Long?) -> Unit = {},
) {
    val uiStateTarea by viewModel.listaTareasUiState.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(uiStateTarea) { tarea ->
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