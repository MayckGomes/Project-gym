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

}