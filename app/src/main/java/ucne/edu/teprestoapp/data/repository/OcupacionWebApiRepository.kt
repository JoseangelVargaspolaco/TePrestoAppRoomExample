package ucne.edu.teprestoapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import ucne.edu.teprestoapp.data.remote.TePrestoWebApi
import ucne.edu.teprestoapp.data.remote.dto.OcupacionDto
import ucne.edu.teprestoapp.util.Resource
import java.io.IOException
import javax.inject.Inject

class OcupacionWebApiRepository @Inject constructor
    (
    private val webapi: TePrestoWebApi
) {
    fun getOcupacion(): Flow<Resource<List<OcupacionDto>>> = flow {
        try {
            emit(Resource.Loading()) //indicar que estamos cargando

            val ocupacion =
                webapi.getOcupacion() //descarga las ocupaciones de internet, se supone quedemora algo

            emit(Resource.Success(ocupacion)) //indicar que se cargo correctamente y pasarle las monedas
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
}