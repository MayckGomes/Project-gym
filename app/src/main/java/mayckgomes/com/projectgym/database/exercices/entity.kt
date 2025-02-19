package mayckgomes.com.projectgym.database.exercices

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercices")
data class Exercicies(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val idTraining: Int,
    val name: String,
    val series: Int,
    val repeticoes: Int
)