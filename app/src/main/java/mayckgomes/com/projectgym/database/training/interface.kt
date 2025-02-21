package mayckgomes.com.projectgym.database.training

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface trainingDAO {

    @Insert
    suspend fun insertTraining(training: Training):Long

    @Update
    suspend fun updateTraining(training: Training)

    @Delete
    suspend fun deleteTraining(training: Training)

    @Query("SELECT * FROM trainings")
    fun getAll(): Flow<List<Training>>

    @Query("SELECT * FROM trainings")
    suspend fun getAllList(): List<Training>

    @Query("SELECT * FROM trainings WHERE id = :id")
    suspend fun getTrainingById(id:Int):Training

}