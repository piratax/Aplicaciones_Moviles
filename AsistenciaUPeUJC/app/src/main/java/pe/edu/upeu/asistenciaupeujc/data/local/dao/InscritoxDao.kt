package pe.edu.upeu.asistenciaupeujc.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import androidx.room.Update
import pe.edu.upeu.asistenciaupeujc.modelo.Inscritox
import pe.edu.upeu.asistenciaupeujc.modelo.InscritoxConActividad


@Dao
interface InscritoxDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarInscritox(inscritox: Inscritox)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarInscritoxes(inscritox: List<Inscritox>)

    @Update
    suspend fun modificarInscritox(inscritox: Inscritox)

    @Query("delete  from inscritox where id=:inscritox")
    suspend fun eliminarInscritox(inscritox: Long)

    /*@Delete
    suspend fun eliminarMaterialesx(materialesx: Materialesx)*/
    @Transaction
    @Query("select m.*, a.nombreActividad from inscritox m, actividad a where m.actividadId=a.id")
    fun reportarInscritox():LiveData<List<InscritoxConActividad>>



    @Query("select * from inscritox where id=:idx")
    fun buscarInscritox(idx: Long): LiveData<Inscritox>
}