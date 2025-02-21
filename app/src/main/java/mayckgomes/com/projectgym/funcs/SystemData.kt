package mayckgomes.com.projectgym.funcs

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.flow.MutableStateFlow
import mayckgomes.com.projectgym.database.training.Training
import mayckgomes.com.projectgym.funcs.DatabasesFuncs.GetTreinos
import mayckgomes.com.projectgym.funcs.DatabasesFuncs.GetTreinosList
import mayckgomes.com.projectgym.funcs.UserData.GetLastTraining
import mayckgomes.com.projectgym.funcs.UserData.GetName
import mayckgomes.com.projectgym.ui.Components.StyledAlertDialog
import mayckgomes.com.projectgym.ui.Components.StyledNameDialog


object System{

    var LastTraining = ""

    var name = ""

    var TrainingList:MutableStateFlow<List<Training>> = MutableStateFlow(emptyList())


    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun loadData(context: Context) {

        val context = LocalContext.current

        LaunchedEffect(Unit){

            GetTreinos(context).collect{
                TrainingList.value = it
            }

        }

        name = GetName()

        LastTraining = GetLastTraining()
    }


}