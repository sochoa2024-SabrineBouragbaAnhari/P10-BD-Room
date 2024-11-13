package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.listatareas.ListaTareasScreen
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.tarea.TareaScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ListaTareasDestination
    ) {
        composable<ListaTareasDestination> {
            ListaTareasScreen(
                onClickNueva = {
                    navController.navigate(TareaDestination())
                },
                onItemModificarClick = {posTarea ->
                    navController.navigate(TareaDestination(posTarea))
                },
                onItemVerClick = {posTarea ->
                    navController.navigate(VistaTareasDestination(posTarea))
                }
            )
        }

        composable<TareaDestination> {backStackEntry ->
            val tarea: TareaDestination = backStackEntry.toRoute()
            TareaScreen(
                idTarea = tarea.posTarea,
                onVolver = {
                    navController.navigateUp()
                },
                onMostrar = {
                    if (tarea.posTarea != null) {
                        navController.navigate(VistaTareasDestination(tarea.posTarea))
                    }
                }
            )
        }

        composable<VistaTareasDestination> {backStackEntry ->
            val tarea: VistaTareasDestination = backStackEntry.toRoute()
            TareaScreen(
                idTarea = tarea.posTarea,
                onVolver = {
                    navController.navigateUp()
                },
                onVolverAInicio = {

                }
            )
        }

    }
}