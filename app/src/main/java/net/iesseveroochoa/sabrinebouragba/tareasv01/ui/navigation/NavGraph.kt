package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.listatareas.ListaTareasScreen
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.tarea.TareaScreen
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.vistaTarea.VistaTareaScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = LISTA_TAREAS_ROUTE
    ) {
        // ruta a la pantalla ListaTareasScreen
        composable(LISTA_TAREAS_ROUTE) {
            ListaTareasScreen(
                onClickNueva = {
                    navController.navigate("$TAREA_ROUTE?posTarea=null")
                },
                onItemModificarClick = { posTarea ->
                    navController.navigate("$TAREA_ROUTE?posTarea=$posTarea")
                },
                onItemVerClick = { posTarea ->
                    navController.navigate("$VISTA_TAREA_ROUTE?posTarea=$posTarea")
                }
            )
        }

        // ruta a la pantalla TareaScreen
        composable("$TAREA_ROUTE?posTarea={posTarea}") { backStackEntry ->
            val posTarea = backStackEntry.arguments?.getString("posTarea")?.toLongOrNull()
            TareaScreen(
                idTarea = posTarea,
                onVolver = {
                    navController.navigateUp()
                },
                onMostrar = {
                    if (posTarea != null)
                        navController.navigate("$VISTA_TAREA_ROUTE?posTarea=$posTarea")
                }
            )
        }

        // ruta a la pantalla VistaTareaScreen
        composable("$VISTA_TAREA_ROUTE?posTarea={posTarea}") { backStackEntry ->
            val posTarea = backStackEntry.arguments?.getString("posTarea")?.toLongOrNull()
            VistaTareaScreen(
                posTarea = posTarea,
                onVolver = {
                    navController.navigateUp()
                },
                onVolverAInicio = {
                    navController.popBackStack(LISTA_TAREAS_ROUTE, inclusive = false)
                }
            )
        }
    }
}
