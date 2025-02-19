package mayckgomes.com.projectgym.funcs

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import mayckgomes.com.projectgym.database.training.Training
import mayckgomes.com.projectgym.funcs.DatabasesFuncs.GetTreinos
import mayckgomes.com.projectgym.funcs.UserData.GetName


object System{

    var name = ""

    var TrainingList:MutableStateFlow<List<Training>> = MutableStateFlow(emptyList())


    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun loadData(context: Context) {

        val context = LocalContext.current

        val scope = rememberCoroutineScope()

        scope.launch {
            GetTreinos(context).collect {
                TrainingList.value = it
            }
        }

        name = GetName()

    }


}