package mayckgomes.com.projectgym.Screens.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import mayckgomes.com.projectgym.EditOrAddTreino
import mayckgomes.com.projectgym.Training
import mayckgomes.com.projectgym.funcs.DatabasesFuncs.GetListExercicies
import mayckgomes.com.projectgym.funcs.MakeMessage
import mayckgomes.com.projectgym.funcs.System.DaysTraining
import mayckgomes.com.projectgym.funcs.System.LastDayTraining
import mayckgomes.com.projectgym.funcs.UserData.EditDaysTraining
import mayckgomes.com.projectgym.funcs.UserData.EditLastDayTraining
import mayckgomes.com.projectgym.funcs.title
import mayckgomes.com.projectgym.ui.Components.StyledText
import mayckgomes.com.projectgym.ui.theme.Black
import mayckgomes.com.projectgym.ui.theme.DarkGray
import mayckgomes.com.projectgym.ui.theme.Gray
import mayckgomes.com.projectgym.ui.theme.White
import mayckgomes.com.projectgym.ui.theme.Yellow
import java.time.LocalDate

@Composable
fun TrainingNamesScreen(navController: NavController, lista: MutableStateFlow<List<mayckgomes.com.projectgym.database.training.Training>>){

    val scope = rememberCoroutineScope()

    val context = LocalContext.current

    val listaTreinos by lista.collectAsState()

    var startTraining by rememberSaveable {
        mutableStateOf(false)
    }

    Scaffold(
       topBar = {
           Box(
               contentAlignment = Alignment.CenterStart,
               modifier = Modifier
                   .fillMaxWidth()
                   .size(70.dp)
                   .clip(RoundedCornerShape(0.dp,0.dp,12.dp,12.dp))
                   .background(color = DarkGray)
                   .padding(20.dp)
           ){
               StyledText("TREINOS", color = White, fontSize = 25.sp, fontWeight = FontWeight.Bold)
           }
       },
       floatingActionButton = {
           FloatingActionButton(
               contentColor = Color.Black,
               containerColor = Yellow,
               onClick = { navController.navigate(EditOrAddTreino())}
               ) {
                Icon(Icons.Default.Add,contentDescription = null)
           }
       }
   )
    { innerpadding ->
       if (listaTreinos.isNotEmpty()){

           LazyColumn(
               modifier = Modifier
                   .fillMaxSize()
                   .background(color = MaterialTheme.colorScheme.primary)
                   .padding(innerpadding)
                   .padding(10.dp)
           ) {
               items(listaTreinos){ treino ->

                   Row(
                       verticalAlignment = Alignment.CenterVertically,
                       horizontalArrangement = Arrangement.SpaceBetween,
                       modifier = Modifier
                           .fillMaxWidth(1f)
                           .clip(RoundedCornerShape(12.dp))
                           .background(MaterialTheme.colorScheme.secondary)
                           .padding(10.dp)
                   ) {

                       Spacer(Modifier.size(1.dp))

                       StyledText( treino.name.title() , color = MaterialTheme.colorScheme.surface, fontSize = 25.sp)

                       Column(
                           horizontalAlignment = Alignment.CenterHorizontally,
                           verticalArrangement = Arrangement.Center){

                           Button(
                               modifier = Modifier
                                   .width(170.dp),
                               colors = ButtonDefaults.buttonColors(
                                   containerColor = Yellow,
                                   contentColor = Color.Black
                               ),
                               onClick = {
                                   scope.launch {

                                       val verifyList = GetListExercicies(context, treino.id)

                                       if (treino.name == "criando"){

                                           MakeMessage(context,"Termine de criar o treino para começar a treinar")

                                       } else if (verifyList.isEmpty()){

                                           MakeMessage(context, "Não Há Exercicios nesse treino")

                                       } else {

                                           startTraining = true

                                           navController.navigate(Training(treino.id))

                                       }

                                   }
                               }
                           ) {
                               StyledText("Comecar", fontSize = 15.sp, color = Black, fontWeight = FontWeight.Bold)
                           }

                           Button(
                               modifier = Modifier
                                   .width(170.dp),
                               colors = ButtonDefaults.buttonColors(
                                   containerColor = Gray,
                                   contentColor = Color.Black
                               ),
                               onClick = {navController.navigate(EditOrAddTreino(treino.id.toString()))}) {
                               StyledText("Editar", fontSize = 15.sp, color = Black, fontWeight = FontWeight.Bold)
                           }

                       }
                   }
                   Spacer(Modifier.size(10.dp))
               }

           }

       } else {

           Column(
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally,
               modifier = Modifier
                   .fillMaxSize()
                   .background(color = Black)
           ) {
               StyledText("Não Há Treinos Criados!!")
           }
       }


        if (startTraining){

            startTraining = false

            if (DaysTraining == 0){

                DaysTraining++

                EditDaysTraining(DaysTraining)

                EditLastDayTraining(LocalDate.now().toString())

            } else if (LastDayTraining != LocalDate.now().toString()){

                DaysTraining++

                EditDaysTraining(DaysTraining)

                EditLastDayTraining(LocalDate.now().toString())

            }

        }

    }
}
