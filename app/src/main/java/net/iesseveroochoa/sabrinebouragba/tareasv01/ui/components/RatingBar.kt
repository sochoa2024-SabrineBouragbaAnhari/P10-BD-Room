package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import net.iesseveroochoa.sabrinebouragba.tareasv01.R

@Composable
fun RatingBar(
    rmaxRating: Int = 5, // Número máximo de estrellas o íconos en la barra de rating, por defecto es 5.
    currentRating: Int, // El rating actual que está seleccionado.
    modifier: Modifier = Modifier, // Modificador opcional para personalizar el diseño del Icon.
    onRatingChanged: (Int) -> Unit, // Función de callback que se ejecuta cuando cambia el rating.
    iconSelect: ImageVector = Icons.Filled.Favorite, // Ícono para una estrella seleccionada (por defecto es un corazón).
    iconUnSelect: ImageVector = Icons.Filled.FavoriteBorder, // Ícono para una estrella no seleccionada.
    color: Color = Color.Blue // El color de las estrellas (por defecto azul).
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        // Texto que muestra "Valoración" a la izquierda de la barra de estrellas.
        Text(text = stringResource(R.string.valoracion), modifier = Modifier.padding(end = 8.dp))
//
//        // Itera desde 1 hasta el número máximo de estrellas (rmaxRating).
//        for (i in 1..rmaxRating) {
//            Icon(
//                imageVector = if (i <= currentRating)
//                    iconSelect // Muestra el ícono seleccionado si la estrella está igual o por debajo del rating actual.
//                else
//                    iconUnSelect, // Muestra el ícono no seleccionado si la estrella está por encima del rating actual.
//                contentDescription = "Star $i", // Descripción accesible para cada estrella, útil para accesibilidad.
//                modifier = modifier
//                    .clickable { // Hace que la estrella sea clicable.
//                        onRatingChanged(
//                            // Si la primera estrella está seleccionada y el rating es 1, permite deseleccionar el rating.
//                            if (i == 1 && currentRating == 1)
//                                0 // Si la primera estrella está seleccionada y el rating es 1, cambia a 0 (deseleccionado).
//                            else
//                                i // Si es cualquier otra estrella, actualiza el rating al número de la estrella seleccionada.
//                        )
//                    }
//                    .size(32.dp), // Define el tamaño de cada estrella.
//                tint = color // Aplica el color definido para las estrellas (por defecto azul).
//            )
//        }
        for (i in 1..rmaxRating) {
            Icon(
                imageVector = if (i <= currentRating)
                //Icons.Filled.Star
                    iconSelect
                else
                //Icons.Outlined.Star,
                    iconUnSelect,
                contentDescription = "Star $i",
                modifier = modifier
                    .clickable { onRatingChanged(
                        //si es la primera posición y está en 1, devuelve 0
                        if(i==1 && currentRating==1)
                            0
                        else
                            i) },
                tint = color
            )
        }
    }
}