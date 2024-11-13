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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import net.iesseveroochoa.sabrinebouragba.tareasv01.R
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components.AppBar

@Composable
fun ListaTareasScreen(
    modifier: Modifier = Modifier,
    viewModel: ListaViewModel = viewModel(),
    onClickNueva: () -> Unit = {},
    onItemModificarClick: (pos: Long) -> Unit = {},
    onItemVerClick: (pos: Long) -> Unit = {}
) {
    // recuperamos el estado de pantalla
    val uiStateTarea by viewModel.uiStateTarea.collectAsState()
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
        Column(modifier = modifier.fillMaxSize()
            .padding(padding)) {
            //lista de palabras
            uiStateTarea.listaTareas.forEachIndexed { pos, item  ->
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    //Cuando se pulsa se ejecuta la lambda onItemClick, que nos lleva a
                    //la pantalla de VistaPalabraScreen
                    Icon(
                        painter = painterResource(R.drawable.ic_ver),
                        contentDescription = "mostrar",
                        modifier = Modifier.clickable {
                            onItemVerClick(pos.toLong())
                        }
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = item.toString(),
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            //navegamos a la pantalla de PalabraScreen para modificar la palabra
                            .clickable {
                                onItemModificarClick(pos.toLong())
                            }
                    )
                }
                //linea separadora
                HorizontalDivider(color = Color.Gray, thickness = 1.dp)
            }
        }
    }
}


