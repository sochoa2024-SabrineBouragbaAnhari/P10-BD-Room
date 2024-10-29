package net.iesseveroochoa.sabrinebouragba.tareasv01.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iesseveroochoa.sabrinebouragba.tareasv01.R

@Composable
fun BasicRadioButton(){
    val estadosTarea= stringArrayResource(R.array.estadoTarea_array)
    var estadoTarea by remember { mutableStateOf(estadosTarea[0]) }
    Column()
    {
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = stringResource(R.string.label_estado_tarea))

            Spacer(modifier = Modifier.width(10.dp))

            Image(

                painter = if (estadoTarea == estadosTarea[0]){
                    painterResource(R.drawable.ic_abierta)
                }else if (estadoTarea == estadosTarea[1]){
                    painterResource(R.drawable.ic_en_curso)
                }else{
                    painterResource(R.drawable.ic_cerrada)
                },
                contentDescription = "Imagen dependiendo del estado de la tarea",
                modifier = Modifier.width(24.dp)

            )
        }

        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            estadosTarea.forEach { estado ->

                RadioButton(
                    selected = (estado == estadoTarea),
                    onClick = { estadoTarea = estado }
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = estado)

            }
        }

    }



}

@Preview(showBackground = true)
@Composable
fun EstadoTareaPreview() {
    BasicRadioButton()
}