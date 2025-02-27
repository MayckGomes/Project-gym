package mayckgomes.com.projectgym.DataTypes

import androidx.compose.ui.graphics.vector.ImageVector

data class NavBarItem(
    val title:String,
    val iconUnselected:ImageVector,
    val iconSelected:ImageVector,
    val screen:String
)