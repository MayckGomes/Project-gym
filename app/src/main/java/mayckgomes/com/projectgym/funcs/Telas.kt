package mayckgomes.com.projectgym.funcs

import androidx.compose.runtime.Composable
import mayckgomes.com.projectgym.Screens.Home.HomeScreen
import mayckgomes.com.projectgym.Screens.Home.ProfileScreen
import mayckgomes.com.projectgym.Screens.Home.TrainingScreen

@Composable
fun Tela(tela:String, userName:String){

    when (tela){
        "Home" -> HomeScreen(userName)
        "Treino" -> TrainingScreen()
        "Perfil" -> ProfileScreen(userName)
    }

}