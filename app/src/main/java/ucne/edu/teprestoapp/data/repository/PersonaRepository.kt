package ucne.edu.teprestoapp.data.repository

import kotlinx.coroutines.flow.Flow
import ucne.edu.teprestoapp.data.local.dao.PersonaDao
import ucne.edu.teprestoapp.data.local.entity.PersonaEntity
import javax.inject.Inject

class PersonaRepository @Inject constructor(
    private  val personaDao: PersonaDao
) {
    suspend fun insert(persona: PersonaEntity) {
        return personaDao.insert(persona)
    }
    suspend fun delete(persona: PersonaEntity) = personaDao.delete(persona)

    suspend fun find(personaId:Int) = personaDao.find(personaId)

    fun getList(): Flow<List<PersonaEntity>> = personaDao.getList()
}