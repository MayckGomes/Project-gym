package mayckgomes.com.projectgym.Screens.Editing

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import mayckgomes.com.projectgym.database.exercices.Exercicies
import mayckgomes.com.projectgym.funcs.DatabasesFuncs.AddExercicies
import mayckgomes.com.projectgym.funcs.DatabasesFuncs.AddTreinos
import mayckgomes.com.projectgym.funcs.DatabasesFuncs.DeleteExercicios
import mayckgomes.com.projectgym.funcs.DatabasesFuncs.DeleteExerciciosByIdTreino
import mayckgomes.com.projectgym.funcs.DatabasesFuncs.DeleteTreinos
import mayckgomes.com.projectgym.funcs.DatabasesFuncs.EditTreinos
import mayckgomes.com.projectgym.funcs.DatabasesFuncs.GetListExercicies
import mayckgomes.com.projectgym.funcs.DatabasesFuncs.GetTreinoById
import mayckgomes.com.projectgym.funcs.MakeMessage
import mayckgomes.com.projectgym.funcs.title
import mayckgomes.com.projectgym.ui.Components.StyledAlertDialog
import mayckgomes.com.projectgym.ui.Components.StyledText
import mayckgomes.com.projectgym.ui.Components.StyledTextField
import mayckgomes.com.projectgym.ui.Components.StyledTextFieldNumber
import mayckgomes.com.projectgym.ui.theme.Black
import mayckgomes.com.projectgym.ui.theme.DarkGray
import mayckgomes.com.projectgym.ui.theme.White
import mayckgomes.com.projectgym.ui.theme.Yellow

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun EditingScreen(navController: NavController, id:String) {

    var idTreino by rememberSaveable {
        mutableStateOf(id)
    }

    val scope = rememberCoroutineScope()

    val context = LocalContext.current

    var titulo by rememberSaveable {
        mutableStateOf("Criando Treino")
    }

    var listaExercicios by rememberSaveable {
        mutableStateOf(emptyList<Exercicies>())
    }

    var nomeTreino by rememberSaveable {
        mutableStateOf("")
    }

    var nomeExercicio by rememberSaveable {
        mutableStateOf("")
    }

    var minutos by rememberSaveable {
        mutableStateOf("")
    }

    var segundos by rememberSaveable {
        mutableStateOf("")
    }

    var series by rememberSaveable {
        mutableStateOf("")
    }

    var repeticoes by rememberSaveable {
        mutableStateOf("")
    }

    var isTimer by rememberSaveable {
        mutableStateOf(false)
    }

    var backAlert by rememberSaveable {
        mutableStateOf(false)
    }

    var deleteAlert by rememberSaveable {
        mutableStateOf(false)
    }

    var deleteExerciceAlert by rememberSaveable {
        mutableStateOf(false)
    }

    var idDeleteExercicie by rememberSaveable {
        mutableStateOf("")
    }

    if (id != "nada"){

        scope.launch {

            val treino = GetTreinoById(context, idTreino.toInt())

            nomeTreino = treino.name

            titulo = "Editando: $nomeTreino"

            listaExercicios = GetListExercicies(context, idTreino.toInt())

        }

    } else {

        LaunchedEffect(Unit){

            idTreino = AddTreinos(context, "criando").toString()

            listaExercicios = GetListExercicies(context, idTreino.toInt())

        }
    }
    Scaffold (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Black),

        topBar = {

            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(92.dp)
                    .clip(RoundedCornerShape(0.dp, 0.dp, 12.dp, 12.dp))
                    .background(color = DarkGray)
                    .padding(5.dp, 20.dp, 20.dp, 20.dp)
                    .padding(top = 25.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(onClick = { backAlert = true }) {
                        Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = null, tint = MaterialTheme.colorScheme.surface)
                    }

                    StyledText(
                        titulo,
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )

                }
            }
        },

        bottomBar = {

           if (id == "nada"){

               Button(
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(10.dp, 10.dp, 10.dp, 45.dp),
                   colors = ButtonDefaults.buttonColors(
                       containerColor = Yellow,
                       contentColor = Black
                   ),
                   onClick = {

                       scope.launch {

                           EditTreinos(context, idTreino.toInt(),nomeTreino.title())
                       }

                       MakeMessage(context,"Treino Criado com Sucesso!")

                       navController.popBackStack()

                   }
               ) {

                   StyledText("Criar Treino")

               }

           } else {

               Row(
                   horizontalArrangement = Arrangement.SpaceEvenly,
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(10.dp, 5.dp, 10.dp, 50.dp),
               ) {

                   Button(
                       modifier = Modifier.size(150.dp,50.dp),
                       onClick = {deleteAlert = true},
                       colors = ButtonDefaults.buttonColors(containerColor = Color.Red,
                           contentColor = Black)) {

                       Text("Excluir")

                   }

                   Button(
                       modifier = Modifier.size(150.dp,50.dp),
                       onClick = {

                           scope.launch {

                               EditTreinos(context,idTreino.toInt(), nomeTreino.title())
                               navController.popBackStack()

                           }

                           MakeMessage(context,"Treino editado com sucesso")

                       },
                       colors = ButtonDefaults.buttonColors(containerColor = Yellow,
                           contentColor = Black)) {

                       Text("Editar")

                   }

               }

           }

        }

    ){paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Black)
                .padding(paddingValues)
                .padding(10.dp)
                .verticalScroll(rememberScrollState())
        ) {

            StyledText("Treino", fontSize = 25.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.surface)

            Spacer(Modifier.size(10.dp))

            StyledTextField(
                value = nomeTreino,
                onValueChange = {nomeTreino = it},
                label = { StyledText("Nome do Treino", color = White) }
            )

            Spacer(Modifier.size(15.dp))

            StyledText("Exercicios", fontWeight = FontWeight.Bold, fontSize = 25.sp, color = MaterialTheme.colorScheme.surface)

            Spacer(Modifier.size(10.dp))

            StyledTextField(
                value = nomeExercicio,
                onValueChange = {nomeExercicio = it},
                label = { StyledText("Nome do Exercicio", color = White) }
            )

            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){

                Checkbox(
                    checked = isTimer,
                    onCheckedChange = {isTimer = !isTimer},
                    colors = CheckboxDefaults.colors(checkedColor = Yellow)
                )

                StyledText("É por tempo?", color = White, modifier = Modifier.clickable { isTimer = !isTimer })

            }

            StyledTextFieldNumber(

                value = series,
                onValueChange = {series = it},
                label = { StyledText("Séries", color = White) },
                enabled = !isTimer

            )

            Spacer(Modifier.size(10.dp))

            StyledTextFieldNumber(

                value = repeticoes,
                onValueChange = {repeticoes = it},
                label = { StyledText("Repetições", color = White) },
                enabled = !isTimer

            )

            Spacer(Modifier.size(10.dp))

            StyledTextFieldNumber(

                value = minutos,
                onValueChange = {minutos = it},
                label = { StyledText("Minutos", color = White) },
                enabled = isTimer

            )

            Spacer(Modifier.size(10.dp))

            StyledTextFieldNumber(

                value = segundos,
                onValueChange = {segundos = it},
                label = { StyledText("Segundos", color = White) },
                enabled = isTimer

            )

            Spacer(Modifier.size(20.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {

                    when{

                        isTimer && nomeExercicio.isNotEmpty() -> {

                            if (minutos == "") {minutos = "0"}
                            if (segundos == ""){segundos = "0"}

                            if (minutos.isDigitsOnly() && segundos.isDigitsOnly()){

                                scope.launch{

                                    AddExercicies(context, Exercicies(
                                        idTraining = idTreino.toInt(),
                                        name = nomeExercicio.title(),
                                        series = (minutos.toInt()*60)+segundos.toInt(),
                                        repeticoes = -1)
                                    )

                                    listaExercicios = GetListExercicies(context, idTreino.toInt())

                                    nomeExercicio = ""


                                    minutos = ""
                                    segundos = ""

                                }

                            } else {

                                MakeMessage(context,"Apenas aceitos números")

                            }
                        }

                        !isTimer && nomeExercicio.isNotEmpty() -> {

                            if (series.isDigitsOnly() && repeticoes.isDigitsOnly()){

                                scope.launch {

                                    AddExercicies(context,Exercicies(
                                        idTraining = idTreino.toInt(),
                                        name = nomeExercicio.title(),
                                        series = if (series == "") 0 else series.toInt(),
                                        repeticoes = if (repeticoes == "") 0 else repeticoes.toInt()
                                    ))

                                    listaExercicios = GetListExercicies(context, idTreino.toInt())

                                    nomeExercicio = ""

                                    series = ""
                                    repeticoes = ""

                                }

                            } else {

                                MakeMessage(context,"Apenas aceitos números")

                            }
                        }

                        else -> MakeMessage(context, "Preencha os Campos Corretamente")

                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Yellow, contentColor = Black)) {
                StyledText("Adicionar Exercicio", fontWeight = FontWeight.Bold)
            }

            Spacer(Modifier.size(10.dp))

            StyledText("Lista de Exercicios", fontSize = 25.sp, fontWeight = FontWeight.Bold)

            Spacer(Modifier.size(10.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(500.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = DarkGray)

            ) {

               if (listaExercicios.isNotEmpty()){

                   LazyColumn(
                       modifier = Modifier
                           .fillMaxSize()
                           .padding(6.dp)
                   ) {

                       items(listaExercicios){exercicios ->

                           Row(
                               horizontalArrangement = Arrangement.SpaceBetween,
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .clip(RoundedCornerShape(12.dp))
                                   .background(color = Black)
                                   .padding(15.dp)
                           ) {
                               Column(
                                   modifier = Modifier
                                       .fillMaxWidth(0.7f)
                               ) {
                                   StyledText(exercicios.name, fontWeight = FontWeight.Bold, fontSize = 25.sp)

                                   Spacer(Modifier.size(5.dp))

                                   if (exercicios.repeticoes == -1){

                                       StyledText("${exercicios.series/60} minutos, ${exercicios.series%60} segundos")

                                   } else {

                                       StyledText("Séries: ${exercicios.series}")

                                       Spacer(Modifier.size(5.dp))

                                       StyledText("Repetições: ${exercicios.repeticoes}")

                                   }

                               }

                               IconButton(onClick = { deleteExerciceAlert = true
                               idDeleteExercicie = exercicios.id.toString()}) {
                                   Icon(Icons.Default.Delete,contentDescription = null, tint = Color.Red)
                               }

                           }

                           Spacer(Modifier.size(10.dp))

                       }

                   }

               } else {

                   Column(
                       horizontalAlignment = Alignment.CenterHorizontally,
                       verticalArrangement = Arrangement.Center
                   ) {

                       StyledText("Ainda não há exercicios nesse treino", fontWeight = FontWeight.Bold)

                   }

               }

            }

        }

    }

    if (backAlert && id == "nada" ){

        StyledAlertDialog(
            title = "Alerta",
            text = "Deseja mesmo não criar o treino?",
            confirmButton = {
                scope.launch {
                    backAlert = false

                    DeleteTreinos(context, idTreino.toInt())

                    navController.popBackStack()
                }
            },
            onDismissRequest = {backAlert = false},
            icon = Icons.Default.Warning

        )

    } else if (backAlert && id != "nada" ){

        StyledAlertDialog(
            title = "Alerta",
            text = "Deseja mesmo não editar mais o treino?",
            confirmButton = {

                backAlert = false

                navController.popBackStack()
            },
            onDismissRequest = {backAlert = false},
            icon = Icons.Default.Warning

        )

    }

    if (deleteAlert){
        StyledAlertDialog(
            title = "Alerta",
            text = "Deseja mesmo excluir o treino?",
            confirmButton = {
                navController.popBackStack()

                scope.launch {
                    deleteAlert = false

                    DeleteTreinos(context,idTreino.toInt())

                    DeleteExerciciosByIdTreino(context, idTreino.toInt())
                }

                MakeMessage(context,"Treino excluido com sucesso")
            },
            onDismissRequest = {deleteAlert = false},
            icon = Icons.Default.Warning
        )
    }

    if (deleteExerciceAlert){

        StyledAlertDialog(
            title = "Alerta",
            text = "Deseja mesmo apagar esse exercicio?",
            onDismissRequest = { deleteExerciceAlert = false},
            confirmButton = {

                scope.launch {
                    deleteExerciceAlert = false

                    DeleteExercicios(context, idDeleteExercicie.toInt())
                    idDeleteExercicie = ""

                    listaExercicios = GetListExercicies(context, idTreino.toInt())
                }

            },
            icon = Icons.Default.Warning
        )

    }

}

@Preview(showSystemUi = true)
@Composable
fun EditingScreenPreview(){
    EditingScreen(navController = rememberNavController(),"0")
}