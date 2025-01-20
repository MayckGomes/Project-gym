package mayckgomes.com.projectgym.funcs

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import mayckgomes.com.projectgym.DataTypes.Treino
import mayckgomes.com.projectgym.funcs.UserData.GetName
import mayckgomes.com.projectgym.funcs.UserFuncs.GetTreinos

object System{


    var name = ""

    var TrainingList:SnapshotStateList<Treino> = mutableStateListOf()

    @SuppressLint("UnrememberedMutableState")
    @Composable
    fun loadData(){

        name = GetName()

        TrainingList = mutableStateListOf<Treino>().apply {
            addAll(GetTreinos())
        }

    }

}