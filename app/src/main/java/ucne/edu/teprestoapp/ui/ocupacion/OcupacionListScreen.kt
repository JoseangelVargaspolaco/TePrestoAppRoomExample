import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import ucne.edu.teprestoapp.DrawerMenu
import ucne.edu.teprestoapp.Screen
import ucne.edu.teprestoapp.data.local.entity.OcupacionEntity
import ucne.edu.teprestoapp.ui.ocupacion.OcupacionScreen
import ucne.edu.teprestoapp.ui.ocupacion.OcupacionViewModel
import ucne.edu.teprestoapp.ui.ocupacion.PersonaScreen
import ucne.edu.teprestoapp.ui.persona.PersonaListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OcupacionListScreen(onNewOcupacion: ()-> Unit) {
    val viewModel: OcupacionViewModel = hiltViewModel()
    val navController: NavHostController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    Column(Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Consulta de Ocupaciones") },
            navigationIcon = {

                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp, 50.dp)
                        .padding(4.dp)
                        .clickable {
                            scope.launch {
                                navController.navigate(Screen.Start.route)
                            }
                        }
                )
            },


            actions = {
                // RowScope here, so these icons will be placed horizontally
                IconButton(onClick = { navController.navigate(Screen.Start.route) }) {
                    Icon(Icons.Filled.Search, contentDescription = "Buscar")
                }

            }
        )
        val uiState by viewModel.uiState.collectAsState()
        OcupacionListScreen(uiState.ocupacionesList)
    }

}




@Composable
fun OcupacionListScreen(ocupacionList: List<OcupacionEntity>) {
    LazyColumn(
        contentPadding = PaddingValues(
            vertical = 8.dp,
            horizontal = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(ocupacionList) { ocupacion ->
            OcupacionRow(ocupacion)
        }
    }
}

@Composable
private fun OcupacionRow(ocupacion: OcupacionEntity) {
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
                Spacer(Modifier.height(50.dp))

                Text(
                    text = ocupacion.descripcion,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(3f)
                )
                Text(
                    String.format("%.2f", ocupacion.sueldo),
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(2f)
                )
            }
            Divider(Modifier.fillMaxWidth())
        }
    }
}