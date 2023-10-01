package pe.edu.upeu.asistenciaupeujc.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inscritox"
    /*,
    foreignKeys = [ForeignKey(entity = Actividad::class, parentColumns = ["id"], childColumns = ["actividadId"], onDelete = ForeignKey.CASCADE)
    ]*/
)
data class Inscritox(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var cui: String,
    var tipoCui: String,
    var evidensPay: String,
    var offlinex: String,
    var actividadId: Long,
)

data class InscritoxConActividad(
    var id: Long,
    var cui: String,
    var tipoCui: String,
    var evidensPay: String,
    var offlinex: String,
    var actividadId: Long,
    var nombreActividad: String
)

data class InscritoxReport(
    var id: Long,
    var cui: String,
    var tipoCui: String,
    var evidensPay: String,
    var offlinex: String,
    var actividadId: Actividad
)

