package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.tarea

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iesseveroochoa.sabrinebouragba.tareasv01.R
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components.MenuDesplegable
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.theme.TareasV01Theme

@Composable
fun PantallaInicial() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp, top = 25.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            MenuDesplegable(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )

            Image(
                painter = painterResource(R.drawable.techo),
                contentDescription = "Imagen de un techo",
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

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