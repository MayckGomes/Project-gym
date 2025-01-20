package mayckgomes.com.projectgym.funcs

import android.content.Context
import android.widget.Toast

fun MakeMessage(context: Context,mensagem:String){

    Toast.makeText(context,mensagem,Toast.LENGTH_SHORT).show()

}