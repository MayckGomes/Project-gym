package mayckgomes.com.projectgym.ui.Components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import mayckgomes.com.projectgym.ui.theme.Yellow

@Composable
fun StyledTextFieldNumber(
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable (() -> Unit)? = null,
    enabled: Boolean
){
    OutlinedTextField(
        modifier = Modifier
            .size(83.dp),
        value = value,
        onValueChange = onValueChange,
        label = label,
        enabled = enabled,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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