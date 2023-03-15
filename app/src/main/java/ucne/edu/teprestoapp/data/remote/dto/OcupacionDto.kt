package ucne.edu.teprestoapp.data.remote.dto

import com.squareup.moshi.Json

data class OcupacionDto(
    val ocupacionId: Int,
    val descripcion: String,
    val sueldo: Double
)