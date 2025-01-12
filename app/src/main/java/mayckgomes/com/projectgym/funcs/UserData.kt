package mayckgomes.com.projectgym.funcs

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import mayckgomes.com.projectgym.DataTypes.Exercicio
import mayckgomes.com.projectgym.DataTypes.Treino
import mayckgomes.com.projectgym.DataTypes.User


object UserData{

    @Composable
    fun ColetarTreinos(): MutableMap<String, *>? {
        val treinosData = LocalContext.current.getSharedPreferences("Treinos",
            Context.MODE_PRIVATE
        )

        return treinosData.all
    }

    @Composable
    fun ColetarEditorTreinos(): SharedPreferences.Editor? {
        val treinosData = LocalContext.current.getSharedPreferences("Treinos",
            Context.MODE_PRIVATE
        ).edit()

        return treinosData
    }

    @Composable
    fun ColetarListaExercicios(){

        val ExerciciosData = LocalContext.current.getSharedPreferences("Exercicios",
            Context.MODE_PRIVATE
        )
    }


    fun ColetarDados(): User {

        val user = User(
            Nome = "Mayck",
            listaTreinos = listOf(
                Treino(
                    id="1",
                    Nome = "Treino A",
                    Lista = listOf(
                        Exercicio(
                            nome = "Esteira",
                            series = 15,
                            repeticoes = -1
                        ),
                        Exercicio(
                            nome = "Supino Inclinado",
                            series = 3,
                            repeticoes = 15
                        )
                    )
                ),
                Treino(
                    id="2",
                    Nome = "Treino B",
                    Lista = listOf(
                        Exercicio(
                            nome = "Esteira",
                            series = 15,
                            repeticoes = -1
                        ),
                        Exercicio(
                            nome = "Supino Inclinado",
                            series = 3,
                            repeticoes = 15
                        )
                    )
                ),
                Treino(
                    id="3",
                    Nome = "Treino C",
                    Lista = listOf(
                        Exercicio(
                            nome = "Esteira",
                            series = 15,
                            repeticoes = -1
                        ),
                        Exercicio(
                            nome = "Supino Inclinado",
                            series = 3,
                            repeticoes = 15
                        )
                    )
                )
            ),
            diasTreinados = 10,
            mediaDiaria = 10000
        )

        return user
    }

    @Composable
    fun BuscarTreinoPorId(id:String): Any? {

        val treinos = ColetarTreinos()

        var returnedTreino = ""

        for(treino in treinos!!){
            if (treino.key == id){
                returnedTreino = treino.value.toString()
            }
        }

        return returnedTreino
    }

    @Composable
    fun addTreinos(nome:String){

        val id = ColetarTreinos()!!.size

        val editor = ColetarEditorTreinos()

        editor?.putString("$id","$nome,[]")?.apply()

    }

    @Composable
    fun addExercicio(idTreino:String,exercicio: Exercicio){
        val treino = BuscarTreinoPorId(idTreino)

        val listaExercicios = treino.toString().toMutableList()

        val newExercicio = listOf(exercicio.nome,exercicio.series,exercicio.repeticoes)

    }

}