package ucne.edu.teprestoapp.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ucne.edu.teprestoapp.data.local.entity.OcupacionEntity

@Dao
interface OcupacionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ocupacionEntity: OcupacionEntity)

    @Delete
    suspend fun delete(ocupacionEntity: OcupacionEntity)

    @Query("""
        SELECT * 
        FROM Ocupaciones
        WHERE OcupacionId=:ocupacionId
        LIMIT 1
    """)
    suspend fun find(ocupacionId: Int): OcupacionEntity?

    @Query("""SELECT * 
        FROM Ocupaciones
        ORDER BY ocupacionId desc
    """)
    fun getList(): Flow<List<OcupacionEntity>>
    @Query("""SELECT * 
        FROM Ocupaciones
        WHERE enviado=0
    """)
    suspend fun getNoEnviadas(): List<OcupacionEntity>
}