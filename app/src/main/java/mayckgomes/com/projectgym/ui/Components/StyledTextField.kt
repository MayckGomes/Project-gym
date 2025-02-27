package mayckgomes.com.projectgym.ui.Components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import mayckgomes.com.projectgym.ui.theme.Yellow

@Composable
fun StyledTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,

){
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = label,
        enabled = enabled,
        shape = RoundedCornerShape(15),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Yellow,
            unfocusedTextColor = Color.White,
            disabledTextColor = Color.White,
            focusedIndicatorColor = Yellow,
            unfocusedIndicatorColor = Yellow,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent
        )
    )
}