package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.listatareas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.repository.Repository
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.tarea.UiStateTarea

class ListaViewModel: ViewModel() {
    val listaTareasUiState: StateFlow<ListaUiState> =
        Repository.getAllTareas().map {ListaUiState(it)}.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ListaUiState()
        )
}