package ucne.edu.teprestoapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Personas")
data class PersonaEntity(
    @PrimaryKey(autoGenerate = true)
    val personaId: Int?=null,
    val nombres: String,
    val telefono: String,
    val celular: String,
    val email: String,
    val direccion: String,
    val fechaNacimieto: String,
    val ocupacionId: Int?=null
)