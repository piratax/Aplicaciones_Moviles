package pe.edu.upeu.asistenciaupeujc.ui.presentation.components
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import pe.edu.upeu.asistenciaupeujc.ui.theme.md_theme_light_onSecondary
import pe.edu.upeu.asistenciaupeujc.ui.theme.md_theme_light_primary
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pe.edu.upeu.asistenciaupeujc.ui.theme.md_theme_light_scrim

data class FabButtonItem(val iconRes: ImageVector, val label: String)

/**
 * Represents the main floating action button (FAB) with an icon and optional rotation.
 * The main FAB is the primary action button that can be expanded to reveal sub-items.
 */
interface FabButtonMain {
    val iconRes: ImageVector
    val iconRotate: Float?
}

/**
 * Implementation of [FabButtonMain] interface.
 *
 * @property iconRes The [ImageVector] representing the icon to be displayed on the main FAB.
 * @property iconRotate The optional rotation angle for the main FAB icon. If null, the icon will not be rotated.
 */
private class FabButtonMainImpl(
    override val iconRes: ImageVector,
    override val iconRotate: Float?
) : FabButtonMain

/**
 * Creates a new instance of [FabButtonMain] with the provided icon and optional rotation.
 *
 * @param iconRes The [ImageVector] representing the icon to be displayed on the main FAB.
 * @param iconRotate The optional rotation angle for the main FAB icon. If null, the icon will not be rotated.
 * @return A new instance of [FabButtonMain] with the specified icon and rotation.
 */
fun FabButtonMain(iconRes: ImageVector = Icons.Filled.Add, iconRotate: Float = 45f): FabButtonMain =
    FabButtonMainImpl(iconRes, iconRotate)

/**
 * Represents the state of a Floating Action Button (FAB), which can be either Collapsed or Expanded.
 * The FAB state is used to determine its visibility and behavior, such as showing or hiding sub-items.
 */
sealed class FabButtonState {
    object Collapsed : FabButtonState()
    object Expand : FabButtonState()

    fun isExpanded() = this == Expand

    fun toggleValue() = if (isExpanded()) {
        Collapsed
    } else {
        Expand
    }
}

/**
 * Remembers the state of a Multi-Floating Action Button (FAB) using [remember] and [mutableStateOf].
 *
 * @return A [MutableState] that holds the current state of the Multi-FAB.
 */
@Composable
fun rememberMultiFabState() =
    remember { mutableStateOf<FabButtonState>(FabButtonState.Collapsed) }

/**
 * Represents a sub-item for a Floating Action Button (FAB) with customized icon and background tints.
 * Sub-items are secondary action buttons that appear when the main FAB is expanded.
 */
interface FabButtonSub {
    val iconTint: Color
    val backgroundTint: Color
}

/**
 * Implementation of [FabButtonSub] interface.
 *
 * @property iconTint The [Color] used to tint the icon of the sub-item.
 * @property backgroundTint The [Color] used to tint the background of the sub-item.
 */
private class FabButtonSubImpl(
    override val iconTint: Color,
    override val backgroundTint: Color
) : FabButtonSub

/**
 * Creates a new instance of [FabButtonSub] with the provided icon and background tints.
 *
 * @param backgroundTint The [Color] used to tint the background of the sub-item.
 * @param iconTint The [Color] used to tint the icon of the sub-item.
 * @return A new instance of [FabButtonSub] with the specified icon and background tints.
 */
fun FabButtonSub(
    backgroundTint: Color = md_theme_light_primary,
    iconTint: Color = md_theme_light_onSecondary
): FabButtonSub = FabButtonSubImpl(iconTint, backgroundTint)


