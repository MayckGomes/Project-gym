package mayckgomes.com.projectgym.funcs

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


object UserData{

    @Composable
    fun GetName(): String{
        val UserData = LocalContext.current.getSharedPreferences("User",Context.MODE_PRIVATE)
        val nome = UserData.getString("Nome","Usuario")

        Log.d("Nome", "GetName: $nome")

        return nome!!
    }


    @Composable
    fun EditName(name:String){
        val UserData = LocalContext.current.getSharedPreferences("User",Context.MODE_PRIVATE)

        System.name = name

        UserData.edit().putString("Nome",name).apply()
    }


    @Composable
    fun GetLastTraining(): String {

        val userData = LocalContext.current.getSharedPreferences("lastTraining", Context.MODE_PRIVATE)

        return userData.getString("training","Nenhum")!!
    }


    @Composable
    fun EditLastTraining(training: String){

        val userData = LocalContext.current.getSharedPreferences("lastTraining", Context.MODE_PRIVATE)

        userData.edit().putString("training",training).apply()

        System.LastTraining = training

    }


    @Composable
    fun GetLastDayTraining(): String {

        val userData = LocalContext.current.getSharedPreferences("lastDayTraining", Context.MODE_PRIVATE)

        return userData.getString("date","Você ainda não treinou")!!

    }


    @Composable
    fun EditLastDayTraining(day: String){

        val userData = LocalContext.current.getSharedPreferences("lastDayTraining", Context.MODE_PRIVATE)

        userData.edit().putString("date",day).apply()
    }


    @Composable
    fun GetDaysTraining(): String {

        val userData = LocalContext.current.getSharedPreferences("days", Context.MODE_PRIVATE)

        return userData.getString("days","0")!!

    }


    @Composable
    fun EditDaysTraining(days: Int) {

        val userData = LocalContext.current.getSharedPreferences("days", Context.MODE_PRIVATE)

        userData.edit().putString("days", days.toString()).apply()

    }


    @Composable
    fun GetMediaTimeTraining(): Int {

        val userData = LocalContext.current.getSharedPreferences("mediaTime", Context.MODE_PRIVATE)

        val time = userData.getInt("time",0)

        val trainings = userData.getInt("numberOfTrainings",0)

        return if (time == 0 && trainings == 0) 0 else time/trainings

    }


    @Composable
    fun AddMediaTimeTraining(time: Int){

        val userData = LocalContext.current.getSharedPreferences("mediaTime", Context.MODE_PRIVATE)

        val timeActual = userData.getInt("time",0)

        val trainings = userData.getInt("numberOfTrainings",0)

        userData.edit().putInt("time",timeActual+time).apply()

        userData.edit().putInt("numberOfTrainings",trainings+1).apply()

    }
}