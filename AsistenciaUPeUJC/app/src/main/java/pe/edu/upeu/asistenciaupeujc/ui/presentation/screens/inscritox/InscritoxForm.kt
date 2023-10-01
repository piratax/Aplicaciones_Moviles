package pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.inscritox

import android.annotation.SuppressLint
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
import com.google.gson.Gson
import pe.edu.upeu.asistenciaupeujc.modelo.ComboModel
import pe.edu.upeu.asistenciaupeujc.modelo.Inscritox
import pe.edu.upeu.asistenciaupeujc.ui.navigation.Destinations
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.Spacer
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.AccionButtonCancel
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.AccionButtonSuccess
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.ComboBox
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.DropdownMenuCustom
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.MyFormKeys
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.NameTextField

@Composable
fun InscritoxForm(
    text: String,
    darkMode: MutableState<Boolean>,
    navController: NavHostController,
    viewModel: InscritoxFormViewModel= hiltViewModel()
) {
    val inscritoxD:Inscritox
    if (text!="0"){
        inscritoxD = Gson().fromJson(text, Inscritox::class.java)
    }else{
        inscritoxD= Inscritox(0,"","", "","",0)
    }

    formulario(inscritoxD.id!!,
        darkMode,
        navController,
        inscritoxD,
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
               inscritox: Inscritox,
               viewModel: InscritoxFormViewModel){

    Log.i("VERRR", "d: "+inscritox?.id!!)
    val person=Inscritox(0,"","", "","",0)
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val isLoading by viewModel.isLoading.observeAsState(false)
    val actis by viewModel.activ.observeAsState(arrayListOf())


    Scaffold(modifier = Modifier.padding(top = 60.dp, start = 16.dp, end = 16.dp, bottom = 32.dp)){
        BuildEasyForms { easyForm ->
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

                var listE = listOf(
                    ComboModel("SI","SI"),
                    ComboModel("NO","NO"),
                )


                NameTextField(easyForms = easyForm, text =inscritox?.cui!!,"Nomb. Cui:", MyFormKeys.CUI )
                NameTextField(easyForms = easyForm, text =inscritox?.tipoCui!!,"Nomb. tipoCui:", MyFormKeys.TIPOCUI )
                NameTextField(easyForms = easyForm, text =inscritox?.evidensPay!!,"mater_entre:", MyFormKeys.MATERENTRE )
                ComboBox(easyForm = easyForm, "offlinex:", inscritox?.offlinex!!, listE)
                DropdownMenuCustom(easyForm = easyForm, label = "Actividad:", textv ="", viewModel.listE, MyFormKeys.ACTIVIDADID )


                Row(Modifier.align(Alignment.CenterHorizontally)){
                    AccionButtonSuccess(easyForms = easyForm, "Guardar", id){
                        val lista=easyForm.formData()

                        person.cui=(lista.get(0) as EasyFormsResult.StringResult).value
                        person.tipoCui=(lista.get(1) as EasyFormsResult.StringResult).value
                        person.evidensPay=(lista.get(2) as EasyFormsResult.StringResult).value
                        person.offlinex=splitCadena((lista.get(3) as EasyFormsResult.GenericStateResult<String>).value)
                        person.actividadId=splitCadena((lista.get(4) as EasyFormsResult.GenericStateResult<String>).value).toLong()
                        //person.actividadId = (lista.get(7) as EasyFormsResult.StringResult).value.toLong()


                        if (id==0.toLong()){

                            Log.i("AGREGAR", "OF:"+ person.offlinex)
                            Log.i("AGREGARID", "OF:"+ person.actividadId)

                            viewModel.addInscritox(person)

                            navController.navigate(Destinations.InscritoxUI.route)
                        }else{
                            person.id=id
                            Log.i("MODIFICAR", "M:"+person)
                            viewModel.editInscritox(person)
                            navController.navigate(Destinations.InscritoxUI.route)
                        }

                    }
                    Spacer()
                    AccionButtonCancel(easyForms = easyForm, "Cancelar"){
                        navController.navigate(Destinations.InscritoxUI.route)
                    }
                }
            }
        }
    }

}


fun splitCadena(data:String):String{
    return if(data!="") data.split("-")[0] else ""
}