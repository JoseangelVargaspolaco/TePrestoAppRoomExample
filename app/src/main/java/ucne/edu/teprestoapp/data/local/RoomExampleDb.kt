package ucne.edu.teprestoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ucne.edu.teprestoapp.data.local.dao.OcupacionDao
import ucne.edu.teprestoapp.data.local.dao.PersonaDao
import ucne.edu.teprestoapp.data.local.entity.OcupacionEntity
import ucne.edu.teprestoapp.data.local.entity.PersonaEntity

@Database(
    entities = [
        OcupacionEntity::class,
        PersonaEntity::class,
    ],
    version = 2
)
abstract class RoomExampleDb: RoomDatabase() {
    abstract val ocupacionDao: OcupacionDao
    abstract val personaDao: PersonaDao
}