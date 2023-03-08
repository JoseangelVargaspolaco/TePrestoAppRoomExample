package ucne.edu.teprestoapp.ui.ocupacion

import android.app.DatePickerDialog
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import edu.ucne.tepresto.ui.persona.PersonaViewModel
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PersonaScreen(viewModel: PersonaViewModel = hiltViewModel()) {
    PersonaBody(viewModel, Modifier.fillMaxWidth())
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PersonaBody(
    viewModel: PersonaViewModel, modifier: Modifier
) {
    val anio: Int
    val mes: Int
    val dia: Int

    val mCalendar = Calendar.getInstance()
    anio = mCalendar.get(Calendar.YEAR)
    mes = mCalendar.get(Calendar.MONTH)
    dia = mCalendar.get(Calendar.DAY_OF_MONTH)

    val mDatePickerDialog = DatePickerDialog(
        LocalContext.current, { _: DatePicker, anio: Int, mes: Int, dia: Int ->
            viewModel.fechaNacimiento = "$dia/${mes + 1}/$anio"
        }, anio, mes, dia
    )
    /*----------------------------------------Code Start------------------------------------------------------*/
    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
            value = viewModel.nombres,
            onValueChange = { it -> viewModel.nombres = it },
            label = { Text("Nombres") })

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.telefono,
            onValueChange = { it -> viewModel.telefono = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text("Telefono")})

        OutlinedTextField(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
            value = viewModel.celular,
            onValueChange = { viewModel.celular = it },
            label = { Text("Celular") })

        OutlinedTextField(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
            value = viewModel.email,
            onValueChange = { viewModel.email = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = { Text("Email") })

        OutlinedTextField(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
            value = viewModel.direccion,
            onValueChange = { viewModel.direccion = it },
            label = { Text("Direccion") })

        OutlinedTextField(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
            value = viewModel.fechaNacimiento,
            onValueChange = { viewModel.fechaNacimiento = it },
            enabled = false,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = null,
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                        .clickable {
                            mDatePickerDialog.show()
                        })
            },
            label = { Text(text = "Fecha de nacimiento") })

        OutlinedTextField(
            label = { Text("Seleccionar ocupacion") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null
                )
            },
            value = viewModel.ocupacionId,
            onValueChange = { viewModel.ocupacionId = it },
            readOnly = true, enabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {}
        )


        ExtendedFloatingActionButton(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            text = { Text("Guardar") },
            icon = { Icon(imageVector = Icons.Filled.Save, contentDescription = "Save") },
            onClick = {
                    viewModel.insertar()
            }
        )
    }
}


/* metodo seleccionar ocupacion fallido */
//        var expandio by remember { mutableStateOf(false) }
//
//        val suggestions = listOf(viewModell.descripcion)
//
//        var selectedText by remember { mutableStateOf("") }
//
//        OutlinedTextField(
//            label = { Text("Seleccionar ocupacion") },
//            leadingIcon = {
//                Icon(
//                    imageVector = Icons.Default.ArrowDropDown,
//                    contentDescription = null
//                )
//            },
//            value = selectedText,
//            onValueChange = { selectedText = it },
//            readOnly = true, enabled = false,
//            modifier = Modifier
//                .fillMaxWidth()
//                .clickable {
//                    expandio = true
//                    Log.e("tag", "expandio")
//                }
//
//        )
//        DropdownMenu(
//            expanded = expandio,
//            onDismissRequest = { expandio = false },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            suggestions.forEach { it
//                DropdownMenuItem(
//                    onClick = {
//                        expandio = false
//                        selectedText = it
//                    },
//                    text = { it }
//                )
//            }
//        }

