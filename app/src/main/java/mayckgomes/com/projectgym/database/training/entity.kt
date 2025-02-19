package mayckgomes.com.projectgym.database.training

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trainings")
data class Training(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
)