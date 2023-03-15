package ucne.edu.teprestoapp.ui.SplashScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WavingHand
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import ucne.edu.teprestoapp.ui.navegation.ScreenModule

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainSplashScreen(navController: NavHostController): NavHostController {

    LaunchedEffect(key1 =  true)
    {
        delay(3500)
        navController.popBackStack()
        navController.navigate(ScreenModule.Start.route)
    }

    Splash()
    return navController
}

@Composable
fun Splash() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.DarkGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Filled.WavingHand,
            contentDescription = null,
            modifier = Modifier
                .size(180.dp, 180.dp)
                .padding(4.dp),
            tint = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    Splash()
}
