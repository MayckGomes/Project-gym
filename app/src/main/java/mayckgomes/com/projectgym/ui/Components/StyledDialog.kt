package mayckgomes.com.projectgym.ui.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.window.Dialog
import mayckgomes.com.projectgym.funcs.System
import mayckgomes.com.projectgym.funcs.UserData.EditName
import mayckgomes.com.projectgym.ui.theme.Black
import mayckgomes.com.projectgym.ui.theme.Gray
import mayckgomes.com.projectgym.ui.theme.White
import mayckgomes.com.projectgym.ui.theme.Yellow

@Composable
fun StyledNameDialog(
    onDismissRequest: () -> Unit,
): String {

    var name by rememberSaveable {
        mutableStateOf("")
    }

    var isClicked by rememberSaveable {
        mutableStateOf(false)
    }

    Dialog(onDismissRequest) {

        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .height(250.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(color = Gray)
                .padding(15.dp)



        ){



            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                StyledText("Olá, Seja bem-vindo(a) ao Project GYM!!", color = White, fontSize = 20.sp, fontWeight = FontWeight.Bold)

                Spacer(Modifier.size(10.dp))

                StyledText("Para começar, Digite seu nome", color = White)

                Spacer(Modifier.size(10.dp))

                StyledTextField(
                    value = name,
                    onValueChange = {name = it},
                    label = { StyledText("Nome", color = White) }
                )

                Spacer(Modifier.size(25.dp))

                Button(
                    onClick = {isClicked = true},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Yellow,
                        contentColor = Black
                    ),
                    shape = RoundedCornerShape(15),
                    modifier = Modifier.fillMaxWidth()

                ) {

                    StyledText("Começar", fontWeight = FontWeight.Bold)

                }

            }

        }

    }

    if (isClicked){

        EditName(name)

        System.name = name

        isClicked = false

        onDismissRequest()

    }

    return name

}

@Preview
@Composable
fun StyledNameDialogPreview(){
    StyledNameDialog({})
}