package mayckgomes.com.projectgym.Screens.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mayckgomes.com.projectgym.funcs.System.DaysTraining
import mayckgomes.com.projectgym.funcs.System.LastTraining
import mayckgomes.com.projectgym.funcs.System.MediaDaysTraining
import mayckgomes.com.projectgym.funcs.System.name
import mayckgomes.com.projectgym.funcs.title
import mayckgomes.com.projectgym.ui.Components.StyledNameDialog
import mayckgomes.com.projectgym.ui.Components.StyledText
import mayckgomes.com.projectgym.ui.theme.DarkGray
import mayckgomes.com.projectgym.ui.theme.Gray
import mayckgomes.com.projectgym.ui.theme.White
import mayckgomes.com.projectgym.ui.theme.Yellow

@Composable
fun HomeScreen(){

    var userName by rememberSaveable {
        mutableStateOf("")
    }

    // tempo em segundos
    val timeDaily by rememberSaveable {
        mutableStateOf(MediaDaysTraining)
    }

    val trainingDays by rememberSaveable {
        mutableStateOf(DaysTraining)
    }

    val lastTraining by rememberSaveable {
        mutableStateOf(LastTraining)
    }

    var dialog by rememberSaveable {
        mutableStateOf(false)
    }

    if (name == "Usuario"){

        dialog = true

    } else {

        userName = name

    }

    if (dialog){

        userName = StyledNameDialog(onDismissRequest = {dialog = false})

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(10.dp)
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .size(75.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(color = MaterialTheme.colorScheme.secondary)
                .border(
                    width = 2.dp,
                    color = Yellow,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(15.dp)

        ){
            StyledText("Olá, ${userName.title()}", color = White, fontSize = 25.sp)
        }

        Spacer(modifier = Modifier.size(90.dp))

        Box(
            modifier = Modifier
                .fillMaxSize(1f)
                .systemBarsPadding()
                .clip(RoundedCornerShape(5))
                .background(color = DarkGray)
        ){

            Column(
                Modifier
                    .fillMaxSize(1f)
                    .padding(10.dp)
            ) {

                Row {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.49f)
                            .height(170.dp)
                            .clip(RoundedCornerShape(7))
                            .background(color = Gray)
                            .padding(10.dp,30.dp)
                    ){

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top,
                            modifier = Modifier.fillMaxSize()
                        ) {

                            Text("Dias Treinados", color = White, fontWeight = FontWeight.Bold)

                            Spacer(Modifier.size(25f.dp))

                            Text(trainingDays.toString(), color = White, fontWeight = FontWeight.Bold, fontSize = 50.sp)

                        }

                    }

                    Spacer(Modifier.size(10.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(170.dp)
                            .clip(RoundedCornerShape(7))
                            .background(color = Gray)
                            .padding(10.dp,30.dp)
                    ){

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top,
                            modifier = Modifier.fillMaxSize()
                        ) {

                            Text("Ultimo Treino", color = White, fontWeight = FontWeight.Bold)

                            Spacer(Modifier.size(25f.dp))

                            Text(lastTraining, color = White, fontWeight = FontWeight.Bold, fontSize = 30.sp)

                        }

                    }

                }

                Spacer(Modifier.size(15.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(170.dp)
                        .clip(RoundedCornerShape(7))
                        .background(color = Gray)
                        .padding(0.dp, 30.dp)
                ){

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top,
                        modifier = Modifier.fillMaxSize()
                    ) {

                        Text("Media Diaria", color = White, fontWeight = FontWeight.Bold)

                        Spacer(Modifier.size(25f.dp))

                        Text("${timeDaily/3600} horas , ${(timeDaily%3600)/60} minutos, ${timeDaily%60} segundos", color = White, fontWeight = FontWeight.Bold, fontSize = 20.sp)

                    }

                }


            }

        }

    }

}

@Preview(showSystemUi = true)
@Composable
fun HomePreview(){
    HomeScreen()
}