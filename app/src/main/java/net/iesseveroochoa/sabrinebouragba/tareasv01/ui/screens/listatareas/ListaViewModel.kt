package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.listatareas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.db.entities.Tarea
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.repository.Repository
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.tempmodel.TempModelTareas

class ListaViewModel : ViewModel() {
    val listaTareasUiState = TempModelTareas.getAllTareas().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    init {
        // Inicializa las tareas de prueba si la lista está vacía
        if (TempModelTareas.listaTareasDestination.isEmpty()) {
            TempModelTareas.iniciaPruebaTareas()
        }
    }
}