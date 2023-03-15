package ucne.edu.teprestoapp.data.remote.dto

data class PersonaDto (
    val personaId: Int,
    val nombres: String,
    val telefono: String,
    val celular: String,
    val email: String,
    val direccion: String,
    val fechaNacimieto: String,
    val ocupacionId: Int
)