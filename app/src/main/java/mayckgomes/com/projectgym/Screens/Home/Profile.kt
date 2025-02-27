package mayckgomes.com.projectgym.Screens.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mayckgomes.com.projectgym.funcs.System
import mayckgomes.com.projectgym.funcs.UserData.EditName
import mayckgomes.com.projectgym.funcs.title
import mayckgomes.com.projectgym.ui.Components.StyledText
import mayckgomes.com.projectgym.ui.Components.StyledTextField
import mayckgomes.com.projectgym.ui.theme.Black
import mayckgomes.com.projectgym.ui.theme.DarkGray
import mayckgomes.com.projectgym.ui.theme.White
import mayckgomes.com.projectgym.ui.theme.Yellow

@Composable
fun ProfileScreen(){

    var name = System.name

    var newName by rememberSaveable {
        mutableStateOf("")
    }

    var isClicked by rememberSaveable {
        mutableStateOf(false)
    }

    val userName by rememberSaveable {
        mutableStateOf(name)
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
                StyledText("PERFIL", color = White, fontSize = 25.sp, fontWeight = FontWeight.Bold)
            }
        }
    ){innerpadding ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(innerpadding)) {

            Spacer(Modifier.size(65.dp))

            Icon(Icons.Outlined.AccountCircle, contentDescription = null, Modifier.size(65.dp), tint = MaterialTheme.colorScheme.surface)

            Spacer(Modifier.size(65.dp))

            StyledText("Olá, ${userName.title()}", color = MaterialTheme.colorScheme.surface, fontSize = 30.sp)

            Spacer(Modifier.size(65.dp))

            StyledTextField(
                value = newName,
                onValueChange = {newName = it},
                label = { StyledText("Editar Nome", color = MaterialTheme.colorScheme.surface) }
            )

            Spacer(Modifier.size(20.dp))

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Yellow,
                    contentColor = Black),
                onClick = {isClicked = true}) {
                Text("Salvar")
            }

            if(isClicked){
                EditName(newName)
                name = newName
            }

        }

    }

}

@Preview(showSystemUi = true)
@Composable
fun ProfileScreenPreview(){
    ProfileScreen()
}