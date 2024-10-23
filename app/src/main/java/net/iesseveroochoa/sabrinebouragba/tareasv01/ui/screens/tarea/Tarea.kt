package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.tarea

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iesseveroochoa.sabrinebouragba.tareasv01.R
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.theme.TareasV01Theme
import androidx.compose.runtime.Composable

@Composable
fun PantallaInicial() {
    val categorias = stringArrayResource(R.array.categorias_array)
    val prioridades = stringArrayResource(R.array.prioridades_array)

    var categoriaSeleccionada by remember { mutableStateOf("") }
    var prioridadSeleccionada by remember { mutableStateOf("") }

    Column (
        modifier = Modifier.padding(8.dp)
    ){
        DropdownMenus(
            label = stringResource(R.string.label_categoria),
            opciones = categorias.toList(),
            seleccion = { categoriaSeleccionada = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        DropdownMenus(
            label = stringResource(R.string.label_prioridad),
            opciones = prioridades.toList(),
            seleccion = { prioridadSeleccionada = it }
        )
    }
}

@Composable
fun DropdownMenus(label: String, opciones: List<String>, seleccion: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var seleccionActual by remember { mutableStateOf(opciones[0]) }

    Column {
        Text(text = label)
        Box(modifier = Modifier.fillMaxWidth().wrapContentSize(Alignment.TopStart)) {
            Button(onClick = { expanded = true }) {
                Text(text = seleccionActual)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
               opciones.forEach { opcion ->
                   DropdownMenuItem(
                       text = { Text(text = opcion) },
                       onClick = {
                           seleccionActual = opcion
                           seleccion(opcion)
                           expanded = false
                       }
                   )
               }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaInicialPreview() {
    TareasV01Theme {
        PantallaInicial()
    }
}