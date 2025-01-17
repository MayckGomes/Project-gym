package mayckgomes.com.projectgym.DataTypes

import kotlinx.serialization.Serializable

@Serializable
data class Treino(
    val id:String,
    val nome:String,
    val idListaTreinos:String
)