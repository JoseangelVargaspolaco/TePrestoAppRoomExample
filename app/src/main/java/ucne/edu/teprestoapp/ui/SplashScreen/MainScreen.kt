package ucne.edu.teprestoapp.ui.SplashScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WavingHand
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ucne.edu.teprestoapp.ui.navegation.Screen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainSplashScreen(navController: NavHostController): NavHostController {
    navController.navigate(Screen.Start.route)
    Splash()
    return navController
}

@Composable
fun Splash() {
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Filled.WavingHand,
            contentDescription = null,
            modifier = Modifier
                .size(180.dp, 180.dp)
                .padding(4.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    Splash()
}


/*
* Intento de SplashScreen
@Composable
fun MainSplashScreen(navController: NavHostController): NavHostController {
    LaunchedEffect(key1 = true) {
        delay(5000)
        navController.popBackStack()
        navController.navigate(Screen.Start.route)
    }
    Splash()
    return navController
}
* */