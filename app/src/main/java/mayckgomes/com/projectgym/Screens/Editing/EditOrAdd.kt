package mayckgomes.com.projectgym.Screens.Editing

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import mayckgomes.com.projectgym.DataTypes.Exercicio
import mayckgomes.com.projectgym.Menu
import mayckgomes.com.projectgym.funcs.MakeMessage
import mayckgomes.com.projectgym.funcs.UserFuncs.AddTreinos
import mayckgomes.com.projectgym.funcs.UserFuncs.EditTreinos
import mayckgomes.com.projectgym.funcs.UserFuncs.GetExercicios
import mayckgomes.com.projectgym.funcs.UserFuncs.GetTreinosById
import mayckgomes.com.projectgym.ui.Components.StyledTextField
import mayckgomes.com.projectgym.ui.Components.StyledTextFieldNumber
import mayckgomes.com.projectgym.ui.theme.DarkGray
import mayckgomes.com.projectgym.ui.theme.Gray
import mayckgomes.com.projectgym.ui.theme.LightGray
import mayckgomes.com.projectgym.ui.theme.Yellow
import java.util.Collections.addAll

@SuppressLint("UnrememberedMutableState")
@Composable
fun EditingScreen(navController:NavController, id:String){

    var listExercicio: SnapshotStateList<Exercicio> = mutableStateListOf()

    var idTreino by rememberSaveable {
        mutableStateOf("")
    }

    var nomeTreino by rememberSaveable {
        mutableStateOf("")
    }

    var titulo by rememberSaveable {
        mutableStateOf("ADICIONAR TREINO")
    }


    var nomeExercicio by rememberSaveable {
        mutableStateOf("")
    }

    var repeticoes by rememberSaveable {
        mutableStateOf("")
    }

    var series by rememberSaveable {
        mutableStateOf("")
    }

    var tempo by rememberSaveable {
        mutableStateOf("")
    }

    var isTimer by rememberSaveable {
        mutableStateOf(false)
    }

    var Clicked by rememberSaveable {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    if (id!="nada"){
        val treino = GetTreinosById(id)

        Log.d("treinos", "ta retornando treino: $treino")

        nomeTreino = treino.nome

        idTreino = treino.id

        titulo = "EDITANDO: ${treino.nome}"

        val exercicios = GetExercicios(treino.idListaTreinos)

        Log.d("exercicios", "val exercicios: $exercicios")

        listExercicio = mutableStateListOf<Exercicio>().apply{
            addAll(exercicios)
        }
    }


    Scaffold(
        topBar = {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(90.dp)
                    .clip(RoundedCornerShape(0.dp, 0.dp, 12.dp, 12.dp))
                    .background(color = LightGray)
                    .padding(5.dp, 20.dp, 20.dp, 20.dp)
                    .padding(top = 25.dp)
            ){
                Row {

                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = null)
                    }

                    Text(titulo, color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Bold)

                }
            }
        },
        bottomBar = {
            Button(
                modifier = Modifier
                .padding(10.dp)
                .padding(0.dp, 0.dp, 0.dp, 35.dp)
                .fillMaxWidth(), onClick = {Clicked = true},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Yellow,
                    contentColor = Color.Black
                )) {
                Text("Salvar")
            }

                // se for para editar treino
            if (Clicked && id!="nada"){

                navController.popBackStack()
                EditTreinos(idTreino, nome = nomeTreino,listExercicio.toList())
                listExercicio.clear()
                Clicked = false


                // se for para add treino
            } else if (Clicked && id=="nada"){

                navController.popBackStack()
                AddTreinos(nomeTreino,listExercicio.toList())
                listExercicio.clear()
                Clicked = false

            }

        }

    ) { innerpadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Gray)
                .padding(innerpadding)
                .padding(5.dp)
        ) {

            Spacer(Modifier.size(20.dp))

            Text("Treino: ", color = Color.White, fontSize = 25.sp)

            Spacer(Modifier.size(10.dp))

            StyledTextField(
                value = nomeTreino,
                onValueChange = {nomeTreino = it},
                label = { Text("Nome do Treino", color = Yellow) }
            )

            Spacer(Modifier.size(10.dp))

            HorizontalDivider()

            Spacer(Modifier.size(10.dp))

            Text("Exercicios: ", color = Color.White, fontSize = 25.sp)

            Spacer(Modifier.size(10.dp))

            StyledTextField(
                value = nomeExercicio,
                onValueChange = {nomeExercicio = it},
                label = { Text("Nome Exercicio", color = Yellow) }
            )

            Spacer(Modifier.size(20.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Checkbox(
                    checked = isTimer,
                    onCheckedChange = {isTimer = it},
                    colors = CheckboxDefaults.colors(
                        checkedColor = Yellow
                    ))


                Text("É por tempo?", color = Color.White, modifier = Modifier.clickable { isTimer = !isTimer })
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                StyledTextFieldNumber(
                    value = series,
                    onValueChange = {series = it},
                    label = { Text("Series", color = Yellow) },
                    enabled = !isTimer,
                )

                Spacer(Modifier.size(10.dp))

                StyledTextFieldNumber(
                    value = repeticoes,
                    onValueChange = {repeticoes = it},
                    label = { Text("Repeticoes", color = Yellow) },
                    enabled = !isTimer,

                )

                Spacer(Modifier.size(10.dp))

                StyledTextFieldNumber(
                    value = tempo,
                    onValueChange = {tempo = it},
                    label = { Text("Tempo", color = Yellow) },
                    enabled = isTimer,
                )

                Spacer(Modifier.size(10.dp))

                Button(
                    onClick = {

                        if (nomeExercicio.isNotEmpty()){

                            when{
                                isTimer && tempo.toIntOrNull() != null -> listExercicio.add(
                                    Exercicio(nome = nomeExercicio, series = tempo.toInt(), repeticoes = -1))


                                !isTimer && series.toIntOrNull() != null && repeticoes.toIntOrNull() != null -> listExercicio.add(
                                    Exercicio(
                                        nome = nomeExercicio,
                                        series = series.toInt(),
                                        repeticoes = repeticoes.toInt()
                                    )
                                )

                                else -> MakeMessage(context,"Preencha os campos Corretamente")

                            }

                        } else {
                            MakeMessage(context,"Coloque um nome ao exercicio")
                        }

                        series = ""
                        repeticoes = ""
                        tempo = ""

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Yellow,
                        contentColor = Color.Black
                    )
                ) {
                    Text("Salvar")
                }

            }

            Spacer(Modifier.size(20.dp))

            Text("Lista Exercicios: ", color = Color.White, fontSize = 25.sp)

            Spacer(Modifier.size(10.dp))

            if(listExercicio.isEmpty()){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(12.dp))
                        .background(color = LightGray),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Ainda não há Exercicios nesse treino!!",color = Color.White)
                }
            } else {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(12.dp))
                        .background(color = LightGray)
                ) {
                    items(listExercicio){ exercicio ->

                        Box(modifier = Modifier
                            .fillMaxWidth(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(color = DarkGray)
                            .padding(10.dp)){
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth(1f)
                            ) {
                                Column {
                                    Text(exercicio.nome, color = Color.White, fontSize = 25.sp)

                                    Spacer(Modifier.size(5.dp))
                                    if (exercicio.repeticoes == -1){

                                        Text("Tempo: ${exercicio.series} minutos",color = Color.White)

                                    } else {

                                        Text("Series: ${exercicio.series}",color = Color.White)

                                        Spacer(Modifier.size(5.dp))

                                        Text("Repetições: ${exercicio.repeticoes}",color = Color.White)

                                    }

                                }

                                IconButton(onClick = {listExercicio.remove(Exercicio(nome = exercicio.nome, series = exercicio.series, repeticoes = exercicio.repeticoes))}) {
                                    Icon(Icons.Default.Delete,contentDescription = null, tint = Color.Red)
                                }
                            }

                        }

                        Spacer(Modifier.size(10.dp))

                    }

                }

            }

        }
    }

}

@Preview
@Composable
fun EditingScreenPreview(){
    EditingScreen(navController = rememberNavController(),id="nada")
}