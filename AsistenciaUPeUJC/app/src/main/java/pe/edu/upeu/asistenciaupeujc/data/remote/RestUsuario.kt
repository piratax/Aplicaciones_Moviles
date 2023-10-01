package pe.edu.upeu.asistenciaupeujc.data.remote

import pe.edu.upeu.asistenciaupeujc.modelo.UsuarioDto
import pe.edu.upeu.asistenciaupeujc.modelo.UsuarioResp
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RestUsuario {
    @POST("/asis/login")
    suspend fun login(@Body user:UsuarioDto):Response<UsuarioResp>
}