package ucne.edu.teprestoapp.data.remote

import retrofit2.http.GET
import retrofit2.http.POST
import ucne.edu.teprestoapp.data.remote.dto.OcupacionDto
import ucne.edu.teprestoapp.data.remote.dto.PersonaDto

interface  TePrestoWebApi{
    @GET("/api/ocupaciones")
    suspend fun getOcupacion(): List<OcupacionDto>

    @POST("/api/Ocupaciones")
    suspend fun postOcupacion(ocupacionDto: OcupacionDto)

    @GET("/api/personas")
    suspend fun getPersona(): List<PersonaDto>

    @POST("/api/Personas")
    suspend fun postPersona(personaDto: PersonaDto)
}