package mayckgomes.com.projectgym.ui.Components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import mayckgomes.com.projectgym.ui.theme.Gray
import mayckgomes.com.projectgym.ui.theme.White
import mayckgomes.com.projectgym.ui.theme.Yellow


@Composable
fun StyledAlertDialog(
    title: String,
    text: String,
    confirmButton: () -> Unit,
    onDismissRequest: () -> Unit,
    icon: ImageVector
){

    AlertDialog(
        onDismissRequest = {onDismissRequest()},
        confirmButton = {
            TextButton(onClick = {confirmButton()}) {
                Text("Sim", fontWeight = FontWeight.Bold, color = Yellow)
            }
        },
        dismissButton = {
            TextButton(onClick = {onDismissRequest()}) {
                Text("Não", fontWeight = FontWeight.Bold, color = Color.Red)
            }
        },
        title = { Text(title) },
        text = { Text(text) },
        icon = { Icon(icon,contentDescription = null) },
        containerColor = Gray,
        titleContentColor = Yellow,
        textContentColor = White,
        iconContentColor = Yellow
    )

}