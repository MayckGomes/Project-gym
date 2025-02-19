package mayckgomes.com.projectgym.database.exercices

import androidx.compose.ui.util.fastForEach
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface exerciciesDAO {

    @Insert
    suspend fun AddExercices(exercicies: Exercicies)

    @Update
    suspend fun UpdateExercice(exercicies: Exercicies)

    @Query("DELETE FROM exercices WHERE id = :id")
    suspend fun DeleteExercice(id: Int)

    @Query("SELECT * FROM exercices WHERE idTraining = :id")
    fun GetAllById(id: Int): Flow<List<Exercicies>>

    @Query("DELETE FROM exercices WHERE idTraining = :id")
    suspend fun deleteExerciciesByTrainingId(id: Int)

    @Query("SELECT * FROM exercices WHERE idTraining = :id")
    suspend fun GetListOfExercicies(id: Int): List<Exercicies>
}