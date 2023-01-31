package ucne.edu.teprestoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ucne.roomexample.ui.ocupacion.OcupacionScreen
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.tepresto.ui.persona.PersonaScreen
import ucne.edu.teprestoapp.ui.theme.TePrestoAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scroll = rememberScrollState()
            Column (modifier = Modifier.verticalScroll(scroll)){
                OcupacionScreen()
                PersonaScreen()
            }
        }
    }
}
