package mayckgomes.com.projectgym.funcs

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import mayckgomes.com.projectgym.DataTypes.User
import mayckgomes.com.projectgym.Screens.Home.HomeScreen
import mayckgomes.com.projectgym.Screens.Home.ProfileScreen
import mayckgomes.com.projectgym.Screens.Home.TrainingScreen

@Composable
fun Tela(tela:String, user:User,navController: NavController){

    when (tela){
        "Home" -> HomeScreen(user.Nome)
        "Treino" -> TrainingScreen(navController,user.listaTreinos)
        "Perfil" -> ProfileScreen(user.Nome)
    }

}