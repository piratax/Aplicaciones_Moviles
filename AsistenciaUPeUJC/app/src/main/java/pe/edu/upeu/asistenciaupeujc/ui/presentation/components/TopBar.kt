package pe.edu.upeu.asistenciaupeujc.ui.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    titulo:String,
    scope: CoroutineScope,
    scaffoldState: DrawerState,
    openDialog: () -> Unit,
    displaySnackBar: () -> Unit
) {

    TopAppBar(
        title = {
            //Text(LocalContext.current.getString(R.string.app_name))
                Text(text = titulo)
        },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.open()
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu, contentDescription
                    = "Menu Icon"
                )
            }
        },
        actions = {
            IconButton(onClick = {
                displaySnackBar()
            }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            }
            IconButton(onClick = {
                openDialog()
            }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            }
        }
    )
}