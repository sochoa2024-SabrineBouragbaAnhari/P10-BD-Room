package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.vistaTarea

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.db.entities.Tarea
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.tempmodel.TempModelTareas

class VistaTareaViewModel: ViewModel() {
    private val _uiStateVistaTarea = MutableStateFlow(UiStateVistaTareas())
    val uiStateVistaTarea: StateFlow<UiStateVistaTareas> = _uiStateVistaTarea.asStateFlow()

    fun buscarTarea(posTarea: Long?) {
        val tarea = posTarea?.let { TempModelTareas.getTarea(it) }
        _uiStateVistaTarea.update {
            it.copy(tarea = tarea)
        }
    }
}

data class UiStateVistaTareas(
    val tarea: Tarea? = null
)