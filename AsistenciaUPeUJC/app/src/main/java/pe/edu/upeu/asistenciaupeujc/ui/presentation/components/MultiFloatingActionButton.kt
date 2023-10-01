package pe.edu.upeu.asistenciaupeujc.ui.presentation.components

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import pe.edu.upeu.asistenciaupeujc.ui.navigation.Destinations


class FabItem(
    val icon: ImageVector,
    val label: String,
    val onFabItemClicked: () -> Unit
)
enum class MultiFabState {
    COLLAPSED, EXPANDED
}

@Composable
fun SmallFloatingActionButtonRow(
    item: FabItem,
    showLabel: Boolean,
    stateTransition: Transition<MultiFabState>
) {
    // Mini Fab Alpha Animation
    val alpha: Float by stateTransition.animateFloat(
        transitionSpec = {
            tween(durationMillis = 50)
        }, label = ""
    ) { state ->
        if (state == MultiFabState.EXPANDED) 1f else 0f
    }
    // Mini Fab Scale Animation
    val scale: Float by stateTransition.animateFloat(
        label = ""
    ) { state ->
        if (state == MultiFabState.EXPANDED) 1.0f else 0f
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .alpha(animateFloatAsState((alpha)).value)
            .scale(animateFloatAsState(targetValue = scale).value)
    ) {
        if (showLabel) {
            val backgroundColor = MaterialTheme.colorScheme.primaryContainer
            Text(
                text = item.label,
                color = contentColorFor(backgroundColor = backgroundColor),
                modifier = Modifier
                    .background(
                        color = backgroundColor,
                        shape = RoundedCornerShape(12.0.dp)
                    )
                    .padding(start = 6.dp, end = 6.dp, top = 4.dp, bottom = 4.dp)
                    .clickable(onClick = { item.onFabItemClicked() })
            )
        }
        SmallFloatingActionButton(
            modifier = Modifier
                .padding(4.dp),
            onClick = { item.onFabItemClicked() },
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 2.dp,
                hoveredElevation = 4.dp
            )
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.label
            )
        }

    }
}

@Composable
fun MultiFloatingActionButton(
    navController:NavHostController,
    fabIcon: ImageVector,
    items: List<FabItem>,
    showLabels: Boolean = true,
    onStateChanged: ((state: MultiFabState) -> Unit)? = null
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    if (currentRoute == null || currentRoute == Destinations.Login.route)
    {
        return
    }

    var currentState by remember { mutableStateOf(MultiFabState.COLLAPSED) }
    val stateTransition: Transition<MultiFabState> =
        updateTransition(targetState = currentState, label = "")
    // State Change
    val stateChange: () -> Unit = {
        currentState = if (stateTransition.currentState == MultiFabState.EXPANDED) {
            MultiFabState.COLLAPSED
        } else MultiFabState.EXPANDED
        onStateChanged?.invoke(currentState)
    }
    // Fab Rotation Animation
    val rotation: Float by stateTransition.animateFloat(
        transitionSpec = {
            if (targetState == MultiFabState.EXPANDED) {
                spring(stiffness = Spring.StiffnessLow)
            } else {
                spring(stiffness = Spring.StiffnessMedium)
            }
        },
        label = ""
    ) { state ->
        if (state == MultiFabState.EXPANDED) 45f else 0f
    }

    Column(
        horizontalAlignment = Alignment.End,
    ) {
        items.forEach { item ->
            SmallFloatingActionButtonRow(
                item = item,
                stateTransition = stateTransition,
                showLabel = showLabels
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
        FloatingActionButton(onClick = {
            stateChange()
        }) {
            Icon(
                imageVector = fabIcon,
                contentDescription = "",
                modifier = Modifier.rotate(rotation)
            )
        }
    }
}