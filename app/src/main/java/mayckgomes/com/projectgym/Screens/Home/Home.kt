package mayckgomes.com.projectgym.Screens.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mayckgomes.com.projectgym.funcs.System
import mayckgomes.com.projectgym.funcs.System.LastTraining
import mayckgomes.com.projectgym.funcs.System.name
import mayckgomes.com.projectgym.funcs.UserData.GetName
import mayckgomes.com.projectgym.funcs.title
import mayckgomes.com.projectgym.ui.Components.StyledNameDialog
import mayckgomes.com.projectgym.ui.Components.StyledText
import mayckgomes.com.projectgym.ui.theme.Black
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
    var timeDaily by rememberSaveable {
        mutableStateOf(60)
    }

    var trainingDays by rememberSaveable {
        mutableStateOf(3)
    }

    var lastTraining by rememberSaveable {
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
            .background(color = Black)
            .padding(10.dp)
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .size(75.dp)
                .background(color = DarkGray)
                .border(
                    width = 2.dp,
                    color = Yellow,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(15.dp)

        ){
            StyledText("Olá, ${userName.title()}", color = White, fontSize = 25.sp)
        }

        Spacer(modifier = Modifier.size(15.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(color = DarkGray)
                .padding(10.dp)
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text("Sua media diaria é:", color = Color.White)
                Spacer(Modifier.size(10.dp))
                Text(
                    if (timeDaily>=60) "${timeDaily/60}h ${timeDaily%60}m" else "${timeDaily}m",
                    color = Color.White
                )
            }
        }

        Spacer(Modifier.size(15.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(color = DarkGray)
                .padding(10.dp)
        ){
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                StyledText("Você Treinou ", color = Color.White)

                Spacer(Modifier.size(10.dp))

                StyledText("$trainingDays dias", color = Color.White)

            }
        }

        Spacer(Modifier.size(15.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(color = DarkGray)
                .padding(10.dp)
        ){

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                StyledText("O ultimo Treino Foi:",color = Color.White)

                Spacer(Modifier.size(10.dp))

                StyledText(lastTraining, color = Color.White)

            }

        }

    }

}

@Preview(showSystemUi = true)
@Composable
fun HomePreview(){
    HomeScreen()
}