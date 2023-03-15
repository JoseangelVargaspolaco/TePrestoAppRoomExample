package ucne.edu.teprestoapp.ui.ocupacion

import android.app.DatePickerDialog
import android.os.Build
import android.util.Log
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.tepresto.ui.persona.PersonaViewModel
import kotlinx.coroutines.launch
import ucne.edu.teprestoapp.data.local.entity.OcupacionEntity
import ucne.edu.teprestoapp.ui.navegation.ScreenModule
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PersonaScreen(viewModel: PersonaViewModel = hiltViewModel(), navController: NavController) {
    PersonaBody(viewModel, Modifier.fillMaxWidth(), navController)
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PersonaBody(
    viewModel: PersonaViewModel, modifier: Modifier, navController: NavController
) {
    val scope = rememberCoroutineScope()
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
    Column(modifier = Modifier.fillMaxWidth())
    {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .size(30.dp, 30.dp)
                .padding(4.dp)
                .clickable {
                    scope.launch {
                        navController.navigate(ScreenModule.Persona.route)
                    }
                }
        )

        Spacer(modifier = Modifier.padding(20.dp))
        Text(
            text = "Registro de personas", fontSize = 27.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.padding(10.dp))
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

//        OutlinedTextField(
//            label = { Text("Seleccionar ocupacion") },
//            leadingIcon = {
//                Icon(
//                    imageVector = Icons.Default.ArrowDropDown,
//                    contentDescription = null
//                )
//            },
//            value = viewModel.ocupacionId,
//            onValueChange = { viewModel.ocupacionId = it },
//            readOnly = true, enabled = false,
//            modifier = Modifier
//                .fillMaxWidth()
//                .clickable {}
//        )

        OcupacionDropdownMenu()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.BottomCenter)
        ) {
            ExtendedFloatingActionButton(
                modifier = Modifier
                    .size(120.dp, 120.dp)
                    .align(Alignment.CenterHorizontally)
                    .wrapContentSize(Alignment.Center),
                text = { Text("Guardar") },
                icon = { Icon(imageVector = Icons.Filled.Save, contentDescription = "Save") },
                onClick = {
                    viewModel.insertar()
                }
            )
        }
    }
}


/* metodo seleccionar ocupacion fallido */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OcupacionDropdownMenu() {
        var expand by remember { mutableStateOf(false) }

        val selection = listOf<OcupacionEntity>()
        var selectedText by remember { mutableStateOf("") }

        OutlinedTextField(
            label = { Text("Seleccionar ocupacion") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null
                )
            },
            value = selectedText,
            onValueChange = { selectedText = it },
            readOnly = true, enabled = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    expand = true
                    Log.e("tag", "expandio")
                }

        )
        DropdownMenu(
            expanded = expand,
            onDismissRequest = { expand = false },
            modifier = Modifier.fillMaxWidth().background(color = Color.Cyan)
        ) {
            selection.forEach {it
                DropdownMenuItem(
                    text = { Text(it.descripcion) },
                    onClick = {
                        expand = false
                        selectedText = it.descripcion
                    }
                )
            }
        }
}

