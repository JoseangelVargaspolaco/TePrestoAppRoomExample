package ucne.edu.teprestoapp.data.repository

import kotlinx.coroutines.flow.Flow
import ucne.edu.teprestoapp.data.local.dao.PersonaDao
import ucne.edu.teprestoapp.data.local.entity.PersonaEntity
import ucne.edu.teprestoapp.data.local.entity.toOcupacionDto
import ucne.edu.teprestoapp.data.local.entity.toPersonaDto
import ucne.edu.teprestoapp.data.remote.TePrestoWebApi
import javax.inject.Inject

class PersonaRepository @Inject constructor(
    private  val personaDao: PersonaDao,
    private  val tePrestoWebApi: TePrestoWebApi
) {
    suspend fun insert(persona: PersonaEntity) {

        return personaDao.insert(persona)//insertar en la base de datos

        val personasNoEnviadas = personaDao.getNoEnviadas() //buscar no enviados

        personasNoEnviadas.map { personaEntity ->
            val dto = personaEntity.toPersonaDto()
            tePrestoWebApi.postPersona(dto)
        }
    }
    suspend fun delete(persona: PersonaEntity) = personaDao.delete(persona)

    suspend fun find(personaId:Int) = personaDao.find(personaId)

    fun getList(): Flow<List<PersonaEntity>> = personaDao.getList()
}