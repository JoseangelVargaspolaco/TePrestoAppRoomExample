package ucne.edu.teprestoapp.ui.navegation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenModule(val route: String, val title: String, val icon: ImageVector) {
    object Splash : ScreenModule("SplashScreen","", Icons.TwoTone.Eco)
    object Start : ScreenModule("ui","Inicio", Icons.TwoTone.Home)
    object Ocupacion : ScreenModule("ocupacion", "Registro de ocupaciónes", Icons.TwoTone.Engineering)
    object Persona : ScreenModule("persona", "Registro de personas", Icons.TwoTone.PersonAdd)
    object OcupacionList : ScreenModule("ocupacion_list","Lista de ocupaciones", Icons.TwoTone.List)
    object PersonaList : ScreenModule("persona_list","Lista de personas", Icons.TwoTone.List)
}