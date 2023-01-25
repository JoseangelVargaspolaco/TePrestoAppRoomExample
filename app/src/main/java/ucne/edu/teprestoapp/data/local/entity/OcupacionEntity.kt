package ucne.edu.teprestoapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Ocupaciones")
data class OcupacionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val descripcion : String,
    val sueldo: Double
)
