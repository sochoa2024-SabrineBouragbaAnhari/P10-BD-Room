package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.tarea

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components.EstadoTarea
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components.MenuDesplegable
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.theme.TareasV01Theme

@Composable
fun PantallaInicial() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp, top = 20.dp)
    ) {
        MenuDesplegable()
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaInicialPreview() {
    TareasV01Theme {
        PantallaInicial()
    }
}