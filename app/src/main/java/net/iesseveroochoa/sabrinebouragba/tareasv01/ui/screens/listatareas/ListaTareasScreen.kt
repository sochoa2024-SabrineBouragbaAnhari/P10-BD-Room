package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.listatareas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import net.iesseveroochoa.sabrinebouragba.tareasv01.R
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components.AppBar
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components.LazyColumnCard

@Composable
fun ListaTareasScreen(
    modifier: Modifier = Modifier,
    viewModel: ListaViewModel = viewModel(),
    onClickNueva: () -> Unit = {},
    onItemModificarClick: (pos: Long?) -> Unit = {},
    onItemVerClick: (pos: Long?) -> Unit = {},
) {
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
                .padding(padding)
        ) {
            LazyColumnCard(
                modifier = Modifier.fillMaxWidth(),
                viewModel = viewModel,
                onItemModificarClick = onItemModificarClick,
                onItemVerClick = onItemVerClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListaTareasScreenPreview() {
    ListaTareasScreen()
}