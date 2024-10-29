package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DynamicSelectTextField(
    options: List<String>, // Lista de opciones que se mostrarán en el menú desplegable.
    selectedValue: String, // Valor seleccionado actualmente en el menú desplegable.
    label: String, // Etiqueta que describe el campo.
    onValueChangedEvent: (String) -> Unit, // Callback que se invoca cuando el valor cambia.
    modifier: Modifier = Modifier // Modificador opcional para personalizar el componente.
) {
    // Variable que indica si el menú desplegable está expandido o no.
    var expanded by remember { mutableStateOf(false) }

    // Caja que contiene el menú desplegable y el campo de texto.
    ExposedDropdownMenuBox(
        expanded = expanded, // Controla si el menú está expandido.
        onExpandedChange = { expanded = !expanded }, // Cambia el estado de expansión cuando se interactúa.
        modifier = modifier // Aplica el modificador pasado.
    ) {
        // Campo de texto que muestra el valor seleccionado y abre el menú desplegable cuando se hace clic.
        OutlinedTextField(
            value = selectedValue, // Muestra el valor seleccionado.
            onValueChange = { }, // No se permite modificar el valor manualmente.
            readOnly = true, // El campo es de solo lectura.
            label = { Text(text = label) }, // Muestra la etiqueta que describe el campo.
            trailingIcon = {
                // Icono de flecha que muestra el estado del menú desplegable (expandido o no).
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = OutlinedTextFieldDefaults.colors(), // Define los colores predeterminados del campo de texto.
            modifier = Modifier
                .menuAnchor() // Asegura que el menú esté anclado al campo de texto.
                .fillMaxWidth() // El campo de texto ocupa todo el ancho disponible.
        )

        // Menú desplegable que muestra las opciones disponibles.
        ExposedDropdownMenu(
            expanded = expanded, // Controla si el menú está visible.
            onDismissRequest = { expanded = false } // Cierra el menú cuando se hace clic fuera de él.
        ) {
            // Itera sobre cada opción en la lista de opciones.
            options.forEach { option ->
                // Cada opción se representa como un elemento del menú.
                DropdownMenuItem(
                    text = { Text(text = option) }, // Muestra el texto de la opción.
                    onClick = {
                        // Llama al callback para actualizar el valor seleccionado cuando se selecciona una opción.
                        onValueChangedEvent(option)
                        expanded = false // Cierra el menú después de seleccionar una opción.
                    }
                )
            }
        }
    }
}