/**
 * Composable function to display a Multi-Floating Action Button (Multi-FAB) that can be expanded to reveal sub-items.
 *
 * @param modifier The optional [Modifier] for customizing the layout of the Multi-FAB.
 * @param items The list of [FabButtonItem] representing the sub-items to be displayed when the Multi-FAB is expanded.
 * @param fabState The [MutableState] representing the current state of the Multi-FAB, whether it is expanded or collapsed.
 * @param fabIcon The [FabButtonMain] representing the main FAB with an icon and optional rotation.
 * @param fabOption The [FabButtonSub] representing the customization options for the sub-items.
 * @param onFabItemClicked The callback function to handle click events on the sub-items.
 * @param stateChanged The optional callback function to notify when the state of the Multi-FAB changes (expanded or collapsed).
 */
@Composable
fun MultiFloatingActionButton(
    modifier: Modifier = Modifier,
    items: List<FabButtonItem>,
    fabState: MutableState<FabButtonState> = rememberMultiFabState(),
    fabIcon: FabButtonMain,
    fabOption: FabButtonSub = FabButtonSub(),
    onFabItemClicked: (fabItem: FabButtonItem) -> Unit,
    stateChanged: (fabState: FabButtonState) -> Unit = {}
) {
    // Animation for rotating the main FAB icon based on its state (expanded or collapsed)
    val rotation by animateFloatAsState(
        if (fabState.value == FabButtonState.Expand) {
            fabIcon.iconRotate ?: 0f
        } else {
            0f
        }, label = "Main Fab Button"
    )

    Column(
        modifier = modifier.wrapContentSize(),
        horizontalAlignment = Alignment.End
    ) {
        // AnimatedVisibility to show or hide the sub-items when the Multi-FAB is expanded or collapsed
        AnimatedVisibility(
            visible = fabState.value.isExpanded(),
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            // LazyColumn to display the sub-items in a vertical list
            LazyColumn(
                modifier = Modifier.wrapContentSize(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(items.size) { index ->
                    // Composable to display each individual sub-item
                    MiniFabItem(
                        item = items[index],
                        fabOption = fabOption,
                        onFabItemClicked = onFabItemClicked
                    )
                }
                item {} // Empty item to provide spacing at the end of the list
            }
        }

        // Main FloatingActionButton representing the Multi-FAB
        FloatingActionButton(
            onClick = {
                fabState.value = fabState.value.toggleValue()
                stateChanged(fabState.value)
            },
            containerColor = fabOption.backgroundTint,
            contentColor = fabOption.iconTint
        ) {
            // Icon for the main FAB with optional rotation based on its state (expanded or collapsed)
            Icon(
                imageVector = fabIcon.iconRes,
                contentDescription = "Main Fab Button",
                modifier = Modifier.rotate(rotation),
                tint = fabOption.iconTint
            )
        }
    }
}

/**
 * Composable function to display an individual sub-item of the Multi-Floating Action Button (Multi-FAB).
 *
 * @param item The [FabButtonItem] representing the sub-item with an icon and label.
 * @param fabOption The [FabButtonSub] representing the customization options for the sub-items.
 * @param onFabItemClicked The callback function to handle click events on the sub-items.
 */
@Composable
fun MiniFabItem(
    item: FabButtonItem,
    fabOption: FabButtonSub,
    onFabItemClicked: (item: FabButtonItem) -> Unit
) {
    Row(
        modifier = Modifier
            .wrapContentSize()
            .padding(end = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Text label for the sub-item displayed in a rounded-corner background
        Text(
            text = item.label,
            style = MaterialTheme.typography.labelSmall,
            color = md_theme_light_onSecondary,
            modifier = Modifier
                .clip(RoundedCornerShape(size = 8.dp))
                .background(md_theme_light_scrim.copy(alpha = 0.5f))
                .padding(all = 8.dp)
        )

        // FloatingActionButton representing the sub-item
        FloatingActionButton(
            onClick = { onFabItemClicked(item) },
            modifier = Modifier.size(40.dp),
            containerColor = fabOption.backgroundTint,
            contentColor = fabOption.iconTint
        ) {
            // Icon for the sub-item with customized tint
            Icon(
                imageVector = item.iconRes,
                contentDescription = "Float Icon",
                tint = fabOption.iconTint
            )
        }
    }
}