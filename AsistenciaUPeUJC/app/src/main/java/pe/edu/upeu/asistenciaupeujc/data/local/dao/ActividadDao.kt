package pe.edu.upeu.asistenciaupeujc.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import pe.edu.upeu.asistenciaupeujc.modelo.Actividad

@Dao
interface ActividadDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarActividad(actividad: Actividad)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarActividades(actividad: List<Actividad>)

    @Update
    suspend fun modificarActividad(actividad: Actividad)

    @Delete
    suspend fun eliminarActividad(actividad: Actividad)

    @Query("select * from actividad")
    fun reportatActividad():LiveData<List<Actividad>>

    @Query("select * from actividad where id=:idx")
    fun buscarActividad(idx: Long):LiveData<Actividad>

}