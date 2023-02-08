package com.ucne.roomexample.ui.ocupacion

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ucne.edu.teprestoapp.data.repository.OcupacionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ucne.edu.teprestoapp.data.local.entity.OcupacionEntity
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

    var uiState = MutableStateFlow(OcupacionUiState())
        private set
    init {
        getListaOcupacion()
    }
    fun getListaOcupacion() {
        viewModelScope.launch(Dispatchers.IO) {
            ocupacionRepository.getList().collect{lista ->
                uiState.update {
                    it.copy(ocupacionesList = lista)
                }
            }
        }
    }

    fun insertar() {
        val ocupacion = OcupacionEntity(
            descripcion = descripcion,
            sueldo = sueldo.toDoubleOrNull() ?: 0.0
        )

        viewModelScope.launch(Dispatchers.IO) {
            ocupacionRepository.insert(ocupacion)
            Limpiar()
        }
    }

    private fun Limpiar() {
        descripcion = ""
        sueldo = ""
    }
}