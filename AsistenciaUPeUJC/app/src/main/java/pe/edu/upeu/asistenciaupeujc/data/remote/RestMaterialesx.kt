package pe.edu.upeu.asistenciaupeujc.data.remote

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

interface RestMaterialesx {
    @GET("/asis/materialesx/list")
    suspend fun reportarMaterialesx(@Header("Authorization") token:String):Response<List<MaterialesxReport>>


    @GET("/asis/materialesx/buscar/{id}")
    suspend fun getMaterialesxId(@Header("Authorization") token:String , @Query("id") id:Long):Response<Materialesx>


    @DELETE("/asis/materialesx/eliminar/{id}")
    suspend fun deleteMaterialesx(@Header("Authorization") token:String , @Path("id") id:Long):Response<MsgGeneric>


    @PUT("/asis/materialesx/editar/{id}")
    suspend fun actualizarMaterialesx(@Header("Authorization") token:String , @Path("id") id:Long , @Body materialesx :Materialesx):Response<Materialesx>

    @POST("/asis/materialesx/crear")
    suspend fun insertarMaterialesx(@Header("Authorization") token:String , @Body materialesx :Materialesx):Response<Materialesx>


}