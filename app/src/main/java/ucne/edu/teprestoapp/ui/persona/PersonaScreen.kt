package edu.ucne.tepresto.ui.persona

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ucne.edu.teprestoapp.data.local.entity.PersonaEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonaScreen(viewModel: PersonaViewModel = hiltViewModel()) {
    Column(modifier = Modifier.fillMaxSize()) {

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.nombres,
            onValueChange = { viewModel.nombres = it },
            label = { Text("Nombres") }
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.telefono,
            onValueChange = { viewModel.telefono = it },
            label = { Text("Telefono") }
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.celular,
            onValueChange = { viewModel.celular = it },
            label = { Text("Celular") }
        )
        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.email,
            onValueChange = { viewModel.email = it },
            label = { Text("Email") }
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.direccion,
            onValueChange = { viewModel.direccion = it },
            label = { Text("Direccion") }
        )
        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.fechaNacimiento,
            onValueChange = { viewModel.fechaNacimiento = it },
            label = { Text("Fecha de nacimiento") }
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.ocupacionId,
            onValueChange = {viewModel.ocupacionId = it},
            label = { Text("Ocupacion") }
        )

        ExtendedFloatingActionButton(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            text = { Text("Guardar") },
            icon = { Icon(imageVector = Icons.Filled.Save, contentDescription = "Save") },
            onClick = {
                viewModel.insertar(
                    viewModel.nombres,
                    viewModel.telefono,
                    viewModel.celular,
                    viewModel.email,
                    viewModel.direccion,
                    viewModel.fechaNacimiento,
                    viewModel.ocupacionId
                )
            }
        )
    }
}
