package mayckgomes.com.projectgym

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import mayckgomes.com.projectgym.Screens.Editing.EditingScreen
import mayckgomes.com.projectgym.Screens.Home.Menu
import mayckgomes.com.projectgym.funcs.System.loadData
import mayckgomes.com.projectgym.ui.theme.ProjectGymTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjectGymTheme {

                loadData()

                Navegacao()

            }
        }
    }
}

@Serializable
object Menu

@Serializable
data class EditOrAddTreino(
    val id:String
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
    })

}

