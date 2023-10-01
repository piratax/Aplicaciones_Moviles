package pe.edu.upeu.asistenciaupeujc.data.remote

import pe.edu.upeu.asistenciaupeujc.modelo.Inscritox
import pe.edu.upeu.asistenciaupeujc.modelo.InscritoxReport
import pe.edu.upeu.asistenciaupeujc.modelo.Materialesx
import pe.edu.upeu.asistenciaupeujc.modelo.MaterialesxReport
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

interface RestInscritox {
    @GET("/asis/inscrito/list")
    suspend fun reportarInscritox(@Header("Authorization") token:String):Response<List<InscritoxReport>>


    @GET("/asis/inscrito/buscar/{id}")
    suspend fun getInscritoxId(@Header("Authorization") token:String , @Query("id") id:Long):Response<Inscritox>


    @DELETE("/asis/inscrito/eliminar/{id}")
    suspend fun deleteInscritox(@Header("Authorization") token:String , @Path("id") id:Long):Response<MsgGeneric>


    @PUT("/asis/inscrito/editar/{id}")
    suspend fun actualizarInscritox(@Header("Authorization") token:String , @Path("id") id:Long , @Body inscritox :Inscritox):Response<Inscritox>

    @POST("/asis/inscrito/crear")
    suspend fun insertarInscritox(@Header("Authorization") token:String , @Body inscritox :Inscritox):Response<Inscritox>


}