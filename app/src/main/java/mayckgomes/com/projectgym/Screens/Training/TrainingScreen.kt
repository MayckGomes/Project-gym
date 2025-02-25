package mayckgomes.com.projectgym.Screens.Training

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import mayckgomes.com.projectgym.FinalTraining
import mayckgomes.com.projectgym.database.exercices.Exercicies
import mayckgomes.com.projectgym.funcs.DatabasesFuncs.GetListExercicies
import mayckgomes.com.projectgym.funcs.DatabasesFuncs.GetTreinoById
import mayckgomes.com.projectgym.funcs.StartTimer
import mayckgomes.com.projectgym.funcs.StartTimerInReverse
import mayckgomes.com.projectgym.funcs.UserData.EditLastTraining
import mayckgomes.com.projectgym.funcs.SystemTimer
import mayckgomes.com.projectgym.ui.Components.StyledAlertDialog
import mayckgomes.com.projectgym.ui.Components.StyledText
import mayckgomes.com.projectgym.ui.theme.Black
import mayckgomes.com.projectgym.ui.theme.DarkGray
import mayckgomes.com.projectgym.ui.theme.ProjectGymTheme
import mayckgomes.com.projectgym.ui.theme.Yellow

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun TrainingScreen(navController: NavController,idTraining:Int){
    ProjectGymTheme {

        val context = LocalContext.current

        var listExercicies by rememberSaveable {
            mutableStateOf(emptyList<Exercicies>())
        }

        var isLoading by rememberSaveable {
            mutableStateOf(true)
        }

        var actualExercicies by rememberSaveable {
            mutableIntStateOf(0)
        }

        var titulo by rememberSaveable {
            mutableStateOf("Treino")
        }

        var nomeExercicio by rememberSaveable {
            mutableStateOf("")
        }

        var series by rememberSaveable {
            mutableIntStateOf(0)
        }

        var repeticoes by rememberSaveable {
            mutableIntStateOf(0)
        }

        var isClicked by rememberSaveable {
            mutableStateOf(false)
        }

        LaunchedEffect(Unit) {
            titulo = GetTreinoById(context,idTraining).name

            listExercicies = GetListExercicies(context,idTraining)

            SystemTimer.start()

            isLoading = false


        }

        if (isLoading){

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(color = Black)
                    .fillMaxSize()
            ) {

                CircularProgressIndicator()

            }

        } else {

            EditLastTraining(titulo)

            Scaffold(
                topBar ={

                    Box(
                        contentAlignment = Alignment.CenterStart,
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(92.dp)
                            .clip(RoundedCornerShape(0.dp, 0.dp, 12.dp, 12.dp))
                            .background(color = DarkGray)
                            .padding(5.dp, 20.dp, 20.dp, 20.dp)
                            .padding(top = 25.dp)
                    ){
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            IconButton(onClick = { isClicked = true}) {
                                Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = null)
                            }

                            StyledText(titulo, color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Bold)

                        }
                    }

                },

                bottomBar = {

                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 0.dp, 0.dp, 40.dp)
                    ) {

                        TextButton(onClick = {}) {
                            StyledText("Cancelar", color = Color.Red, fontWeight = FontWeight.Bold)
                        }

                        TextButton(onClick = {
                            if ((actualExercicies+1)==listExercicies.size){

                                SystemTimer.stop()

                                navController.navigate(FinalTraining(titulo))

                            } else {
                                actualExercicies++
                            }
                        }) {
                            StyledText("Avançar", color = Yellow, fontWeight = FontWeight.Bold)
                        }

                    }

                }

            ) {paddingValues ->

                LaunchedEffect(actualExercicies) {

                    nomeExercicio = listExercicies[actualExercicies].name
                    series = listExercicies[actualExercicies].series
                    repeticoes = listExercicies[actualExercicies].repeticoes

                }

                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .background(Black)
                ) {

                    Spacer(Modifier.size(15.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp, 0.dp)
                            .height(120.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(color = DarkGray)
                    ){

                        Column(
                            Modifier
                                .fillMaxSize()
                                .padding(10.dp)) {

                            StyledText(nomeExercicio, fontWeight = FontWeight.Bold, fontSize = 25.sp)

                            if (repeticoes != -1){

                                StyledText("Séries: $series")

                                Spacer(Modifier.size(10.dp))

                                StyledText("Repetições: $repeticoes")

                            } else {

                                StyledText("Minutos : ${series/60}")

                                Spacer(Modifier.size(10.dp))

                                StyledText("Segundos : ${series%60}")
                            }
                        }
                    }

                    if (repeticoes == -1){

                        StartTimerInReverse(series)

                    } else {

                        StartTimer()

                    }

                }
            }

            if (isClicked){

                StyledAlertDialog(
                    title = "Alerta",
                    text= "Deseja mesmo sair do treino?",
                    confirmButton = {

                        isClicked = false

                        SystemTimer.stop()

                        navController.navigate(FinalTraining(titulo))

                    },
                    onDismissRequest = {
                        isClicked = false
                    },
                    icon = Icons.Default.Warning
                )

            }

        }
    }
}