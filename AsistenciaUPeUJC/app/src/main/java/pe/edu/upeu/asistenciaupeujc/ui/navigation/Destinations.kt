package pe.edu.upeu.asistenciaupeujc.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Login:Destinations("login", "Login", Icons.Filled.Settings)
    object Pantalla1 : Destinations( "pantalla1", "Pantalla 1", Icons.Filled.Home )
    object Pantalla2 : Destinations("pantalla2/?newText={newText}", "Pantalla 2", Icons.Filled.Settings) {
        fun createRoute(newText: String) = "pantalla2/?newText=$newText"
    }
    object Pantalla3 : Destinations("pantalla3", "Pantalla 3", Icons.Filled.Favorite)
    object Pantalla4 : Destinations("pantalla4", "Pantalla 4x", Icons.Filled.Face )

    object Pantalla5 : Destinations("pantalla5", "Pantalla 5x", Icons.Filled.AccountCircle )

    object ActividadUI: Destinations("actividadUI","Adm. Actividades", Icons.Filled.DateRange)

    object ActividadForm: Destinations("actividadForm?actId={actId}", "Form Actividad", Icons.Filled.Add){
        fun passId(actId:String?):String{
            return "actividadForm?actId=$actId"
        }
    }

    object MaterialesxUI: Destinations("materialesxUI","Adm. Materialesxes", Icons.Filled.DateRange)

    object MaterialesxForm: Destinations("MaterialesxForm?matId={matId}", "Form Materialesx", Icons.Filled.Add){
        fun passId(matId:String?):String{
            return "materialesxForm?matId=$matId"
        }
    }

    object InscritoxUI: Destinations("inscritoxUI","Adm. Inscritoxes", Icons.Filled.DateRange)

    object InscritoxForm: Destinations("InscritoxForm?matId={matId}", "Form Inscritox", Icons.Filled.Add){
        fun passId(matId:String?):String{
            return "inscritoxForm?matId=$matId"
        }
    }


}