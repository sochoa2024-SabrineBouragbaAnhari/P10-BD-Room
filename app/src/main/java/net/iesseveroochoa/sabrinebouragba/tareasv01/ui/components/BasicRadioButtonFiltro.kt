package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components

import androidx.benchmark.perfetto.Row
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun BasicRadioButtonFiltro(
    listaOpciones: List<String>, // Lista de opciones de los radio buttons.
    operacionSeleccionada: String, // La opción actualmente seleccionada.
    onOptionSelected: (String) -> Unit, // Función de callback que se ejecuta cuando se selecciona una nueva opción. // Modificador opcional para personalizar el diseño.
) {
    // Crea una fila para alinear horizontalmente los elementos.
    Row {
        // Itera sobre cada opción en la lista de opciones.
        listaOpciones.forEach { opcion ->
            Row(
                modifier = Modifier
                    .padding(8.dp) // Aplica un padding alrededor de cada opción.
                    .height(56.dp) // Define la altura de cada fila de opción.
                    .selectable(
                        // Comprueba si la opción actual es la seleccionada.
                        selected = (opcion == operacionSeleccionada),
                        // Llama a la función cuando se selecciona la opción.
                        onClick = { onOptionSelected(opcion) },
                        // Define el papel de este elemento como un botón de radio.
                        role = Role.RadioButton
                    ),
                verticalAlignment = Alignment.CenterVertically // Alinea los elementos verticalmente al centro.
            ) {
                // Renderiza el botón de radio.
                RadioButton(
                    // Marca el botón de radio si la opción actual está seleccionada.
                    selected = (opcion == operacionSeleccionada),
                    // Elimina la acción de clic del propio radio button, ya que se maneja en el contenedor Row.
                    onClick = null
                )

                Text(
                    text = opcion,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}