package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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


    val colorFondo = if (prioridadSeleccionada == "Alta") ColorPrioridadAlta else Color.Transparent

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colorFondo)
            .padding(8.dp) //Separar los elementos de los bordes
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween // Distribuye los elementos
        ) {
            Column(modifier = Modifier.weight(1f)) {
                DynamiSelectedTextField(
                    selectedValue = categoriaSeleccionada,
                    options = categorias.toList(),
                    label = stringResource(R.string.label_categoria),
                    onValueChangedEvent = { categoriaSeleccionada = it },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                DynamiSelectedTextField(
                    selectedValue = prioridadSeleccionada,
                    options = prioridades.toList(),
                    label = stringResource(R.string.label_prioridad),
                    onValueChangedEvent = { prioridadSeleccionada = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.width(16.dp)) // Añadir espacio entre los dropdowns y la imagen

            Image(
                painter = painterResource(R.drawable.techo),
                contentDescription = "Imagen de un techo",
                modifier = Modifier
                    .weight(1f) // Imagen ocupará el mismo espacio que las columnas anteriores
                    .clip(RoundedCornerShape(35.dp))
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        ControlSwitch()

        Spacer(modifier = Modifier.height(16.dp))

        EstadoTarea()
    }

}

@Composable
fun ControlSwitch(){
    var estaPagado by remember { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = if (estaPagado) {
                painterResource(R.drawable.ic_pagado)
            } else {
                painterResource(R.drawable.ic_no_pagado)
            },
            contentDescription = "Imagen de un icono de pago",
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(10.dp)) // Ajuste para que el texto no esté pegado al icono

        Text(
            text = if (estaPagado) {
                stringResource(R.string.label_pagado)
            } else {
                stringResource(R.string.label_noPagado)
            }
        )

        Spacer(modifier = Modifier.width(10.dp))

        Row {
            Switch(
                checked = estaPagado,
                onCheckedChange = { estaPagado = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Green,
                    uncheckedThumbColor = Color.Red
                )
            )
        }

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