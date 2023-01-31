package edu.ucne.tepresto.ui.persona

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.type.DateTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ucne.edu.teprestoapp.data.local.entity.PersonaEntity
import ucne.edu.teprestoapp.data.repository.PersonaRepository
import javax.inject.Inject

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

    fun insertar(
        nombres: String,
        telefono: String,
        celular: String,
        email: String,
        direccion: String,
        fechaNacimiento: String,
        ocupacionId: String
    ) {

        val persona = PersonaEntity(
            nombres = nombres,
            telefono = telefono,
            celular = celular,
            email = email,
            direccion = direccion,
            fechaNacimieto = fechaNacimiento,
            ocupacionId = ocupacionId.toIntOrNull()?:0
        )

        viewModelScope.launch {
            personaRepository.insert(persona)
        }
    }
}