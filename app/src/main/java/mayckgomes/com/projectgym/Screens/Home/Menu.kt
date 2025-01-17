package mayckgomes.com.projectgym.Screens.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import mayckgomes.com.projectgym.DataTypes.NavBarItem
import mayckgomes.com.projectgym.DataTypes.User
import mayckgomes.com.projectgym.funcs.Tela
import mayckgomes.com.projectgym.ui.theme.Gray
import mayckgomes.com.projectgym.ui.theme.LightGray
import mayckgomes.com.projectgym.ui.theme.Yellow


@Composable
fun Menu(navController: NavController){

    var telaName by rememberSaveable {
        mutableStateOf("Home")
    }

    val navItems = listOf(
        NavBarItem(
            title = "Home",
            iconUnselected = Icons.Outlined.Home,
            iconSelected = Icons.Filled.Home,
            screen = "Home"
        ),
        NavBarItem(
            title = "Treino",
            iconUnselected = Icons.Outlined.PlayArrow,
            iconSelected = Icons.Filled.PlayArrow,
            screen = "Treino"
        ),
        NavBarItem(
            title = "Perfil",
            iconUnselected = Icons.Outlined.AccountCircle,
            iconSelected = Icons.Filled.AccountCircle,
            screen = "Perfil"
        )
    )

    Scaffold(
        bottomBar = {

            var itemSelected by rememberSaveable {
                mutableStateOf("Home")
            }

            NavigationBar(
                modifier = Modifier.background(color = LightGray),
                containerColor = LightGray,

            ) {
                navItems.forEach{ navBarItem ->
                    NavigationBarItem(
                        selected = if (itemSelected == navBarItem.title){true}else{false},
                        onClick = {
                            itemSelected=navBarItem.title
                            telaName = navBarItem.screen
                                  },
                        icon = {
                            if(itemSelected == navBarItem.title){
                                Icon(navBarItem.iconSelected, contentDescription = null, tint = Yellow)
                            }else{
                            Icon(navBarItem.iconUnselected, contentDescription = null, tint = Yellow)
                            }},
                        label = {
                            if(itemSelected == navBarItem.title){
                                Text(navBarItem.title, color = Yellow, fontWeight = FontWeight.Bold)
                            } else {
                                Text(navBarItem.title, color = Yellow)
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Gray
                        )
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
            Tela(telaName, navController = navController)
        }
    }

}
