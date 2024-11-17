package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.listatareas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.iesseveroochoa.sabrinebouragba.tareasv01.R
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.db.entities.Tarea
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.theme.ColorPrioridadAlta

@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    color: Color,
    tarea: Tarea
) {
    Card(
        modifier = modifier.height(120.dp),
        colors = CardDefaults.cardColors(color)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.Top
        ) {
            // Imagen de la tarea
            Icon(
                painter = painterResource(id = getImagen(tarea.img)),
                contentDescription = "Imagen",
                modifier = Modifier
                    .size(width = 100.dp, height = 120.dp)
                    .padding(end = 8.dp)
            )

            // Información de la tarea
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Categoría y estado
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(getEstado(tarea.estado).toInt()),
                        contentDescription = "Estado",
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = getCategoria(tarea.categoria),
                        style = androidx.compose.ui.text.TextStyle(color = Color.DarkGray),
                    )
                }

                // Técnico
                Text(
                    text = tarea.tecnico,
                    style = androidx.compose.ui.text.TextStyle(fontSize = 16.sp),
                    modifier = Modifier.padding(vertical = 2.dp)
                )

                // Descripción
                Text(
                    text = tarea.descripcion
                )
            }

            // ID
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(text = tarea.id.toString())
            }
        }
    }
}

@Composable
fun getPrioridad(prioridad: Int): Color {
    return when(prioridad) {
        0 -> ColorPrioridadAlta
        else -> Color.LightGray
    }
}

@Composable
fun getEstado(estado: Int): String {
    return when(estado) {
        0 -> R.drawable.ic_abierta.toString()
        1 -> R.drawable.ic_en_curso.toString()
        else -> R.drawable.ic_cerrada.toString()
    }
}

@Composable
fun getCategoria(categoria: Int): String {
    return when(categoria) {
        0 -> "Reparación"
        1 -> "Instalación"
        2 -> "Mantenimiento"
        3 -> "Comercial"
        else -> "Otro"
    }
}

@Composable
fun getImagen(imagen: String): Int {
    return when (imagen) {
        "foto1" -> R.drawable.foto1
        "foto2" -> R.drawable.foto2
        "foto3" -> R.drawable.foto3
        else -> R.drawable.foto4
    }
}
