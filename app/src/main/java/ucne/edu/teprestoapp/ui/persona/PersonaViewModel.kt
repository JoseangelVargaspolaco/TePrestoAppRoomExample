package edu.ucne.tepresto.ui.persona

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ucne.edu.teprestoapp.data.local.entity.OcupacionEntity
import ucne.edu.teprestoapp.data.local.entity.PersonaEntity
import ucne.edu.teprestoapp.data.repository.PersonaRepository
import java.text.DateFormat
import java.time.format.DateTimeFormatter
import javax.inject.Inject
data class PersonaUiState(
    val personaList: List<PersonaEntity> = emptyList()
)
@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class PersonaViewModel @Inject constructor(
    private val personaRepository: PersonaRepository
) : ViewModel() {

    var nombres by mutableStateOf("")
    var telefono by mutableStateOf("")
    var celular by mutableStateOf("")
    var email by mutableStateOf("")
    var direccion by mutableStateOf("")
    var fechaNacimiento by mutableStateOf("")
    var ocupacionId by mutableStateOf("")

    var uiState = MutableStateFlow(PersonaUiState())
        private set
    init {
        getListaPersonas()
    }
    fun getListaPersonas() {
        viewModelScope.launch(Dispatchers.IO) {
            personaRepository.getList().collect{listas ->
                uiState.update {
                    it.copy(personaList = listas)
                }
            }
        }
    }

    fun insertar() {
        val persona = PersonaEntity(
            nombres = nombres,
            telefono = telefono,
            celular = celular,
            email = email,
            direccion = direccion,
            fechaNacimieto = fechaNacimiento.toString(),
            ocupacionId = ocupacionId.toIntOrNull()?:0
        )

        viewModelScope.launch {
            personaRepository.insert(persona)
        }
    }
}