package ucne.edu.teprestoapp.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ucne.edu.teprestoapp.data.local.entity.PersonaEntity

@Dao
interface PersonaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(personaEntity: PersonaEntity)

    @Delete
    suspend fun delete(personaEntity: PersonaEntity)

    @Query("""
        SELECT * 
        FROM Persona
        WHERE PersonaId=:personaId
        LIMIT 1
    """)
    suspend fun find(personaId: Int): PersonaEntity?

    @Query("SELECT * FROM Personas")
    fun getList(): Flow<List<PersonaEntity>>
}