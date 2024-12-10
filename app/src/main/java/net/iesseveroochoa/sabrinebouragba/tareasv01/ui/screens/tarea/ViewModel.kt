package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.tarea

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import net.iesseveroochoa.sabrinebouragba.tareasv01.R
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.db.entities.Tarea
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.repository.Repository
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.repository.Repository.delTarea
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.listatareas.UiStateDialogo
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.theme.ColorPrioridadAlta

class TareaViewModel(application: Application): AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private val context = application.applicationContext

    // Listas desde strings.xml
    val listaPrioridad = context.resources.getStringArray(R.array.prioridades_array).toList()
    val listaCategoria = context.resources.getStringArray(R.array.categorias_array).toList()
    val listaEstado = context.resources.getStringArray(R.array.estadoTarea_array).toList()

    // Tarea
    var tarea: Tarea? = null

    // Prioridad Alta
    val PRIORIDAD_ALTA: String = listaPrioridad[0]

    // Estado inicial de la UI
    private val _uiStateTarea = MutableStateFlow(
        UiStateTarea(
            prioridad = listaPrioridad[2],
            categoria = listaCategoria[0],
            estado = listaEstado[0]
        )
    )
    val uiStateTarea: StateFlow<UiStateTarea> = _uiStateTarea.asStateFlow()

    // Estado de la UI
    private val _uiState = MutableStateFlow(UiStateDialogo())
    val uiState: StateFlow<UiStateDialogo> = _uiState.asStateFlow()

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

    fun onValueChangeCategoria(nuevaCategoria: String) {
        _uiStateTarea.value = _uiStateTarea.value.copy(categoria = nuevaCategoria)
    }

    fun onValueChangePagado(nuevoPagado: Boolean) {
        _uiStateTarea.value = _uiStateTarea.value.copy(pagado = nuevoPagado)
    }

    fun onValueChangeValoracion(nuevaValoracion: Int) {
        _uiStateTarea.value = _uiStateTarea.value.copy(valoracion = nuevaValoracion)
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

    /**
     * Guardar tarea en la BD
     */
    fun onConfirmarDialogoGuardar() {
        //cierra el dialogo
        _uiStateTarea.value = _uiStateTarea.value.copy(
            mostrarDialogo = false
        )
        //lanzamos la corrutina para guardar la tarea
        viewModelScope.launch(Dispatchers.IO) {
            guardarTarea()
        }
    }
    //cierra el dialogo
    fun onCancelarDialogoGuardar() {
        _uiStateTarea.value = _uiStateTarea.value.copy(
            mostrarDialogo = false
        )
    }

    // Metodos para usar los datos de Tarea ----------------------

    fun tareaToUiState(tarea: Tarea) {
        _uiStateTarea.value = _uiStateTarea.value.copy(
            categoria = listaCategoria[tarea.categoria],
            prioridad = listaPrioridad[tarea.prioridad],
            pagado = tarea.pagado,
            estado = listaEstado[tarea.estado],
            valoracion = tarea.valoracion,
            tecnico = tarea.tecnico,
            descripcion = tarea.descripcion,
            esFormularioValido = tarea.tecnico.isNotBlank() &&
                    tarea.descripcion.isNotBlank(),
            esTareaNueva = false,
            colorFondo = if (PRIORIDAD_ALTA == listaPrioridad[tarea.prioridad])
                ColorPrioridadAlta else Color.Transparent
        )
    }

    fun uiStateToTarea(): Tarea {
        return if (uiStateTarea.value.esTareaNueva) {
            //si es nueva, le asigna un id
            Tarea(
                categoria = listaCategoria.indexOf(uiStateTarea.value.categoria),
                prioridad = listaPrioridad.indexOf(uiStateTarea.value.prioridad),
                img = tarea?.img ?: "",
                pagado = uiStateTarea.value.pagado,
                estado = listaEstado.indexOf(uiStateTarea.value.estado),
                valoracion = uiStateTarea.value.valoracion,
                tecnico = uiStateTarea.value.tecnico,
                descripcion = uiStateTarea.value.descripcion
            ) //si no es nueva, actualiza la tarea
        } else {
            Tarea(
                tarea?.id ?: 0L,
                categoria = listaCategoria.indexOf(uiStateTarea.value.categoria),
                prioridad = listaPrioridad.indexOf(uiStateTarea.value.prioridad),
                img = tarea?.img ?: "",
                pagado = uiStateTarea.value.pagado,
                estado = listaEstado.indexOf(uiStateTarea.value.estado),
                valoracion = uiStateTarea.value.valoracion,
                tecnico = uiStateTarea.value.tecnico,
                descripcion = uiStateTarea.value.descripcion
            )
        }
    }

    fun getTarea(id: Long) {
        // Lanzamos corrutina que nos devuelve la tarea de la BD
        viewModelScope.launch(Dispatchers.IO) {
            tarea = Repository.getTarea(id)
            if (tarea != null) {
                tareaToUiState(tarea!!)
            } else {
                // tarea no encontrada
                _uiStateTarea.value = _uiStateTarea.value.copy(
                    esTareaNueva = true, // Si no se encuentra, se asume como nueva
                    descripcion = "",
                    tecnico = ""
                )
            }
        }
    }

    suspend fun guardarTarea() {
        val tarea = uiStateToTarea() // Convertimos el estado a un objeto Tarea
        if (uiStateTarea.value.esTareaNueva) {
            // Llamar al repositorio para guardar la nueva tarea
            Repository.addTarea(tarea)
        } else {
            // Llamar al repositorio para actualizar la tarea existente
            Repository.addTarea(tarea)
        }
    }

    // Función para mostrar el diálogo de borrar
    fun onMostrarDialogoBorrar(tarea: Tarea) {
        _uiState.value = _uiState.value.copy(
            mostrarDialogoBorrar = true,
            tareaBorrar = tarea
        )
    }

    // Función para cancelar el diálogo
    fun cancelarDialogo() {
        _uiState.value = _uiState.value.copy(mostrarDialogoBorrar = false)
    }

    // Función para aceptar el borrado de la tarea
    fun aceptarDialogo() {
        _uiState.value.tareaBorrar?.let {
            // Llamamos al repositorio para eliminar la tarea
            viewModelScope.launch(Dispatchers.IO) {
                delTarea(it) // Elimina la tarea de la base de datos
            }
        }
        // Cierra el diálogo
        _uiState.value = _uiState.value.copy(
            mostrarDialogoBorrar = false,
            tareaBorrar = null
        )
    }
}