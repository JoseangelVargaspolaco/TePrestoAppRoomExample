package ucne.edu.teprestoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ucne.edu.teprestoapp.data.local.dao.OcupacionDao
import ucne.edu.teprestoapp.data.local.entity.OcupacionEntity

@Database(
    entities = [
        OcupacionEntity::class
    ],
    version = 1
)
abstract class RoomExampleDb: RoomDatabase() {
    abstract val ocupacionDao: OcupacionDao
}