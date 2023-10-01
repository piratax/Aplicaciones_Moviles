package pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.actividad

import android.annotation.SuppressLint
import android.os.Looper
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.k0shk0sh.compose.easyforms.BuildEasyForms
import com.github.k0shk0sh.compose.easyforms.EasyFormsResult
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc.modelo.Actividad
import pe.edu.upeu.asistenciaupeujc.modelo.ComboModel
import pe.edu.upeu.asistenciaupeujc.ui.navigation.Destinations
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.Spacer
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.AccionButtonCancel
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.AccionButtonSuccess
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.ComboBox
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.ComboBoxTwo
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.DatePickerCustom
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.DropdownMenuCustom
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.MyFormKeys
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.NameTextField
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.TimePickerCustom
import pe.edu.upeu.asistenciaupeujc.utils.TokenUtils

@Composable
fun ActividadForm(
    text: String,
    darkMode: MutableState<Boolean>,
    navController: NavHostController,
    viewModel: ActividadFormViewModel= hiltViewModel()
) {
    val actividadD:Actividad
    if (text!="0"){
        actividadD = Gson().fromJson(text, Actividad::class.java)
    }else{
        actividadD= Actividad(0,"","", "","","","","","","","", "", "", "", "")
    }
    val isLoading by viewModel.isLoading.observeAsState(false)
    formulario(actividadD.id!!,
        darkMode,
        navController,
        actividadD,
        viewModel
    )

}



@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MissingPermission",
    "CoroutineCreationDuringComposition"
)
@Composable
fun formulario(id:Long,
               darkMode: MutableState<Boolean>,
               navController: NavHostController,
               actividad:Actividad,
               viewModel: ActividadFormViewModel){

    Log.i("VERRR", "d: "+actividad?.id!!)
    val person=Actividad(0,"","", "","","","","","","","", "", "", "", "")
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var locationCallback: LocationCallback? = null
    var fusedLocationClient: FusedLocationProviderClient? = null
    fusedLocationClient = LocationServices.getFusedLocationProviderClient(
        context)
    locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            for (lo in p0.locations) {
                Log.e("LATLONX", "Lat: ${lo.latitude} Lon: ${lo.longitude}")
                person.latitud=lo.latitude.toString()
                person.longitud=lo.longitude.toString()
            }
        }
    }
    scope.launch{
        val locationRequest = LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        fusedLocationClient!!.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())

        Log.e("LATLON", "Lat: ${person.latitud} Lon: ${person.longitud}")
        delay(1500L)
        if (fusedLocationClient != null) {
            fusedLocationClient!!.removeLocationUpdates(locationCallback);
            fusedLocationClient = null;
        }

    }

    Scaffold(modifier = Modifier.padding(top = 60.dp, start = 16.dp, end = 16.dp, bottom = 32.dp)){
        BuildEasyForms { easyForm ->
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                NameTextField(easyForms = easyForm, text =actividad?.nombreActividad!!,"Nomb. Actividad:", MyFormKeys.NAME )
                var listE = listOf(
                    ComboModel("Activo","Activo"),
                    ComboModel("Desactivo","Desactivo"),
                )
                ComboBox(easyForm = easyForm, "Estado:", actividad?.estado!!, listE)

                var listEv = listOf(
                    ComboModel("SI","SI"),
                    ComboModel("NO","NO"),
                )
                ComboBoxTwo(easyForm = easyForm, "Evaluar:", actividad?.evaluar!!, listEv)


                DatePickerCustom(easyForm = easyForm, label = "Fecha", texts = actividad?.fecha!!, MyFormKeys.FECHA,"yyyy-MM-dd")
                TimePickerCustom(easyForm = easyForm, label = "Hora", texts = actividad?.horai!!, MyFormKeys.TIME, "HH:mm:ss")
                TimePickerCustom(easyForm = easyForm, label = "Min. Toler", texts = actividad?.minToler!!, MyFormKeys.TIME_TOLER,"HH:mm:ss")
                NameTextField(easyForms = easyForm, text = actividad?.mater!!, "Materiales:", MyFormKeys.MATERIALES )
                DropdownMenuCustom(easyForm = easyForm, label = "Validar Inscripcion:", actividad.validInsc, list =listEv, MyFormKeys.VALIDINSCRIP )
                DropdownMenuCustom(easyForm = easyForm, label = "Validar Asis. SubActividad:", actividad.asisSubact, list =listEv, MyFormKeys.ASISSUBACT )
                DropdownMenuCustom(easyForm = easyForm, label = "Reg. Entrada y Salida:", actividad.entsal, list =listEv, MyFormKeys.ENTSAL )
                DropdownMenuCustom(easyForm = easyForm, label = "Reg. Offline:", actividad.offlinex, list =listEv, MyFormKeys.OFFLINE )

                Row(Modifier.align(Alignment.CenterHorizontally)){
                    AccionButtonSuccess(easyForms = easyForm, "Guardar", id){
                        val lista=easyForm.formData()
                        person.nombreActividad=(lista.get(0) as EasyFormsResult.StringResult).value
                        person.estado=splitCadena((lista.get(1) as EasyFormsResult.GenericStateResult<String>).value)
                        person.evaluar=splitCadena((lista.get(2) as EasyFormsResult.GenericStateResult<String>).value)
                        person.fecha=(lista.get(3) as EasyFormsResult.GenericStateResult<String>).value
                        person.horai=(lista.get(4) as EasyFormsResult.GenericStateResult<String>).value
                        person.minToler=(lista.get(5) as EasyFormsResult.GenericStateResult<String>).value
                        person.mater=(lista.get(6) as EasyFormsResult.StringResult).value
                        person.validInsc= splitCadena((lista.get(7) as EasyFormsResult.GenericStateResult<String>).value)
                        person.asisSubact= splitCadena((lista.get(8) as EasyFormsResult.GenericStateResult<String>).value)
                        person.entsal= splitCadena((lista.get(9) as EasyFormsResult.GenericStateResult<String>).value)
                        person.offlinex= splitCadena((lista.get(10) as EasyFormsResult.GenericStateResult<String>).value)
                        person.userCreate= TokenUtils.USER_LOGIN

                        if (id==0.toLong()){
                            Log.i("AGREGAR", "M:"+ person.mater)
                            Log.i("AGREGAR", "VI:"+ person.validInsc)
                            Log.i("AGREGAR", "SA:"+ person.asisSubact)
                            Log.i("AGREGAR", "ES:"+ person.entsal)
                            Log.i("AGREGAR", "OF:"+ person.offlinex)
                            viewModel.addActividad(person)
                        }else{
                            person.id=id
                            Log.i("MODIFICAR", "M:"+person)
                            viewModel.editActividad(person)
                        }
                        navController.navigate(Destinations.ActividadUI.route)
                    }
                    Spacer()
                    AccionButtonCancel(easyForms = easyForm, "Cancelar"){
                        navController.navigate(Destinations.ActividadUI.route)
                    }
                }
            }
        }
    }
}


fun splitCadena(data:String):String{
    return if(data!="") data.split("-")[0] else ""
}