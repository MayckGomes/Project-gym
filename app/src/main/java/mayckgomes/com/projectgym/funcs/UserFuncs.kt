package mayckgomes.com.projectgym.funcs

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import mayckgomes.com.projectgym.DataTypes.Exercicio
import mayckgomes.com.projectgym.DataTypes.Treino
import mayckgomes.com.projectgym.funcs.System.TrainingList

object UserFuncs {

    @Composable
    private fun GetTreinosData(): SharedPreferences {

        val treinosData = LocalContext.current.getSharedPreferences("Training",Context.MODE_PRIVATE)

        return treinosData!!
    }


    @Composable
    private fun GetExerciciosData(): SharedPreferences {

        val exerciciosData = LocalContext.current.getSharedPreferences("Exercicios",Context.MODE_PRIVATE)

        return exerciciosData!!
    }


    @Composable
    fun GetTreinos(): List<Treino> {
        val treinosData = GetTreinosData()
        val treinosMap = treinosData.all

        var lista:MutableList<Treino> = mutableListOf()

        if (treinosMap.isNotEmpty()){

            val treinoslist = treinosMap.values.toMutableList()


            for (treinos in treinoslist){

                val id = treinos.toString().split(";")[0]
                val nome = treinos.toString().split(";")[1]

                lista.add(
                    Treino(
                        id = id,
                        nome = nome,
                        idListaTreinos = id
                    )
                )
            }

        }

        return lista.toList()
    }


    // Refeito ok
    @Composable
    fun GetTreinosById(idTreino: String): Treino {

        val treino = TrainingList[idTreino.toInt()]

        return Treino(treino.id,treino.nome,treino.idListaTreinos)
    }


    @Composable
    fun GetExercicios(id:String): List<Exercicio> {

        val exerciciosData = GetExerciciosData()

        var lista:MutableList<Exercicio> = mutableListOf()

        if (exerciciosData.getString(id,"")!!.toString() != ""){

            val exerciciosString = exerciciosData.getString(id,"")!!.toString().split(";")

            for (exercicios in exerciciosString){

                Log.d("exercicios", "GetExercicios: $exercicios")

                if (exercicios.isNotEmpty()){
                    val exercicio = exercicios.split(",")

                    val nome = exercicio[0]
                    val series = exercicio[1].toInt()
                    val repeticoes = exercicio[2].toInt()

                    lista.add(Exercicio(nome,series,repeticoes))
                }

            }

        }

        return lista.toList()

    }


    //Refeita ok
    @Composable
    fun AddTreinos(nome:String, listaExercicio: List<Exercicio>){

        val id = TrainingList.size

        TrainingList.add(Treino(id=id.toString(),nome=nome, idListaTreinos=id.toString()))
        EditExercicios(id.toString(),listaExercicio)
        SaveTreinos(TrainingList)
    }

    //Refeita ok
    @Composable
    fun EditTreinos(idTreino:String,nome: String, listaExercicio: List<Exercicio>){

        TrainingList[idTreino.toInt()] = Treino(idTreino, nome = nome, idListaTreinos = idTreino)

        EditExercicios(idTreino,listaExercicio)
        SaveTreinos(TrainingList)
    }


    @Composable
    fun EditExercicios(id:String, exercicios: List<Exercicio>){

        val exerciciosData = GetExerciciosData()

        var listaString = ""

        for (exercicio in exercicios){

            listaString += "${exercicio.nome},${exercicio.series},${exercicio.repeticoes};"

        }

        exerciciosData.edit().putString(id,listaString).apply()

    }


    @Composable
    fun SaveTreinos(listaTreinos:List<Treino>){

        val treinosData = GetTreinosData()

        treinosData.edit().clear().apply()

        for (treino in listaTreinos){

            treinosData.edit().putString("${treino.id}","${treino.id};${treino.nome}").apply()

        }

    }
}