package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iesseveroochoa.sabrinebouragba.tareasv01.R
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.theme.ColorPrioridadAlta
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.theme.TareasV01Theme

@Composable
fun MenuDesplegable(modifier: Modifier = Modifier) {
    val categorias = stringArrayResource(R.array.categorias_array)
    val prioridades = stringArrayResource(R.array.prioridades_array)

    var categoriaSeleccionada by remember { mutableStateOf("") }
    var prioridadSeleccionada by remember { mutableStateOf("") }

    var colorFondo= if (prioridadSeleccionada == "Alta") ColorPrioridadAlta else Color.Transparent

    Column (
        modifier = modifier
        .background(colorFondo)
    ){
        DynamiSelectedTextField(
            selectedValue = categoriaSeleccionada,
            options = categorias.toList(),
            label = stringResource(R.string.label_categoria),
            onValueChangedEvent = { categoriaSeleccionada = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        DynamiSelectedTextField(
            selectedValue = prioridadSeleccionada,
            options = prioridades.toList(),
            label = stringResource(R.string.label_prioridad),
            onValueChangedEvent = { prioridadSeleccionada = it }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DynamiSelectedTextField(
    selectedValue: String,
    options: List<String>,
    label: String,
    onValueChangedEvent: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selectedValue,
            onValueChange = {},
            label = { Text(text = label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = OutlinedTextFieldDefaults.colors(),
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        expanded = false
                        onValueChangedEvent(option)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaInicialPreview() {
    TareasV01Theme {
        MenuDesplegable()
    }
}