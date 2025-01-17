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
import mayckgomes.com.projectgym.DataTypes.Treino
import mayckgomes.com.projectgym.Screens.Editing.EditingScreen
import mayckgomes.com.projectgym.Screens.Home.Menu
import mayckgomes.com.projectgym.Screens.Training.Exercicios
import mayckgomes.com.projectgym.ui.theme.ProjectGymTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjectGymTheme {

                Navegacao()

            }
        }
    }
}

@Serializable
object Menu


@Serializable
data class ShowExercicios(
    val id:String
)

@Serializable
data class EditOrAddTreino(
    val id:String
)

@Composable
fun Navegacao(){
    val navController = rememberNavController()

    NavHost(navController, startDestination = Menu, builder = {

        composable<Menu> { Menu(navController) }


        composable<ShowExercicios> {
            val args = it.toRoute<ShowExercicios>()
            Exercicios(navController, id = args.id) }

        composable<EditOrAddTreino> {
            val args = it.toRoute<EditOrAddTreino>()
            EditingScreen(navController, id = args.id)
        }
    })

}

