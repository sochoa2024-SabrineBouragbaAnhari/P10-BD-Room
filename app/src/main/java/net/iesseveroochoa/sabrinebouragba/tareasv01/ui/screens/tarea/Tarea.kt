package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.tarea

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import net.iesseveroochoa.sabrinebouragba.tareasv01.R
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components.BasicRadioButton
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components.DialogoConfirmacion
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components.DynamicSelectTextField
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components.RatingBar

@SuppressLint("ResourceType")
@Composable
fun TareaScreen(
    modifier: Modifier = Modifier,
    viewModel: TareaViewModel = viewModel()
) {
    val uiStateTarea by viewModel.uiStateTarea.collectAsState()

    //no sé si poner esto está bien, pero me obligaba a ponerlo.
    val snackbarHostState = remember { SnackbarHostState() } // Estado para el Snackbar
    val scope = rememberCoroutineScope() // Scope para lanzar corutinas
    val context = LocalContext.current // Contexto de la aplicación

//    // Carga la lista de categorías desde el string.xml y establece la categoría seleccionada por defecto.
//    val categorias = stringArrayResource(id = R.array.categorias_array).toList()
//    var categoriaSelected by remember { mutableStateOf(categorias[0]) }
//
//    // Carga la lista de prioridades desde el string.xml y establece la prioridad seleccionada por defecto.
//    //val prioridades = stringArrayResource(id = R.array.Prioridad).toList()
//    //var prioridadSelected by remember { mutableStateOf(prioridades[2]) }
//
//    // Variable booleana para controlar si está pagado o no.
//    var checked by remember { mutableStateOf(false) }
//
//    // Carga la lista de estados desde el string.xml y establece el estado seleccionado por defecto.
//    val radioEstado = stringArrayResource(R.array.estadoTarea_array).toList()
//    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioEstado[0]) }
//
//    // Variable para almacenar el valor actual de la valoración del cliente.
//    var currentRating by remember { mutableIntStateOf(0) }
//
//    // Variable para almacenar el valor del técnico seleccionado.
//    var tecnicoValue by remember { mutableStateOf("") }
//
//    // Variable para almacenar el valor de la descripción.
//    var descripcionValue by remember { mutableStateOf("") }

    // Define el color de fondo si la prioridad seleccionada es "Alta".
    //val PRIORIDAD_ALTA = prioridades[0]
    //val backGroundColor = if (PRIORIDAD_ALTA == prioridadSelected) ColorPrioridadAlta else Color.Transparent

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton( onClick = {
                //si están todos los campos rellenos, mostramos el dialogo
                if (uiStateTarea.esFormularioValido)
                    viewModel.onGuardar()
                else {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = context.getString(R.string.label_rellenar),
                            duration = SnackbarDuration.Short
                        )
                    }
                }

            }
            ){
                Icon(
                    //recuperamos el icono del sistema
                    painter = painterResource(id = android.R.drawable.ic_menu_save),
                    contentDescription = stringResource(R.string.label_guardar)
                )
            }
        }
    ) { innerPadding ->
        // Comienza la UI principal.
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding), // Aplica el padding aquí
            color = uiStateTarea.colorFondo
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize() // Ocupa todo el espacio disponible.
            ) {
                // Fila que contiene el selector de categorías y prioridades junto a una imagen.
                Row(
                    verticalAlignment = CenterVertically,
                ) {
                    // Columna que contiene los selectores desplegables de categoría y prioridad.
                    Column(modifier = Modifier.weight(1f)) {
                        DynamicSelectTextField(
                            options = viewModel.listaCategoria,
                            selectedValue = uiStateTarea.categoria,
                            label = stringResource(R.string.label_categoria),
                            onValueChangedEvent = { viewModel.onValueChangeCategoria(it) },
                            modifier = Modifier.padding(0.dp, 50.dp, 0.dp, 0.dp)
                        )

                        DynamicSelectTextField(
                            options = viewModel.listaPrioridad,
                            selectedValue = uiStateTarea.prioridad,
                            label = stringResource(R.string.label_prioridad),
                            onValueChangedEvent = {
                                viewModel.onValueChangePrioridad(it)
                            }
                        )
                    }

                    // Imagen que se ajusta al tamaño y tiene esquinas redondeadas.
                    Image(
                        painter = painterResource(id = R.drawable.techo),
                        contentDescription = R.string.label_imagen.toString(),
                        contentScale = ContentScale.Crop, // Esto asegura que la imagen ocupe todo el contenedor y no quede deformada
                        modifier = Modifier.size(150.dp)
                            .padding(10.dp, 40.dp, 10.dp, 0.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .weight(1f)
                    )
                }

                // Fila que muestra un ícono y un switch para indicar si la tarea está pagada o no.
                Row(verticalAlignment = CenterVertically) {
                    if (uiStateTarea.pagado)
                        Icon(
                            painterResource(R.drawable.ic_pagado),
                            contentDescription = null,
                            modifier = Modifier.padding(8.dp)
                        )
                    else
                        Icon(
                            painterResource(R.drawable.ic_no_pagado),
                            contentDescription = null,
                            modifier = Modifier.padding(8.dp)
                        )

                    Text(text = stringResource(R.string.label_pagado), modifier = Modifier.padding(8.dp))

                    Switch(
                        checked = uiStateTarea.pagado,
                        modifier = Modifier.padding(8.dp),
                        onCheckedChange = { viewModel.onValueChangePagado(it) }
                    )
                }

                // Fila que muestra el estado seleccionado y el ícono correspondiente.
                Row(verticalAlignment = CenterVertically) {
                    Text(
                        text = stringResource(R.string.estado_tarea),
                        modifier = Modifier.padding(8.dp)
                    )

                    when (uiStateTarea.estado) {
                        viewModel.listaEstado[0] -> Icon(
                            painterResource(R.drawable.ic_abierta),
                            contentDescription = null,
                            modifier = Modifier.padding(8.dp))
                        viewModel.listaEstado[1] -> Icon(
                            painterResource(R.drawable.ic_en_curso),
                            contentDescription = null,
                            modifier = Modifier.padding(8.dp))
                        viewModel.listaEstado[2] -> Icon(
                            painterResource(R.drawable.ic_cerrada),
                            contentDescription = null,
                            modifier = Modifier.padding(8.dp))

                    }
                }
                // Radio buttons para seleccionar el estado.
                BasicRadioButton(selectedOption =  uiStateTarea.estado,
                    onOptionSelected = { viewModel.onValueChangeEstado(it) },
                    radioOptions = viewModel.listaEstado)

                // Barra de valoración para indicar la puntuación.
                RatingBar(currentRating = uiStateTarea.valoracion,
                    onRatingChanged = { viewModel.onValueChangeValoracion(it)}
                )

                // Campo de texto para el nombre del técnico.
                ShowOutlinedTextField(
                    label = R.string.tecnico,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    value = uiStateTarea.tecnico,
                    onValueChange = {viewModel.onTecnicoValueChange(it)},
                    modifier = Modifier.fillMaxWidth()
                )

                // Campo de texto para el nombre del descipción.
                ShowOutlinedTextField(
                    label = R.string.descripcion,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    value = uiStateTarea.descripcion,
                    onValueChange = {viewModel.onDescripcionValueChange(it)},
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = false
                )

                // Sección para la descripción con scroll vertical.
                Box(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                ) {
                    //Dialogo de confirmación
                    if (uiStateTarea.mostrarDialogo) {
                        DialogoConfirmacion(
                            onDismissRequest = {
                                //cancela el dialogo
                                viewModel.onCancelarDialogoGuardar()
                            },
                            onConfirmation = {
                                //guardaría los cambios
                                viewModel.onConfirmarDialogoGuardar()
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = context.getString(R.string.label_tareaG),
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            },
                            dialogTitle = stringResource(R.string.label_atencion),
                            dialogText = stringResource(R.string.label_confirmacion),
                            icon = Icons.Default.Info
                        )
                    }
                }
            }
        }
    }
}

/**
 * Muestra un campo de texto con un label y un valor.
 * @param label - El identificador del string para mostrar como etiqueta.
 * @param keyboardOptions - Opciones del teclado.
 * @param value - El valor del campo de texto.
 * @param onValueChange - Callback para cuando el valor cambia.
 * @param singleLine - Indica si el campo de texto es de una sola línea.
 * @param modifier - Modificador para aplicar estilos adicionales.
 */
@Composable
fun ShowOutlinedTextField(
    modifier: Modifier = Modifier,
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChange: (String) -> Unit,
    singleLine: Boolean = true
) {
    OutlinedTextField(
        value = value,
        label = { Text(text = stringResource(label)) },
        onValueChange = onValueChange,
        modifier = modifier,
        keyboardOptions = keyboardOptions,
        singleLine = singleLine
    )
}

@Preview(showBackground = true)
@Composable
fun TareaScreenPreview() {
    TareaScreen()
}