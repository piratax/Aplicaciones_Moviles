package pe.edu.upeu.asistenciaupeujc.data.remote

import pe.edu.upeu.asistenciaupeujc.modelo.Actividad
import pe.edu.upeu.asistenciaupeujc.modelo.MsgGeneric
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface RestActividad {
    @GET("/asis/actividad/list")
    suspend fun reportarActividad(@Header("Authorization") token:String):Response<List<Actividad>>

    @GET("/asis/actividad/buscar/{id}")
    suspend fun getActividadId(@Header("Authorization") token:String, @Query("id") id:Long):Response<Actividad>

    @DELETE("/asis/actividad/eliminar/{id}")
    suspend fun deleteActividad(@Header("Authorization") token:String, @Path("id") id:Long):Response<MsgGeneric>

    @PUT("/asis/actividad/editar/{id}")
    suspend fun actualizarActividad(@Header("Authorization") token:String, @Path("id") id:Long, @Body actividad: Actividad): Response<Actividad>

    @POST("/asis/actividad/crear")
    suspend fun insertarActividad(@Header("Authorization") token:String,@Body actividad: Actividad): Response<Actividad>
}