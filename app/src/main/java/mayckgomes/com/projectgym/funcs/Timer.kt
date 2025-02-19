package mayckgomes.com.projectgym.funcs

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import mayckgomes.com.projectgym.ui.theme.Yellow

@Composable
fun StartTimerInReverse(time: Int) {
    var isRunning by remember { mutableStateOf(false) }
    var currentTimeInSeconds by remember { mutableIntStateOf(time) }
    var seconds by remember { mutableIntStateOf(time % 60) }
    var minutes by remember { mutableIntStateOf(time / 60) }
    var hours by remember { mutableIntStateOf(time / 3600) }
    var enabled by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = isRunning, key2 = currentTimeInSeconds) {
        if (isRunning && currentTimeInSeconds > 0) {
            delay(1000)
            currentTimeInSeconds--

            seconds = currentTimeInSeconds % 60
            minutes = (currentTimeInSeconds / 60) % 60
            hours = currentTimeInSeconds / 3600
        } else if (seconds == 0 && minutes == 0 && hours == 0){
            isRunning = false
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(200.dp)
                .background(color = Color.Transparent)
                .border(width = 1.dp, color = Yellow, shape = CircleShape)
        ) {
            Text(
                text = String.format("%02d:%02d:%02d", hours, minutes, seconds),
                fontSize = 33.sp,
                color = Color.White
            )
        }

        Spacer(Modifier.size(20.dp))

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Yellow, contentColor = Color.Black),
            onClick = { isRunning = !isRunning }
        ) {
            Text(
                if (isRunning) {
                "Parar"
            } else if (currentTimeInSeconds == 0) {
                enabled = false
                "Finalizado"
            } else {
                "Começar"
            })
        }
    }
}

@Composable
fun StartTimer(){

    var isRunning by remember { mutableStateOf(false) }
    var currentTimeInSeconds by remember { mutableIntStateOf(0) }
    var seconds by remember { mutableIntStateOf(currentTimeInSeconds % 60) }
    var minutes by remember { mutableIntStateOf(currentTimeInSeconds / 60) }
    var hours by remember { mutableIntStateOf(currentTimeInSeconds / 3600) }

    LaunchedEffect(key1 = isRunning, key2 = currentTimeInSeconds) {
        if (isRunning) {
            delay(1000)
            currentTimeInSeconds++

            seconds = currentTimeInSeconds % 60
            minutes = (currentTimeInSeconds / 60) % 60
            hours = currentTimeInSeconds / 3600
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(200.dp)
                .background(color = Color.Transparent)
                .border(width = 1.dp, color = Yellow, shape = CircleShape)
        ) {
            Text(
                text = String.format("%02d:%02d:%02d", hours, minutes, seconds),
                fontSize = 33.sp,
                color = Color.White
            )
        }

        Spacer(Modifier.size(20.dp))

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Yellow, contentColor = Color.Black),
            onClick = { isRunning = !isRunning }
        ) {
            Text(if (isRunning) "Parar" else "Começar")
        }
    }

}