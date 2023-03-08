package ucne.edu.teprestoapp

import OcupacionListScreen
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.twotone.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ucne.edu.teprestoapp.ui.ocupacion.*
import ucne.edu.teprestoapp.ui.theme.TePrestoAppTheme
import dagger.hilt.android.AndroidEntryPoint

import kotlinx.coroutines.launch
import ucne.edu.teprestoapp.ui.MainSplashScreen
import ucne.edu.teprestoapp.ui.persona.PersonaListScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TePrestoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Splash.route
                    ) {
                        composable(Screen.Splash.route) {
                            DrawerMenu(MainSplashScreen(navController))
                        }
                        composable(Screen.Start.route) {
                            Spacer(Modifier.height(10.dp))
                            DrawerMenu(navController)
                        }
                        composable(Screen.Ocupacion.route) {
                            OcupacionScreen()
                        }
                        composable(Screen.Persona.route) {
                            PersonaScreen()
                        }
                        composable(Screen.OcupacionList.route) {
                            OcupacionListScreen(onNewOcupacion = {})
                        }
                        composable(Screen.PersonaList.route) {
                            PersonaListScreen(onNewPersona = {})
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerMenu(
    navController: NavHostController
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
// icons to mimic drawer destinations
    val ic  = Icons.TwoTone.Favorite

    val items = listOf(Screen.Start, Screen.Ocupacion, Screen.OcupacionList, Screen.Persona, Screen.PersonaList)
    val selectedItem = remember { mutableStateOf(items[0]) }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                items.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item.icon, contentDescription = null) },
                        label = { Text(item.title) },
                        selected = item == selectedItem.value,
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedItem.value = item
                            navController.navigate(item.route)
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .size(200.dp, 200.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(Modifier.height(10.dp))

                Icon(
                    imageVector = Icons.Filled.List,
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp, 50.dp)
                        .padding(4.dp)
                        .clickable {
                            scope.launch { drawerState.open() }
                        }
                )
            }
        }
    )
}

sealed class Screen(val route: String,val title: String, val icon: ImageVector) {
    object Splash : Screen("ui","", Icons.TwoTone.Eco)
    object Start : Screen("start","Inicio", Icons.TwoTone.Home)
    object Ocupacion : Screen("ocupacion", "Registro de ocupaciónes", Icons.TwoTone.Engineering)
    object Persona : Screen("persona", "Registro de personas", Icons.TwoTone.PersonAdd)
    object OcupacionList : Screen("ocupacion_list","Lista de ocupaciones", Icons.TwoTone.List)
    object PersonaList : Screen("persona_list","Lista de personas", Icons.TwoTone.List)
}

@Composable
fun StartScreen(name: String = "") {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TePrestoAppTheme {
        StartScreen("Android")
    }
}
