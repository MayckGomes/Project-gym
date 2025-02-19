package mayckgomes.com.projectgym.database.training

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Training::class], version = 1)
abstract class trainingDatabase: RoomDatabase() {

    abstract fun trainingDb(): trainingDAO

}