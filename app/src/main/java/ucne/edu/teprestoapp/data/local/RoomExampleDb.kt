package ucne.edu.teprestoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ucne.edu.teprestoapp.data.local.dao.OcupacionDao
import ucne.edu.teprestoapp.data.local.dao.PersonaDao
import ucne.edu.teprestoapp.data.local.entity.OcupacionEntity
import ucne.edu.teprestoapp.data.local.entity.PersonaEntity

@Database(
    entities = [
        PersonaEntity::class,
        OcupacionEntity::class
    ],
    version = 2
)
abstract class RoomExampleDb: RoomDatabase() {
    abstract val personaDao: PersonaDao
    abstract val ocupacionDao: OcupacionDao
}