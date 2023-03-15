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
import kotlinx.coroutines.launch
import ucne.edu.teprestoapp.data.local.entity.OcupacionEntity
import ucne.edu.teprestoapp.ui.navegation.ScreenModule
import ucne.edu.teprestoapp.ui.ocupacion.OcupacionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OcupacionListScreen(onNewOcupacion: ()-> Unit, navController: NavController) {
    val viewModel: OcupacionViewModel = hiltViewModel()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    Column(Modifier.fillMaxWidth()) {
        Spacer(Modifier.height(50.dp))
        TopAppBar(
            title = {
                Text(
                    "Consulta de Ocupaciones", modifier = Modifier
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
                                navController.popBackStack()
                            }
                        }
                )
            },


            actions = {
                // RowScope here, so these icons will be placed horizontally
                IconButton(onClick = { navController.navigate(ScreenModule.Start.route) }) {
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
    Spacer(Modifier.height(40.dp))
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
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()

            ) {
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