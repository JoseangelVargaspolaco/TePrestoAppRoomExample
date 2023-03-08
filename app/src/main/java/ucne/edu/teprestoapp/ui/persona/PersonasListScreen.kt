package ucne.edu.teprestoapp.ui.persona

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import edu.ucne.tepresto.ui.persona.PersonaViewModel
import ucne.edu.teprestoapp.data.local.entity.OcupacionEntity
import ucne.edu.teprestoapp.data.local.entity.PersonaEntity

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonaListScreen(onNewPersona: ()-> Unit) {
    val viewModel: PersonaViewModel = hiltViewModel()
    val navController: NavHostController = rememberNavController()
    Column(Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Consulta de Personas") },
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = null)
                }
            },


            actions = {
                // RowScope here, so these icons will be placed horizontally
                IconButton(onClick = { navController.navigate("Home") }) {
                    Icon(Icons.Filled.Search, contentDescription = "Buscar")
                }

            }
        )
        val uiState by viewModel.uiState.collectAsState()
        PersonaListScreen(uiState.personaList)
    }
}


@Composable
fun PersonaListScreen(personaList: List<PersonaEntity>) {
    LazyColumn(
        contentPadding = PaddingValues(
            vertical = 8.dp,
            horizontal = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(personaList) {persona ->
            PersonaRow(persona)
        }
    }
}

@Composable
private fun PersonaRow(persona: PersonaEntity) {
    //TODO Implementar swipe to delete
    Card(
        shape = RoundedCornerShape(1.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Text(
                    text = persona.nombres,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(3f)
                )
                Text(
                    text = persona.telefono,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(3f)
                )
                Text(
                    text = persona.celular,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(3f)
                )
                Text(
                    text = persona.email,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(3f)
                )
                Text(
                    text = persona.direccion,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(3f)
                )
                Text(
                    text = persona.fechaNacimieto,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(3f)
                )
                Text(
                    text = persona.ocupacionId.toString(),
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(3f)
                )
            }
            Divider(Modifier.fillMaxWidth())
        }
    }
}

