package mayckgomes.com.projectgym

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import mayckgomes.com.projectgym.DataTypes.Exercicio
import mayckgomes.com.projectgym.DataTypes.User
import mayckgomes.com.projectgym.Screens.Home.Menu
import mayckgomes.com.projectgym.Screens.Training.Exercicios
import mayckgomes.com.projectgym.funcs.UserData
import mayckgomes.com.projectgym.ui.theme.ProjectGymTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjectGymTheme {

                val Usuario = UserData.ColetarDados()
                Navegacao(user = Usuario)

            }
        }
    }
}

@Serializable
object Menu

//@Serializable
//data class EditTreino(
//    val lista:List<Exercicio>
//)

@Serializable
object EditTreino

@Serializable
object EditExercicio

@Composable
fun Navegacao(user:User){
    val navController = rememberNavController()

    NavHost(navController, startDestination = Menu, builder = {

        composable<Menu> { Menu(user,navController) }
        composable<EditTreino> {

            Exercicios(navController) }
    })

}

