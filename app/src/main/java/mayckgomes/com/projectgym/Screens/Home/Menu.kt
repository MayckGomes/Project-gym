package mayckgomes.com.projectgym.Screens.Home

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import mayckgomes.com.projectgym.DataTypes.NavBarItem
import mayckgomes.com.projectgym.DataTypes.User
import mayckgomes.com.projectgym.funcs.Tela
import mayckgomes.com.projectgym.ui.theme.Gray
import mayckgomes.com.projectgym.ui.theme.LightGray
import mayckgomes.com.projectgym.ui.theme.Yellow


@Composable
fun Menu(user: User,navController: NavController){

    var telaName by rememberSaveable {
        mutableStateOf("Home")
    }

    val navItems = listOf(
        NavBarItem(
            title = "Home",
            icon = Icons.Default.Home,
            screen = "Home"
        ),
        NavBarItem(
            title = "Treino",
            icon = Icons.Default.PlayArrow,
            screen = "Treino"
        ),
        NavBarItem(
            title = "Perfil",
            icon = Icons.Default.Person,
            screen = "Perfil"
        )
    )

    Scaffold(
        bottomBar = {

            NavigationBar(
                modifier = Modifier.background(color = LightGray),
                containerColor = LightGray,

            ) {
                navItems.forEachIndexed { index, navBarItem ->
                    NavigationBarItem(
                        selected = false,
                        onClick = { telaName = navBarItem.screen },
                        icon = { Icon(navBarItem.icon, contentDescription = null, tint = Yellow) },
                        label = { Text(navBarItem.title, color = Yellow) }
                    )
                }

            }

        }

    ){ innerpadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
                .background(color = Gray)
        ) {
            Tela(telaName, user, navController = navController)
        }
    }

}
