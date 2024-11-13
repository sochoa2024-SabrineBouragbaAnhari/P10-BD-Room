package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.vistaTarea

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.db.entities.Tarea
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.repository.Repository
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.tempmodel.TempModelTareas

class VistaTareaViewModel {
    private val _uiStateVistaTarea = MutableStateFlow(UiStateVistaTareas())

    fun buscarPalabra(cod: Long?) {
        _uiStateVistaTarea.update {
            it.copy(
                tarea = if (cod == null) "" else TempModelTareas.getTarea(cod)
            )
        }
    }

}

data class UiStateVistaTareas(
    val listaTareas: List<Tarea> = emptyList()
)