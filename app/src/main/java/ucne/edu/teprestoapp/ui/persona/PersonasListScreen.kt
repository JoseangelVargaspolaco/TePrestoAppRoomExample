package ucne.edu.teprestoapp.ui.persona

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.tepresto.ui.persona.PersonaViewModel
import kotlinx.coroutines.launch
import ucne.edu.teprestoapp.data.local.entity.PersonaEntity
import ucne.edu.teprestoapp.ui.navegation.ScreenModule

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonaListScreen(onNewPersona: ()-> Unit, navController: NavController) {
    val viewModel: PersonaViewModel = hiltViewModel()
    val scope = rememberCoroutineScope()
    Column(Modifier.fillMaxSize()) {
        Spacer(Modifier.height(50.dp))
        TopAppBar(
            title = {
                Text(
                    "Consulta de Personas", modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(
                            Alignment.Center
                        )
                )
            },
            navigationIcon = {
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
    Spacer(Modifier.height(40.dp))
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
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(3f)
                )
                Text(
                    text = persona.telefono,
                    textAlign = TextAlign.Start,
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
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(4f)
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

