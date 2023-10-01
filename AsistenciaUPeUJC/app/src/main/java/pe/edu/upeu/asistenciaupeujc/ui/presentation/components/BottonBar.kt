package pe.edu.upeu.asistenciaupeujc.ui.presentation.components

import android.graphics.drawable.Icon
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import pe.edu.upeu.asistenciaupeujc.ui.navigation.Destinations

@Composable
fun BottomNavigationBar(items: List<Destinations>, navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoutex = navBackStackEntry?.destination?.route
    if (currentRoutex == null || currentRoutex == Destinations.Login.route)
    {
        return
    }

    /*val items = listOf(
        Destinations.Pantalla1,
        Destinations.Pantalla2,
        Destinations.Pantalla3,
    )*/
    var selectedItem by remember { mutableStateOf(0) }
    var currentRoute by remember { mutableStateOf(Destinations.Pantalla1.route) }

    items.forEachIndexed { index, navigationItem ->
        if (navigationItem.route == currentRoute) {
            selectedItem = index
        }
    }

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                icon = { Icon(item.icon!!, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    currentRoute = item.route
                    navController.navigate(item.route) {
                        if(item.route=="pantalla1"){
                            popUpTo(item.route)
                        }else{
                            launchSingleTop = true
                            restoreState = true
                        }
                        /*navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }*/

                    }
                }
            )
        }
    }
}