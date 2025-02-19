package mayckgomes.com.projectgym.database.exercices

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Exercicies::class], version = 1)
abstract class exercicesDatabase:RoomDatabase(){

    abstract fun exercicesdb():exerciciesDAO

}