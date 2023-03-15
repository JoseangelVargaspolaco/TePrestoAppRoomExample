package ucne.edu.teprestoapp.data.repository

import kotlinx.coroutines.flow.Flow
import ucne.edu.teprestoapp.data.local.dao.OcupacionDao
import ucne.edu.teprestoapp.data.local.dao.PersonaDao
import ucne.edu.teprestoapp.data.local.entity.OcupacionEntity
import ucne.edu.teprestoapp.data.local.entity.PersonaEntity
import ucne.edu.teprestoapp.data.local.entity.toOcupacionDto
import ucne.edu.teprestoapp.data.remote.TePrestoWebApi
import javax.inject.Inject

class OcupacionRepository @Inject constructor(
    private  val ocupacionDao: OcupacionDao,
    private  val tePrestoWebApi: TePrestoWebApi
) {
    suspend fun insert(ocupacion: OcupacionEntity) {

        return ocupacionDao.insert(ocupacion) //insertar en la base de datos

        val ocupacionesNoEnviadas = ocupacionDao.getNoEnviadas() //buscar no enviados

        ocupacionesNoEnviadas.map { ocupacionEntity ->
            val dto = ocupacionEntity.toOcupacionDto()
            tePrestoWebApi.postOcupacion(dto)
        }
    }
    suspend fun delete(ocupacion: OcupacionEntity) = ocupacionDao.delete(ocupacion)

    suspend fun find(ocupacionId:Int) = ocupacionDao.find(ocupacionId)

    fun getList(): Flow<List<OcupacionEntity>> = ocupacionDao.getList()
}
