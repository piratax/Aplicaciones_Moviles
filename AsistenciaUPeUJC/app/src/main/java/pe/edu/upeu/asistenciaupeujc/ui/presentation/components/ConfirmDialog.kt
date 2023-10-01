package pe.edu.upeu.asistenciaupeujc.ui.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun ConfirmDialog(
    message: String,
    onConfirm: () -> Unit,
    onDimins: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { },
        title = {
            Text(text = "Confirmación")
        },
        text = {
            Text(text = message)
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = "Confirmar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDimins) {
                Text(text = "Cancelar")
            }
        }
    )
}