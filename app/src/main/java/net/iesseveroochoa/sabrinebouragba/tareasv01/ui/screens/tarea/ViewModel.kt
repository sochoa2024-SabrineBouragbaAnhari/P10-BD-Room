package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.tarea

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import net.iesseveroochoa.sabrinebouragba.tareasv01.R
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.db.entities.Tarea
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.repository.Repository
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
                img = R.drawable.foto3.toString(),
                pagado = uiStateTarea.value.pagado,
                estado = listaEstado.indexOf(uiStateTarea.value.estado),
                valoracion = uiStateTarea.value.valoracion,
                tecnico = uiStateTarea.value.tecnico,
                descripcion = uiStateTarea.value.descripcion
            ) //si no es nueva, actualiza la tarea
        } else {
            Tarea(
                tarea!!.id,
                categoria = listaCategoria.indexOf(uiStateTarea.value.categoria),
                prioridad = listaPrioridad.indexOf(uiStateTarea.value.prioridad),
                img = tarea!!.img,
                pagado = uiStateTarea.value.pagado,
                estado = listaEstado.indexOf(uiStateTarea.value.estado),
                valoracion = uiStateTarea.value.valoracion,
                tecnico = uiStateTarea.value.tecnico,
                descripcion = uiStateTarea.value.descripcion
            )
        }
    }

    fun getTarea(id: Long) {
        tarea = Repository.getTarea(id)
        //si no es nueva inicia la UI con los valores de la tarea
        if (tarea != null) tareaToUiState(tarea!!)
    }


    class TareaViewModel: ViewModel() {
        private val _uiStateTarea = MutableStateFlow(TareaUIState())
        val uiStateTarea: StateFlow<TareaUIState> = _uiStateTarea.asStateFlow()
    }

}