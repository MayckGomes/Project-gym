package mayckgomes.com.projectgym.DataTypes

import kotlinx.serialization.Serializable

@Serializable
data class Exercicio(
    val nome:String,
    val series:Int,
    val repeticoes:Int
)
