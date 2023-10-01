package pe.edu.upeu.asistenciaupeujc.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc.data.local.dao.InscritoxDao
import pe.edu.upeu.asistenciaupeujc.data.remote.RestInscritox
import pe.edu.upeu.asistenciaupeujc.modelo.Inscritox
import pe.edu.upeu.asistenciaupeujc.modelo.InscritoxConActividad
import pe.edu.upeu.asistenciaupeujc.utils.TokenUtils
import javax.inject.Inject

interface InscritoxRepository {

    suspend fun deleteInscritox(inscritox: InscritoxConActividad)
    fun reportarInscritoxes(): LiveData<List<InscritoxConActividad>>

    fun buscarInscritoxId(id:Long): LiveData<Inscritox>

    suspend fun insertarInscritox(inscritox: Inscritox):Boolean

    suspend fun modificarRemoteInscritox(inscritox: Inscritox):Boolean
}

class InscritoxRepositoryImp @Inject constructor(
    private val restInscritox: RestInscritox,
    private val inscritoxDao: InscritoxDao
): InscritoxRepository{
    override suspend fun deleteInscritox(inscritox: InscritoxConActividad){
        CoroutineScope(Dispatchers.IO).launch {
            restInscritox.deleteInscritox(TokenUtils.TOKEN_CONTENT,inscritox.id)
        }
        inscritoxDao.eliminarInscritox(inscritox.id)
    }


    override fun reportarInscritoxes(): LiveData<List<InscritoxConActividad>> {
        try {
            CoroutineScope(Dispatchers.IO).launch{
                delay(3000)
                val data=restInscritox.reportarInscritox(TokenUtils.TOKEN_CONTENT).body()!!
                val dataxx = data.map {
                    Inscritox(it.id, it.cui, it.tipoCui,it.evidensPay,it.offlinex, it.actividadId.id)
                }
                inscritoxDao.insertarInscritoxes(dataxx)
            }

        }catch (e:Exception){
            Log.i("ERROR", "Error: ${e.message}")
        }
        return inscritoxDao.reportarInscritox()
    }

    override fun buscarInscritoxId(id:Long): LiveData<Inscritox> {
        return  inscritoxDao.buscarInscritox(id)
    }


    override suspend fun insertarInscritox(inscritox: Inscritox):Boolean{
        //Log.i("DATAXXX", "${inscritox.actividadId}")
        return restInscritox.insertarInscritox(TokenUtils.TOKEN_CONTENT, inscritox).body()!=null
    }

    override suspend fun modificarRemoteInscritox(inscritox: Inscritox):Boolean{

        CoroutineScope(Dispatchers.IO).launch {
            Log.i("VER", TokenUtils.TOKEN_CONTENT)
        }
        return restInscritox.actualizarInscritox(TokenUtils.TOKEN_CONTENT, inscritox.id, inscritox).body()!=null
    }
}