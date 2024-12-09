package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.listatareas

import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
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
        modifier = modifier
            .height(120.dp)
        ,
        colors = CardDefaults.cardColors(color)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen de la tarea
            Image(
                painter = painterResource(id = R.drawable.foto3),
                contentDescription = "Imagen",
                modifier = Modifier
                    .size(width = 100.dp, height = 120.dp),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop
            )

            // Información de la tarea
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
                ,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Categoría y estado
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(getEstado(tarea.estado)),
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
                    style = androidx.compose.ui.text.TextStyle(fontSize = 16.sp, color = Color.Blue),
                    modifier = Modifier.padding(vertical = 2.dp)
                )

                // Descripción
                Text(
                    text = tarea.descripcion,
                    maxLines = 2,
                    style = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                    modifier = Modifier.padding(vertical = 2.dp),
                    overflow = TextOverflow.Ellipsis
                )
            }

            // ID
            Column(
                modifier = Modifier
                    .padding(8.dp)
                ,
                verticalArrangement = Arrangement.Top
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
fun getEstado(estado: Int): Int {
    return when(estado) {
        0 -> R.drawable.ic_abierta
        1 -> R.drawable.ic_en_curso
        else -> R.drawable.ic_cerrada
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

@Preview
@Composable
fun PreviewItemCard() {
    ItemCard(
        tarea = Tarea(
            categoria = 0,
            prioridad = 0,
            img = "foto1",
            pagado = true,
            estado = 0,
            valoracion = 5,
            tecnico = "Pepe Gotero",
            descripcion = "Descripción de la tarea 1: Lorem ipsum dolor sit amet."
        ),
        color = ColorPrioridadAlta
    )
}