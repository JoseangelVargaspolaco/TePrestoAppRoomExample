package ucne.edu.teprestoapp.ui.navegation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String,val title: String, val icon: ImageVector) {
    object Splash : Screen("SplashScreen","", Icons.TwoTone.Eco)
    object Start : Screen("ui","Inicio", Icons.TwoTone.Home)
    object Ocupacion : Screen("ocupacion", "Registro de ocupaciónes", Icons.TwoTone.Engineering)
    object Persona : Screen("persona", "Registro de personas", Icons.TwoTone.PersonAdd)
    object OcupacionList : Screen("ocupacion_list","Lista de ocupaciones", Icons.TwoTone.List)
    object PersonaList : Screen("persona_list","Lista de personas", Icons.TwoTone.List)
}