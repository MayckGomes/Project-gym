package mayckgomes.com.projectgym.DataTypes

data class User(
    var Nome:String,
    var listaTreinos:List<Treino>,
    var diasTreinados:Int,
    var mediaDiaria:Int
)
