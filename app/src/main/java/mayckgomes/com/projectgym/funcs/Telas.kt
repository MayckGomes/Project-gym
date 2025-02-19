package mayckgomes.com.projectgym.funcs

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import mayckgomes.com.projectgym.Screens.Home.HomeScreen
import mayckgomes.com.projectgym.Screens.Home.ProfileScreen
import mayckgomes.com.projectgym.Screens.Home.TrainingNamesScreen
import mayckgomes.com.projectgym.database.training.Training

@Composable
fun Tela(tela:String, navController: NavController, listaTreinos: MutableStateFlow<List<Training>>){

    when (tela){
        "Home" -> HomeScreen()
        "Treino" -> TrainingNamesScreen(navController,listaTreinos)
        "Perfil" -> ProfileScreen()
    }

}