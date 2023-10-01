package pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.actividad

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.google.gson.Gson
import pe.edu.upeu.asistenciaupeujc.modelo.Actividad
import pe.edu.upeu.asistenciaupeujc.ui.navigation.Destinations
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.ConfirmDialog
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.Spacer
import pe.edu.upeu.asistenciaupeujc.utils.TokenUtils
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import pe.edu.upeu.asistenciaupeujc.R
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.BottomNavigationBar
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.FabItem
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.LoadingCard
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.MultiFloatingActionButton

@Composable
fun ActividadUI (navegarEditarAct: (String) -> Unit, viewModel:
ActividadViewModel= hiltViewModel(), navController: NavHostController
){
    val actis by viewModel.activ.observeAsState(arrayListOf())
    val isLoading by viewModel.isLoading.observeAsState(false)
    Log.i("VERX", ""+actis!!.size )

    MyApp(navController, onAddClick = {
        //viewModel.addUser()
        navegarEditarAct((0).toString())
    }, onDeleteClick = {
        viewModel.deleteActividad(it)
    }, actis, isLoading,
        onEditClick = {
            val jsonString = Gson().toJson(it)
            navegarEditarAct(jsonString)
        }
    )
}


val formatoFecha: DateTimeFormatter? = DateTimeFormatter.ofPattern("dd-MM-yyyy")

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyApp( navController: NavHostController,
    onAddClick: (() -> Unit)? = null,
    onDeleteClick: ((toDelete: Actividad) -> Unit)? = null,
    actividades: List<Actividad>,
    isLoading: Boolean,
    onEditClick: ((toPersona: Actividad) -> Unit)? = null,
) {
    val context = LocalContext.current
    //val navController = rememberNavController()
    val navigationItems2 = listOf(
        Destinations.ActividadUI,
        Destinations.Pantalla1,
        Destinations.Pantalla2,
        Destinations.Pantalla3
    )
  /*  val scaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(initialValue =
        DrawerValue.Closed)
    )*/

    val fabItems = listOf(
        FabItem(
            Icons.Filled.ShoppingCart,
            "Shopping Cart"
        ) {
            val toast = Toast.makeText(context, "Hola Mundo", Toast.LENGTH_LONG) // in Activity
            toast.view!!.getBackground().setColorFilter(Color.CYAN, PorterDuff.Mode.SRC_IN)
            toast.show()
        },
        FabItem(
            Icons.Filled.Favorite,
            "Add Actvidad"
        ) { onAddClick?.invoke() }
    )

    Scaffold(
        bottomBar = {
            BottomAppBar {
            BottomNavigationBar(navigationItems2, navController = navController)
            }
        },
        modifier = Modifier,
        floatingActionButton = {
            MultiFloatingActionButton(
                navController=navController,
                fabIcon = Icons.Filled.Add,
                items = fabItems,
                showLabels = true
            )
        },
    floatingActionButtonPosition = FabPosition.End,
    ) {
        Box(modifier = Modifier.fillMaxSize()){
            LazyColumn(modifier = Modifier
                .padding(top = 60.dp, start = 16.dp, end = 16.dp, bottom = 32.dp)
                .align(alignment = Alignment.TopCenter),
                //.offset(x = (16).dp, y = (-32).dp),
                userScrollEnabled= true,
            ){
                var itemCount = actividades.size
                if (isLoading) itemCount++
                items(count = itemCount) { index ->
                    var auxIndex = index;
                    if (isLoading) {
                        if (auxIndex == 0)
                            return@items LoadingCard()
                        auxIndex--
                    }
                    val actividad = actividades[auxIndex]
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 1.dp
                        ),
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .fillMaxWidth(),
                    ) {
                        Row(modifier = Modifier.padding(8.dp)) {
                            Image(
                                modifier = Modifier
                                    .size(50.dp)
                                    //.clip(CircleShape)
                                    .clip(RoundedCornerShape(8.dp)),
                                painter = rememberImagePainter(
                                    data = actividad.evaluar,
                                    builder = {
                                        placeholder(R.drawable.bg)
                                        error(R.drawable.bg)
                                    }
                                ),
                                contentDescription = null,
                                contentScale = ContentScale.FillHeight
                            )
                            Spacer()
                            Column(
                                Modifier.weight(1f),
                            ) {
                                Text("${actividad.nombreActividad} - ${actividad.estado}", fontWeight = FontWeight.Bold)
                                val datex = LocalDate.parse(actividad.fecha!!, DateTimeFormatter.ISO_DATE)
                                var fecha=formatoFecha?.format(datex)
                                Text(""+fecha, color =
                                MaterialTheme.colorScheme.primary)
                            }

                           Spacer()
                            val showDialog = remember { mutableStateOf(false) }
                            IconButton(onClick = {
                                showDialog.value = true
                            }) {
                                Icon(Icons.Filled.Delete, "Remove", tint = MaterialTheme.colorScheme.primary)
                            }
                            if (showDialog.value){
                                ConfirmDialog(
                                    message = "Esta seguro de eliminar?",
                                    onConfirm = {
                                        onDeleteClick?.invoke(actividad)
                                        showDialog.value=false
                                    },
                                    onDimins = {
                                        showDialog.value=false
                                    }
                                )
                            }

                            IconButton(onClick = {
                                Log.i("VERTOKEN", "Holas")
                                Log.i("VERTOKEN", TokenUtils.TOKEN_CONTENT)
                                onEditClick?.invoke(actividad)
                            }) {
                                Icon(
                                    Icons.Filled.Edit,
                                    contentDescription = "Editar",
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                            }


                        }
                    }
                }
            }

        }

    }
}