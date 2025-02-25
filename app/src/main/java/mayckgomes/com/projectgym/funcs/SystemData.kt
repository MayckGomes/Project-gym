package mayckgomes.com.projectgym.funcs

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import mayckgomes.com.projectgym.database.training.Training
import mayckgomes.com.projectgym.funcs.DatabasesFuncs.GetTreinos
import mayckgomes.com.projectgym.funcs.DatabasesFuncs.GetTreinosList
import mayckgomes.com.projectgym.funcs.UserData.EditDaysTraining
import mayckgomes.com.projectgym.funcs.UserData.EditLastDayTraining
import mayckgomes.com.projectgym.funcs.UserData.EditLastTraining
import mayckgomes.com.projectgym.funcs.UserData.GetDaysTraining
import mayckgomes.com.projectgym.funcs.UserData.GetLastDayTraining
import mayckgomes.com.projectgym.funcs.UserData.GetLastTraining
import mayckgomes.com.projectgym.funcs.UserData.GetMediaTimeTraining
import mayckgomes.com.projectgym.funcs.UserData.GetName
import mayckgomes.com.projectgym.ui.Components.StyledAlertDialog
import mayckgomes.com.projectgym.ui.Components.StyledNameDialog
import java.time.LocalDate
import java.time.LocalTime
import java.util.Date


object System{

    var LastDayTraining = ""

    var LastTraining = ""

    var name = ""

    var DaysTraining = 0

    var MediaDaysTraining = 0

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

        LastDayTraining = GetLastDayTraining()

        DaysTraining = GetDaysTraining().toInt()

        MediaDaysTraining = GetMediaTimeTraining()

    }


}