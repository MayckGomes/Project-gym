package mayckgomes.com.projectgym.funcs

import mayckgomes.com.projectgym.DataTypes.Exercicio
import mayckgomes.com.projectgym.DataTypes.Treino
import mayckgomes.com.projectgym.DataTypes.User

object UserData{
    fun ColetarDados(): User {

        val user = User(
            Nome = "Mayck",
            listaTreinos = listOf(
                Treino(
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
}