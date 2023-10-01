package pe.edu.upeu.asistenciaupeujc.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import pe.edu.upeu.asistenciaupeujc.data.local.dao.ActividadDao
import pe.edu.upeu.asistenciaupeujc.data.remote.RestActividad
import pe.edu.upeu.asistenciaupeujc.modelo.Actividad
import pe.edu.upeu.asistenciaupeujc.utils.TokenUtils
import pe.edu.upeu.asistenciaupeujc.utils.isNetworkAvailable
import javax.inject.Inject

interface ActividadRepository {
    suspend fun deleteActividad(actividad: Actividad)
    fun reportarActividades():LiveData<List<Actividad>>

    fun buscarActividadId(id:Long):LiveData<Actividad>

    suspend fun insertarActividad(actividad: Actividad):Boolean

    suspend fun modificarRemoteActividad(actividad: Actividad):Boolean
}

class ActividadRepositoryImp @Inject constructor(
    private val restActividad: RestActividad,
    private val actividadDao: ActividadDao
): ActividadRepository{
    override suspend fun deleteActividad(actividad: Actividad){
        CoroutineScope(Dispatchers.IO).launch {
            restActividad.deleteActividad(TokenUtils.TOKEN_CONTENT,actividad.id)
        }
        actividadDao.eliminarActividad(actividad)
    }


    override fun reportarActividades():LiveData<List<Actividad>>{
        try {
            CoroutineScope(Dispatchers.IO).launch{
                delay(3000)
                if (isNetworkAvailable(TokenUtils.CONTEXTO_APPX)){
                    val data=restActividad.reportarActividad(TokenUtils.TOKEN_CONTENT).body()!!
                    actividadDao.insertarActividades(data)
                }
            }
        }catch (e:Exception){
            Log.i("ERROR", "Error: ${e.message}")
        }
        return actividadDao.reportatActividad()
    }

    override fun buscarActividadId(id:Long):LiveData<Actividad>{
        return  actividadDao.buscarActividad(id)
    }


    override suspend fun insertarActividad(actividad: Actividad):Boolean{
        return restActividad.insertarActividad(TokenUtils.TOKEN_CONTENT, actividad).body()!=null
    }

    override suspend fun modificarRemoteActividad(actividad: Actividad):Boolean{
        var dd:Boolean=false
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("VER", TokenUtils.TOKEN_CONTENT)
        }
        return restActividad.actualizarActividad(TokenUtils.TOKEN_CONTENT, actividad.id, actividad).body()!=null
    }

}