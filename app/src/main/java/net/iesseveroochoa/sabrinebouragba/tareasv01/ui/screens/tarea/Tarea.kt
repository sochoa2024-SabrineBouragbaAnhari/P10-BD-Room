package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.tarea

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components.ControlSwitch
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components.EstadoTarea
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components.MenuDesplegable
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components.RatingBar
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components.UnaLinea
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components.VariasLineas
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.theme.TareasV01Theme

@Composable
fun PantallaInicial() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp) // Espaciado global para evitar que los elementos queden pegados a los bordes
    ) {
        // Fila para el menú desplegable
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            MenuDesplegable()
        }

        Spacer(modifier = Modifier.height(16.dp)) // Espaciado entre secciones

        // Fila para el control switch
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically // Alinea los elementos verticalmente
        ) {
            ControlSwitch()
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Fila para el estado de la tarea
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            EstadoTarea()
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Fila para la valoración con estrellas (RatingBar)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Valoración:")
            Spacer(modifier = Modifier.width(8.dp))
            RatingBar(onRatingChanged = {})
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Fila para el campo de texto
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            UnaLinea()
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Fila para el campo de texto multilínea
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            VariasLineas()
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
