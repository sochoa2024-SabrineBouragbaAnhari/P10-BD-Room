package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.listatareas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import net.iesseveroochoa.sabrinebouragba.tareasv01.data.repository.Repository

class ListaViewModel: ViewModel() {
    //Lista de filtro estado
    //val listaFiltroEstado = context.resources.getStringArray(R.array.filtro_estado_tarea).toList()

    // Variables estáticas para los estados de las tareas
    companion object {
        val ESTADO_ABIERTA = "Abierta"
        val ESTADO_EN_CURSO = "En curso"
        val ESTADO_CERRADA = "Cerrada"
        val ESTADO_TODAS = "Todas"
    }

    val listaFiltroEstado = listOf(
        ESTADO_ABIERTA,
        ESTADO_EN_CURSO,
        ESTADO_CERRADA,
        ESTADO_TODAS
    )

    val uiStateFiltro = MutableStateFlow(
        UiStateFiltro(
            filtroEstrado = listaFiltroEstado[3]
        )
    )

    val listaTareasUiState: StateFlow<ListaUiState> = uiStateFiltro
        .flatMapLatest { filtroEstado ->
            when (filtroEstado.filtroEstrado) {
                ESTADO_ABIERTA -> Repository.getTareasByEstado(0)
                ESTADO_EN_CURSO -> Repository.getTareasByEstado(1)
                ESTADO_CERRADA -> Repository.getTareasByEstado(2)
                else -> Repository.getAllTareas()
            }.map { listasTareas ->
                ListaUiState(listaTareas = listasTareas)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ListaUiState()
        )

    fun onCheckedChangedFiltroEstado(nuevoFiltroEstado: String){
        uiStateFiltro.value = uiStateFiltro.value.copy(
            filtroEstrado = nuevoFiltroEstado
        )
    }

}