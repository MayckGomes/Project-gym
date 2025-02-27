package mayckgomes.com.projectgym.funcs

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.MutableStateFlow
import mayckgomes.com.projectgym.database.training.Training
import mayckgomes.com.projectgym.funcs.DatabasesFuncs.GetTreinos
import mayckgomes.com.projectgym.funcs.UserData.GetDaysTraining
import mayckgomes.com.projectgym.funcs.UserData.GetLastDayTraining
import mayckgomes.com.projectgym.funcs.UserData.GetLastTraining
import mayckgomes.com.projectgym.funcs.UserData.GetMediaTimeTraining
import mayckgomes.com.projectgym.funcs.UserData.GetName

object System{

    var LastDayTraining = ""

    var LastTraining = ""

    var name = ""

    var DaysTraining = 0

    var MediaDaysTraining = 0

    var TrainingList:MutableStateFlow<List<Training>> = MutableStateFlow(emptyList())


    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun LoadData(context: Context) {

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