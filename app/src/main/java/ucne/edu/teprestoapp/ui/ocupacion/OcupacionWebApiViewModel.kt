package ucne.edu.teprestoapp.ui.ocupacion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import ucne.edu.teprestoapp.data.remote.dto.OcupacionDto
import ucne.edu.teprestoapp.data.repository.OcupacionWebApiRepository
import ucne.edu.teprestoapp.util.Resource
import javax.inject.Inject

data class OcupacionesListState(
    val isLoading: Boolean = false,
    val ocupacion: List<OcupacionDto> = emptyList(),
    val error: String = ""
)

class OcupacionWebApiViewModel @Inject constructor(
    private val ocupacionWebApiRepository: OcupacionWebApiRepository
) : ViewModel() {

    var uiState = MutableStateFlow(OcupacionesListState())
        private set

    init {
        ocupacionWebApiRepository.getOcupacion().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiState.update {
                        it.copy(isLoading = true)
                    }
                }

                is Resource.Success -> {
                    uiState.update {
                        it.copy(ocupacion = result.data ?: emptyList())
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