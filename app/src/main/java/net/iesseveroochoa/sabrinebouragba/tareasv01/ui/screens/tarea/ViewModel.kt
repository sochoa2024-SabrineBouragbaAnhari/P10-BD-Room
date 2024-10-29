package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.tarea

import android.app.Application
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import net.iesseveroochoa.sabrinebouragba.tareasv01.R
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.theme.ColorPrioridadAlta

class TareaViewModel(application: Application): AndroidViewModel(application) {
    private val context = application.applicationContext

    // Listas desde strings.xml
    val listaPrioridad = context.resources.getStringArray(R.array.prioridades_array).toList()
    val listaCategoria = context.resources.getStringArray(R.array.categorias_array).toList()
    val listaEstado = context.resources.getStringArray(R.array.estadoTarea_array).toList()

    // Prioridad Alta
    val PRIORIDAD_ALTA = listaPrioridad[0]

    // Estado inicial de la UI
    private val _uiStateTarea = MutableStateFlow(
        UiStateTarea(
            prioridad = listaPrioridad[2],
            categoria = listaCategoria[0],
            estado = listaEstado[0]
        )
    )
    val uiStateTarea: StateFlow<UiStateTarea> = _uiStateTarea.asStateFlow()

    // Funciones para actualizar cada prioridad en la UI
    fun onValueChangePrioridad(nuevaPrioridad: String) {
        val colorFondo: Color
        if(PRIORIDAD_ALTA == nuevaPrioridad)
            colorFondo = ColorPrioridadAlta
        else
            colorFondo = Color.Transparent

        _uiStateTarea.value = _uiStateTarea.value.copy(
            prioridad = nuevaPrioridad,
            colorFondo = colorFondo
        )
    }

    // Funciones para actualizar cada estado en la UI
    fun onValueChangeEstado(nuevoEstado: String) {
        _uiStateTarea.value = _uiStateTarea.value.copy(estado = nuevoEstado)
    }

    fun onTecnicoValueChange(nuevoTecnico: String) {
        _uiStateTarea.value = _uiStateTarea.value.copy(
            tecnico = nuevoTecnico,
            esFormularioValido = nuevoTecnico.isNotBlank() && _uiStateTarea.value.descripcion.isNotBlank())
    }

    fun onDescripcionValueChange(nuevaDescripcion: String) {
        _uiStateTarea.value = _uiStateTarea.value.copy(
            descripcion = nuevaDescripcion,
            esFormularioValido = nuevaDescripcion.isNotBlank() && _uiStateTarea.value.descripcion.isNotBlank())
    }

    //muestra el dialogo
    fun onGuardar() {
        _uiStateTarea.value = _uiStateTarea.value.copy(
            mostrarDialogo = true
        )
    }
    //guardará los cambios, por el momento solo cierra el dialogo
    fun onConfirmarDialogoGuardar() {
        _uiStateTarea.value = _uiStateTarea.value.copy(
            mostrarDialogo = false
        )
    }
    //cierra el dialogo
    fun onCancelarDialogoGuardar() {
        _uiStateTarea.value = _uiStateTarea.value.copy(
            mostrarDialogo = false
        )
    }
}