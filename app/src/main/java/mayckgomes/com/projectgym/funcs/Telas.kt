package mayckgomes.com.projectgym.funcs

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.StructureKind
import mayckgomes.com.projectgym.DataTypes.Treino
import mayckgomes.com.projectgym.Screens.Home.HomeScreen
import mayckgomes.com.projectgym.Screens.Home.ProfileScreen
import mayckgomes.com.projectgym.Screens.Home.TrainingScreen
import mayckgomes.com.projectgym.funcs.UserData.GetName
import mayckgomes.com.projectgym.funcs.UserFuncs.GetTreinos

@OptIn(ExperimentalSerializationApi::class)
@Composable
fun Tela(tela:String,navController: NavController){

    when (tela){
        "Home" -> HomeScreen()
        "Treino" -> TrainingScreen(navController)
        "Perfil" -> ProfileScreen()
    }

}