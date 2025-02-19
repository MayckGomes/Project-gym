package mayckgomes.com.projectgym.funcs

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.util.fastForEach
import androidx.room.Room
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import mayckgomes.com.projectgym.DataTypes.Exercicio
import mayckgomes.com.projectgym.database.exercices.Exercicies
import mayckgomes.com.projectgym.database.exercices.exercicesDatabase
import mayckgomes.com.projectgym.database.exercices.exerciciesDAO
import mayckgomes.com.projectgym.database.training.Training
import mayckgomes.com.projectgym.database.training.trainingDAO
import mayckgomes.com.projectgym.database.training.trainingDatabase

object DatabasesFuncs {

    private fun GetTreinosDB(context: Context): trainingDAO {

        val trainingdb = Room.databaseBuilder(
            context = context,
            trainingDatabase::class.java,
            "trainings"
        ).build()

        val trainingActions = trainingdb.trainingDb()

        return trainingActions

    }


    private fun GetExerciciosDB(context: Context): exerciciesDAO {

        val exercicesdb = Room.databaseBuilder(
            context = context,
            exercicesDatabase::class.java,
            "exercices"
        ).build()

        val exercicesActions = exercicesdb.exercicesdb()

        return exercicesActions
    }


    fun GetTreinos(context: Context): Flow<List<Training>> {
        val db = GetTreinosDB(context)

        return db.getAll()
    }


    suspend fun GetTreinoById(context: Context,idTreino: Int):Training {

        val db = GetTreinosDB(context)

        var training = db.getTrainingById(idTreino)

        return training
    }


    fun GetExercicios(context: Context, id:Int): Flow<List<Exercicies>> {

        val db = GetExerciciosDB(context)

        return db.GetAllById(id)

    }

    suspend fun GetListExercicies(context: Context,id: Int):List<Exercicies>{

        val db = GetExerciciosDB(context)

        return db.GetListOfExercicies(id)

    }

    suspend fun AddTreinos(context: Context, nome:String):Long {

        val trainingDB = GetTreinosDB(context)

        val id = trainingDB.insertTraining(Training(name = nome))

        return id
    }


    suspend fun AddExercicies(context: Context, exercicies: Exercicies){

        val db = GetExerciciosDB(context)

        db.AddExercices(exercicies)

    }

    //Refeita ok
    suspend fun EditTreinos(context: Context, id: Int, nome: String){

        val db = GetTreinosDB(context)

        db.updateTraining(Training(id,nome))

    }


    suspend fun EditExercicios(context: Context, exercicies: Exercicies){

        val db = GetExerciciosDB(context)

        db.UpdateExercice(exercicies)

    }

    suspend fun DeleteTreinos(context: Context,id: Int){

        val db = GetTreinosDB(context)

        db.deleteTraining(GetTreinoById(context,id))

    }

    suspend fun DeleteExercicios(context: Context, id: Int){

        val db = GetExerciciosDB(context)

        db.DeleteExercice(id)
    }

    suspend fun DeleteExerciciosByIdTreino(context: Context,idTreino: Int){

        val db = GetExerciciosDB(context)

        db.deleteExerciciesByTrainingId(idTreino)

    }
}