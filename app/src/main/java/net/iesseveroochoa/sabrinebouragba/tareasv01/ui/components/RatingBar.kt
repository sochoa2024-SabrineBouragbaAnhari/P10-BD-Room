package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RatingBar(onRatingChanged: (Int) -> Unit){
    val seleccion= remember { mutableStateOf(0) }

    Row {
        for (i in 1..5){
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(4.dp)
                    .clickable {
                        seleccion.value = i
                        onRatingChanged(i)
                    },
                tint = if (i <= seleccion.value) Color.Yellow else Color.Gray
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun RatingBarPreview() {
    RatingBar(onRatingChanged = {})
}