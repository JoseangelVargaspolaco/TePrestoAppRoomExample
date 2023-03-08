package ucne.edu.teprestoapp.ui.ocupacion

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
import ucne.edu.teprestoapp.data.repository.OcupacionRepository
import javax.inject.Inject

data class OcupacionUiState(
    val ocupacionesList: List<OcupacionEntity> = emptyList()
)

@HiltViewModel
class OcupacionViewModel @Inject constructor(
    private val ocupacionRepository: OcupacionRepository
) : ViewModel() {

    var descripcion by mutableStateOf("")
    var sueldo by mutableStateOf("")
    var isValid: Boolean by mutableStateOf(false)

    var uiState = MutableStateFlow(OcupacionUiState())
        private set

    init {
        getLista()
    }

    fun getLista() {
        viewModelScope.launch(Dispatchers.IO) {
            refrescarOcupaciones()
        }
    }

    fun validatonScreen() {

        if (descripcion.isNullOrEmpty()) {
            throw IllegalArgumentException("El campo descripción es requerido")
        } else {
            isValid = false
        }

        if (sueldo.isNullOrEmpty()) {
            throw IllegalArgumentException("El campo sueldo es requerido")
        } else {
            isValid = false
        }
    }

//    private  suspend fun getArticulosFromApi(){
//       /* val articulos = gestionInventarioApi.getList()
//        uiState.update {
//            it.copy(articulosList = articulos)
//        }*/
//    }

    private suspend fun refrescarOcupaciones() {
        ocupacionRepository.getList().collect { lista ->
            uiState.update {
                it.copy(ocupacionesList = lista)
            }
        }
    }

    fun insertar() {
        try {
            validatonScreen()
            val ocupacion = OcupacionEntity(
                descripcion = descripcion,
                sueldo = sueldo.toDoubleOrNull() ?: 0.0
            )

            viewModelScope.launch(Dispatchers.IO) {
                ocupacionRepository.insert(ocupacion)
                Limpiar()
            }
        } catch (ex: IllegalArgumentException) {
            isValid = true
        }
    }

    private fun Limpiar() {
        descripcion = ""
        sueldo = ""
    }
}