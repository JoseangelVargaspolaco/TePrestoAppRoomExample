package ucne.edu.teprestoapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ucne.edu.teprestoapp.data.remote.dto.OcupacionDto

@Entity(tableName = "Ocupaciones")
data class OcupacionEntity(
    @PrimaryKey(autoGenerate = true)
    val ocupacionid: Int? = null,
    var descripcion: String,
    var sueldo: Double,
    val enviado: Boolean = false
)

fun OcupacionEntity.toOcupacionDto(): OcupacionDto {
    return OcupacionDto(
        ocupacionId = this.ocupacionid ?: 0,
        descripcion = this.descripcion,
        sueldo = this.sueldo
    )
}