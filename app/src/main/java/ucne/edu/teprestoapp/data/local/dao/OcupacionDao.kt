package ucne.edu.teprestoapp.data.local.dao

import androidx.room.*
import ucne.edu.teprestoapp.data.local.entity.OcupacionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OcupacionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ocupacionEntity: OcupacionEntity)

    @Delete
    suspend fun delete(ocupacionEntity: OcupacionEntity)

    @Query("""SELECT FROM Ocupaciones WHERE OcupacionId=:ocupacionid LIMIT 1""")
    suspend fun find(ocupacionid: Int): OcupacionEntity?

    @Query("SELECT * FROM Ocupaciones")
    fun getList(): Flow<List<OcupacionEntity>>
}