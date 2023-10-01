package pe.edu.upeu.asistenciaupeujc.ui.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc.ui.navigation.Destinations
import pe.edu.upeu.asistenciaupeujc.ui.navigation.currentRoute
import pe.edu.upeu.asistenciaupeujc.R

@Composable
fun DrawerItem(
    item: Destinations,
    selected: Boolean,
    onItemClick: (Destinations) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(6.dp)
            .clip(RoundedCornerShape(12))
            .background(
                if (selected) Color.Blue.copy(alpha = 0.25f) else
                    Color.Transparent
            )
            .padding(8.dp)
            .clickable { onItemClick(item) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(32.dp),
            imageVector = item.icon,
            contentDescription = item.title,
            tint = if (selected) Color.Blue else Color.Gray
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = item.title,
            style = TextStyle(fontSize = 18.sp),
            color = if (selected) Color.Blue else Color.Black
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Drawer(
    scope: CoroutineScope,
    scaffoldState: DrawerState,
    navController: NavHostController,
    items: List<Destinations>
) {
    Column {
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = "Bg Image",
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(15.dp)
        )
        val currentRoute = currentRoute(navController)
        items.forEach { item ->
            DrawerItem(
                item = item,
                selected = currentRoute ==item.route
            ) {
                navController.navigate(item.route) {
                    launchSingleTop = true
                }
                scope.launch {
                    scaffoldState.close()
                }
            }
        }
    }
}