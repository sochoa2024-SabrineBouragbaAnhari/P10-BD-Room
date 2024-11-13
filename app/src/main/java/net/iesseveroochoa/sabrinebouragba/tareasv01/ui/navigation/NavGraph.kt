package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.listatareas.ListaTareasScreen
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.tarea.TareaScreen
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.vistaTarea.VistaTareaScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        // pantalla de inicio
        startDestination = ListaTareasDestination
    ) {
        // ruta a la pantalla ListaTareasScreen. Pantalla inicial de la app
        composable<ListaTareasDestination> {
            ListaTareasScreen(
                onClickNueva = {
                    // Navegamos a la pantalla TareaScreen. Pasamos null porque es una nueva tarea
                    navController.navigate(TareaDestination())
                },
                // Navegamos a la pantalla Tarea editando una tarea existente.
                // Pasamos la posición de la tarea en la lista
                onItemModificarClick = { posTarea ->
                    navController.navigate(TareaDestination(posTarea))
                },
                // Navegamos a la pantalla VistaTareaScreen.
                // Pasamos la posición de la tarea en la lista
                onItemVerClick = { posTarea ->
                    navController.navigate(VistaTareasDestination(posTarea.toLong()))
                }
            )
        }
        // ruta a la pantalla TareaScreen.
        // backStackEntry contiene los parámetros pasados en la navegación
        composable<TareaDestination> { backStackEntry ->
            // recuperamos el parámetro posTarea de la navegación
            val tarea: TareaDestination = backStackEntry.toRoute()
            TareaScreen(
                idTarea = tarea.posTarea,
                // pasamos la lambda para volver a la pantalla anterior
                onVolver = {
                    navController.navigateUp()
                },
                // pasamos la lambda para navegar a la pantalla de vista tarea si no es nueva
                onMostrar = {
                    if (tarea.posTarea != null)
                        navController.navigate(VistaTareasDestination(tarea.posTarea))
                }
            )
        }
        // ruta a la pantalla VistaTareaScreen.
        composable<VistaTareasDestination> { backStackEntry ->
            // recuperamos el parámetro posTarea de la navegación
            val tarea: VistaTareasDestination = backStackEntry.toRoute()
            VistaTareaScreen(
                posTarea = tarea.posTarea,
                onVolver = {
                    navController.navigateUp()
                },
                onVolverAInicio = {
                    // vaciamos la pila de navegación y mostramos la pantalla de inicio
                    navController.popBackStack(
                        ListaTareasDestination,
                        inclusive = false
                    )
                }
            )
        }
    }
}