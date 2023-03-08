package ucne.edu.teprestoapp.ui.ocupacion

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun OcupacionScreen(viewModel: OcupacionViewModel = hiltViewModel()) {
    OcupacionBody(viewModel)
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun OcupacionBody(
    viewModel: OcupacionViewModel
) {
    val descriptionValidator: (OcupacionViewModel) -> Boolean = { viewModel ->
        viewModel.descripcion.length < 3
    }
    var description by rememberSaveable { mutableStateOf(viewModel.descripcion) }

    val sueldoValidator: (OcupacionViewModel) -> Boolean = { viewModel ->
        viewModel.sueldo.length <= 0
    }
    var sueldo by rememberSaveable { mutableStateOf(viewModel.sueldo) }


    Column(modifier = Modifier.fillMaxWidth()) {

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.descripcion,
            onValueChange = { viewModel.descripcion = it },
            label = { Text("Descripción") },

            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {  }
            )
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.sueldo,
            onValueChange = { viewModel.sueldo = it },
            label = { Text("Salario") }
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




