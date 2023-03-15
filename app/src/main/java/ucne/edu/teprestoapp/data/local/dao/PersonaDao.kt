package ucne.edu.teprestoapp.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ucne.edu.teprestoapp.data.local.entity.OcupacionEntity
import ucne.edu.teprestoapp.data.local.entity.PersonaEntity

@Dao
interface PersonaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(personaEntity: PersonaEntity)

    @Delete
    suspend fun delete(personaEntity: PersonaEntity)

    @Query("""
        SELECT * 
        FROM Personas
        WHERE PersonaId=:personaId
        LIMIT 1
    """)
    suspend fun find(personaId: Int): PersonaEntity?

    @Query("""SELECT * 
        FROM Personas
        ORDER BY personaid desc
    """)
    fun getList(): Flow<List<PersonaEntity>>

    @Query("""SELECT * 
        FROM Personas
        WHERE enviado=0
    """)
    suspend fun getNoEnviadas(): List<PersonaEntity>
}