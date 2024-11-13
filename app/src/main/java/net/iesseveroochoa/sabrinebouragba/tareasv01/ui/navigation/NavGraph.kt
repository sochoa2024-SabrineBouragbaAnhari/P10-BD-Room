package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.listatareas.ListaTareasScreen
import net.iesseveroochoa.sabrinebouragba.tareasv01.ui.screens.tarea.TareaScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val listaTareas = "lista_tareas"
    val rutaTareas = "tarea"
    val vistaTareas = "vista_tarea"

    NavHost(
        navController = navController,
        startDestination = listaTareas
    ) {
        composable(listaTareas) {
            ListaTareasScreen(
                onClickNueva = {
                    navController.navigate(rutaTareas)
                },
                onItemModificarClick = { posTarea ->
                    navController.navigate("$rutaTareas/$posTarea")
                }
            )
        }

        composable("$rutaTareas/{posTarea}") { backStackEntry ->
            val posTarea = backStackEntry.arguments?.getString("posTarea")?.toLongOrNull()
            TareaScreen(
                idTarea = posTarea,
                onVolver = { navController.navigateUp() }
            )
        }

        composable("$vistaTareas/{posTarea}") { backStackEntry ->
            val posTarea = backStackEntry.arguments?.getString("posTarea")?.toLongOrNull()
            TareaScreen(
                idTarea = posTarea,
                onVolver = { navController.navigateUp() }
            )
        }
    }

}