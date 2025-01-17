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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import mayckgomes.com.projectgym.EditOrAddTreino
import mayckgomes.com.projectgym.funcs.UserFuncs.GetTreinos
import mayckgomes.com.projectgym.ui.theme.DarkGray
import mayckgomes.com.projectgym.ui.theme.Gray
import mayckgomes.com.projectgym.ui.theme.LightGray
import mayckgomes.com.projectgym.ui.theme.Yellow

@Composable
fun TrainingScreen(navController: NavController){

    val listaTreinos = GetTreinos()

    val lista by rememberSaveable {
        mutableStateOf(listaTreinos)
    }

   Scaffold(
       modifier = Modifier
           .background(color = Gray),

       topBar = {
           Box(
               contentAlignment = Alignment.CenterStart,
               modifier = Modifier
                   .fillMaxWidth()
                   .size(70.dp)
                   .clip(RoundedCornerShape(0.dp,0.dp,12.dp,12.dp))
                   .background(color = LightGray)
                   .padding(20.dp)
           ){
               Text("TREINOS", color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Bold)
           }
       },
       floatingActionButton = {
           FloatingActionButton(
               contentColor = Color.Black,
               containerColor = Yellow,
               onClick = { navController.navigate(EditOrAddTreino("nada"))}
               ) {
                Icon(Icons.Default.Add,contentDescription = null)
           }
       }
   )
    { innerpadding ->
       if (lista.isNotEmpty()){

           LazyColumn(
               modifier = Modifier
                   .fillMaxSize()
                   .background(color = Gray)
                   .padding(innerpadding)
                   .padding(10.dp)
           ) {
               items(lista){ treino ->

                   Row(
                       verticalAlignment = Alignment.CenterVertically,
                       horizontalArrangement = Arrangement.SpaceBetween,
                       modifier = Modifier
                           .fillMaxWidth(1f)
                           .clip(RoundedCornerShape(12.dp))
                           .background(DarkGray)
                           .padding(10.dp)
                   ) {

                       Spacer(Modifier.size(1.dp))

                       Text( treino.nome , color = Color.White, fontSize = 25.sp)

                       Column(
                           horizontalAlignment = Alignment.CenterHorizontally,
                           verticalArrangement = Arrangement.Center){

                           Button(
                               modifier = Modifier.fillMaxWidth(0.5f),
                               colors = ButtonDefaults.buttonColors(
                                   containerColor = Yellow,
                                   contentColor = Color.Black
                               ),
                               onClick = {null}
                           ) {
                               Text("Comecar", fontSize = 15.sp)
                           }

                           Button(
                               modifier = Modifier.fillMaxWidth(0.5f),
                               colors = ButtonDefaults.buttonColors(
                                   containerColor = Gray,
                                   contentColor = Color.Black
                               ),
                               onClick = {navController.navigate(EditOrAddTreino(treino.id))}) {
                               Text("Editar", fontSize = 15.sp)
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
                   .background(color = Gray)
           ) {
               Text("Não Há Treinos Criados!!")
           }

       }
    }
}
