package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.listatareas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.db.entities.Tarea
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.repository.Repository
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.tempmodel.TempModelTareas

class ListaViewModel : ViewModel() {

//    val listaTareasUiState = TempModelTareas.getAllTareas().stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.WhileSubscribed(5000),
//        initialValue = emptyList()
//    )

    val listaTareasUiState : StateFlow<ListaUiState> = Repository.getAllTareas()
        .map { listasTareas ->
            ListaUiState(listaTareas = listasTareas)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ListaUiState()
        )

    /**
     * Borra una tarea de la base de datos
     */
    fun delTarea(tarea: Tarea) {
        viewModelScope.launch(Dispatchers.IO) {
            Repository.delTarea(tarea)
        }
    }

//    init {
//        // Inicializa las tareas de prueba si la lista está vacía
//        if (TempModelTareas.listaTareasDestination.isEmpty()) {
//            TempModelTareas.iniciaPruebaTareas()
//        }
//    }
}