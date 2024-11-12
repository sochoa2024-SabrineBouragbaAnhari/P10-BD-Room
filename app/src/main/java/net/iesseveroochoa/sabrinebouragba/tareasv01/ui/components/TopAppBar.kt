package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import net.iesseveroochoa.sabrinebouragba.tareasv01.R

/**
 * Barra de navegación superior de la app
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    tituloPantallaActual: String,
    puedeNavegarAtras: Boolean,
    navegaAtras: () -> Unit={},
    modifier: Modifier = Modifier
) {
    TopAppBar(
        //Recuperamos el título del enum AppScreen
        title = { Text(text = tituloPantallaActual) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            //si es la primera pantalla no se muestra el botón de navegación
            if (puedeNavegarAtras) {
                //lambda que iría a la pantalla anterior
                IconButton(onClick = navegaAtras) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        //recuerda que el texto tiene que ir en string.xml
                        contentDescription = R.string.label_atras.toString()
                    )
                }
            }
        }
    )
}