package ucne.edu.teprestoapp.data.repository

import kotlinx.coroutines.flow.Flow
import ucne.edu.teprestoapp.data.local.dao.OcupacionDao
import ucne.edu.teprestoapp.data.local.dao.PersonaDao
import ucne.edu.teprestoapp.data.local.entity.OcupacionEntity
import ucne.edu.teprestoapp.data.local.entity.PersonaEntity
import javax.inject.Inject

class OcupacionRepository @Inject constructor(
    private  val ocupacionDao: OcupacionDao
) {
    suspend fun insert(ocupacion: OcupacionEntity) {
        return ocupacionDao.insert(ocupacion)
    }
    suspend fun delete(ocupacion: OcupacionEntity) = ocupacionDao.delete(ocupacion)

    suspend fun find(ocupacionId:Int) = ocupacionDao.find(ocupacionId)

    fun getList(): Flow<List<OcupacionEntity>> = ocupacionDao.getList()
}
