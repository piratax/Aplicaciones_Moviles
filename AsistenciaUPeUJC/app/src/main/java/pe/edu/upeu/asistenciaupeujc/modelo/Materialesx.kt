package pe.edu.upeu.asistenciaupeujc.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "materialesx"
    /*,
    foreignKeys = [ForeignKey(entity = Actividad::class, parentColumns = ["id"], childColumns = ["actividadId"], onDelete = ForeignKey.CASCADE)
    ]*/
)
data class Materialesx(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var cui: String,
    var tipoCui: String,
    var materEntre: String,
    var fecha: String,
    var horaReg: String,
    var latituda: String,
    var longituda: String,
    var modFh: String,
    var offlinex: String,
    var actividadId: Long,
)

data class MaterialesxConActividad(
    var id: Long,
    var cui: String,
    var tipoCui: String,
    var materEntre: String,
    var fecha: String,
    var horaReg: String,
    var latituda: String,
    var longituda: String,
    var modFh: String,
    var offlinex: String,
    var actividadId: Long,
    var nombreActividad: String
)

data class MaterialesxReport(
    var id: Long,
    var cui: String,
    var tipoCui: String,
    var materEntre: String,
    var fecha: String,
    var horaReg: String,
    var latituda: String,
    var longituda: String,
    var modFh: String,
    var offlinex: String,
    var actividadId: Actividad
)

