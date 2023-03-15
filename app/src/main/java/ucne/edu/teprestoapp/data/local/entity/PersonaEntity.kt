package ucne.edu.teprestoapp.data.local.entity

import android.widget.DatePicker
import androidx.room.Entity
import androidx.room.PrimaryKey
import ucne.edu.teprestoapp.data.remote.dto.OcupacionDto
import ucne.edu.teprestoapp.data.remote.dto.PersonaDto

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
    val ocupacionId: Int,
    val enviado: Boolean = false
)

fun PersonaEntity.toPersonaDto(): PersonaDto {
    return PersonaDto(
        personaId = this.personaId ?: 0,
        nombres = this.nombres,
        telefono = this.telefono,
        celular = this.celular,
        email = this.email,
        direccion = this.direccion,
        fechaNacimieto = this.fechaNacimieto,
        ocupacionId = this.ocupacionId
    )
}