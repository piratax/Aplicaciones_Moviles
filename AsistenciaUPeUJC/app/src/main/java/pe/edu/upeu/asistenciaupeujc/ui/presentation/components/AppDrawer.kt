package pe.edu.upeu.asistenciaupeujc.ui.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc.R
import pe.edu.upeu.asistenciaupeujc.ui.navigation.Destinations


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDrawer(
    route: String,
    scope: CoroutineScope,
    scaffoldState: DrawerState,
    navController: NavHostController,
    items: List<Destinations>,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    if (currentRoute == null || currentRoute == Destinations.Login.route)
    {
        return
    }

    ModalDrawerSheet(modifier = Modifier) {
        DrawerHeader(modifier)
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer_padding)))

        items.forEach { item ->
            NavigationDrawerItem(
                label = {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                selected = route == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        if(item.route=="pantalla1"){
                            popUpTo(item.route)
                        }else{
                        launchSingleTop = true
                        restoreState = true}
                    }
                    scope.launch {
                        scaffoldState.close()
                    }
                },
                icon = { Icon(imageVector = item.icon, contentDescription = null) },
                shape = MaterialTheme.shapes.small
            )
        }
    }
}


@Composable
fun DrawerHeader(modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .background(MaterialTheme.colorScheme.secondary)
            .padding(dimensionResource(id = R.dimen.header_padding))
            .fillMaxWidth()
    ) {

        Image(
            painterResource(id = R.drawable.person),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(dimensionResource(id = R.dimen.header_image_size))
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer_padding)))

        Text(
            text = stringResource(id = R.string.app_name),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}
/*
@Preview
@Composable
fun DrawerHeaderPreview() {
    AppDrawer(modifier = Modifier, route = AllDestinations.HOME)
}*/