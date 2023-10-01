package pe.edu.upeu.asistenciaupeujc.ui.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import pe.edu.upeu.asistenciaupeujc.ui.navigation.Destinations
import pe.edu.upeu.asistenciaupeujc.ui.navigation.currentRoute
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    items: List<Destinations>
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRouteX = navBackStackEntry?.destination?.route
    if (currentRouteX == null || currentRouteX == Destinations.Pantalla1.route) {
        return
    }

    val currentRoute = currentRoute(navController)
    NavigationBar(
        //backgroundColor = Color(0.0f, 0.8f, 0.8f),
        //contentColor = Color.White
    ) {
        items.forEach { screen -> NavigationBarItem( icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = screen.title
            )
        },
            label = { Text(screen.title) },
            selected = currentRoute == screen.route,
            onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                }
            },
            alwaysShowLabel = false
        )
        }
    }
}