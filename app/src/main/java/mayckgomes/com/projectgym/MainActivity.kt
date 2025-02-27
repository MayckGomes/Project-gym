package mayckgomes.com.projectgym

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import mayckgomes.com.projectgym.Screens.Editing.EditingScreen
import mayckgomes.com.projectgym.Screens.Home.Menu
import mayckgomes.com.projectgym.Screens.Training.FinalTrainingScreen
import mayckgomes.com.projectgym.Screens.Training.TrainingScreen
import mayckgomes.com.projectgym.funcs.System.LoadData
import mayckgomes.com.projectgym.ui.theme.ProjectGymTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjectGymTheme {

                LoadData(LocalContext.current)

                Navegacao()

            }
        }
    }
}

@Serializable
object Menu

@Serializable
data class EditOrAddTreino(
    val id:String = "nada"
)

@Serializable
data class Training(
    val idTraining: Int
)

@Serializable
data class FinalTraining(
    val nameTraining: String
)

@Composable
fun Navegacao(){
    val navController = rememberNavController()

    NavHost(navController, startDestination = Menu, builder = {

        composable<Menu> { Menu(navController) }

        composable<EditOrAddTreino> {
            val args = it.toRoute<EditOrAddTreino>()
            EditingScreen(navController, id = args.id)
        }

        composable<Training> {
            val args = it.toRoute<Training>()
            TrainingScreen(navController,idTraining = args.idTraining)
        }

        composable<FinalTraining> {
            val args = it.toRoute<FinalTraining>()
            FinalTrainingScreen(navController,args.nameTraining)
        }

    },
        enterTransition = { slideInHorizontally { it } },
        exitTransition = { slideOutHorizontally { -it } },
        popEnterTransition = { slideInHorizontally { -it } },
        popExitTransition = { slideOutHorizontally { it } }

    )
}

