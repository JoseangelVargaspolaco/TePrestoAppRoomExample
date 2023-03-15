package ucne.edu.teprestoapp.ui.persona

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import ucne.edu.teprestoapp.data.remote.dto.PersonaDto
import ucne.edu.teprestoapp.data.repository.PersonaWebApiRepository
import ucne.edu.teprestoapp.util.Resource
import javax.inject.Inject

data class PersonaListState(
    val isLoading: Boolean = false,
    val persona: List<PersonaDto> = emptyList(),
    val error: String = ""
)

class PersonaWebApiViewModel @Inject constructor(
    private val personaWebApiRepository: PersonaWebApiRepository
) : ViewModel() {

    var uiState = MutableStateFlow(PersonaListState())
        private set

    init {
        personaWebApiRepository.getPersona().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiState.update {
                        it.copy(isLoading = true)
                    }
                }

                is Resource.Success -> {
                    uiState.update {
                        it.copy(persona = result.data ?: emptyList())
                    }
                }

                is Resource.Error -> {
                    uiState.update {
                        it.copy(error = result.message ?: "Error desconocido")
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

}