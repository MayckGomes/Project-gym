package mayckgomes.com.projectgym.DataTypes

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class NavBarItem(
    val title:String,
    val icon:ImageVector,
    val screen:String
)