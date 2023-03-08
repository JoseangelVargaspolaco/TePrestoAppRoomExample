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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ucne.edu.teprestoapp.ui.ocupacion.*
import ucne.edu.teprestoapp.ui.theme.TePrestoAppTheme
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.tepresto.ui.persona.PersonaViewModel

import ucne.edu.teprestoapp.ui.navegation.DrawerMenu
import ucne.edu.teprestoapp.ui.navegation.Screen
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
                    val viewModel: PersonaViewModel = hiltViewModel()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Start.route
                    ) {
//                        composable(Screen.Splash.route) {
//                            DrawerMenu(MainSplashScreen(navController))
//                        }
                        composable(Screen.Start.route) {
                            DrawerMenu(navController)
                        }
                        composable(Screen.Ocupacion.route) {
                            OcupacionScreen()
                        }
                        composable(Screen.Persona.route) {
                            PersonaScreen(viewModel,navController)
                        }
                        composable(Screen.OcupacionList.route) {
                            OcupacionListScreen(onNewOcupacion = {}, navController)
                        }
                        composable(Screen.PersonaList.route) {
                            PersonaListScreen(onNewPersona = {}, navController)
                        }
                    }
                }
            }
        }
    }
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
