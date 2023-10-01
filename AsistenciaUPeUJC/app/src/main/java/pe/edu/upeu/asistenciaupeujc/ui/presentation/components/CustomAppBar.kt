package pe.edu.upeu.asistenciaupeujc.ui.presentation.components

import android.graphics.PorterDuff
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc.ui.navigation.Destinations
import pe.edu.upeu.asistenciaupeujc.ui.theme.ThemeType


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    contDes:String,
    darkMode: MutableState<Boolean>,
    themeType: MutableState<ThemeType>?,
    navController: NavController,
    scope: CoroutineScope,
    scaffoldState: DrawerState,
    openDialog: () -> Unit,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    if (currentRoute == null || currentRoute == Destinations.Login.route)
    {
        return
    }
    Box(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
    ) {
        TopAppBar(
            modifier = Modifier
                //.background(MaterialTheme.colorScheme.secondary)
                .fillMaxWidth()
                .statusBarsPadding(),
            title = {
                Text(
                    text = if (contDes=="") "App Bar" else contDes,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.clickable {
                        themeType!!.value = ThemeType.RED
                    }
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        scaffoldState.open()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription
                        = "Menu Icon"
                    )
                }
            },
            actions = {
                AppBarActions(darkMode, themeType!!, openDialog)
            }
        )
    }
}
@Composable
fun AppBarActions(darkMode: MutableState<Boolean>, themeType:
MutableState<ThemeType>, openDialog: () -> Unit) {
    ChangeMode(darkMode)
    ShareAction(openDialog)
    MoreAction(themeType)
}
@Composable
fun ChangeMode(darkMode: MutableState<Boolean>) {
    val context = LocalContext.current
    IconButton(
        onClick = { darkMode.value = !darkMode.value

        }
    ) {
        Icon(
            imageVector = if (darkMode.value) {
                Icons.Default.Person
            } else {
                Icons.Default.Face
            },
            contentDescription = "Dark mode Icon"
        )
    }
}


@Composable
fun ShareAction( openDialog: () -> Unit) {
    val context = LocalContext.current
    IconButton(
        onClick = {
            openDialog()
            Toast.makeText(context, "Share Clicked!",
                Toast.LENGTH_SHORT).show()
        }
    ) {
        Icon(
            imageVector = Icons.Filled.Share,
            contentDescription = "search_icon",
        )
    }
}
@Composable
fun MoreAction(themeType: MutableState<ThemeType>) {
    var expanded by remember { mutableStateOf(false) }

    //val items = listOf(ThemeType.PURPLE, ThemeType.RED, ThemeType.YELLOW, ThemeType.DROWN, ThemeType.GREEN)

    IconButton(
        onClick = {expanded = true}
    ) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = "search_icon",
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            ThemeType.values().forEach { item ->DropdownMenuItem(
                 onClick = {
                     expanded = false
                     themeType.value = item
                 },
                 text = {
                     Row(verticalAlignment = Alignment.CenterVertically) {
                         RadioButton(selected = themeType.value ==
                                 item, onClick = {
                             themeType.value = item
                         }
                         )
                         Text(text = item.name)
                     }
                 }
             )
             }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CustomAppBarPreview() {
    val isDarkMode = remember { mutableStateOf(false) }
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val openDialog = remember { mutableStateOf(false) }
    CustomTopAppBar("App",isDarkMode, themeType = null,navController,scope,
        drawerState, openDialog = { openDialog.value = true },)
}
