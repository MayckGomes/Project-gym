package mayckgomes.com.projectgym.Screens.Training

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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import mayckgomes.com.projectgym.DataTypes.Exercicio
import mayckgomes.com.projectgym.Menu
import mayckgomes.com.projectgym.ui.theme.DarkGray
import mayckgomes.com.projectgym.ui.theme.Gray
import mayckgomes.com.projectgym.ui.theme.LightGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Exercicios(navController: NavController){

    var exercicios = listOf(
        Exercicio(
            nome = "Esteira",
            series = 15,
            repeticoes = -1
        ),
        Exercicio(
            nome = "Supino Inclinado",
            series = 3,
            repeticoes = 15
        )
    )

    var menuIsOpen by rememberSaveable {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(90.dp)
                    .clip(RoundedCornerShape(0.dp,0.dp,12.dp,12.dp))
                    .background(color = LightGray)
                    .padding(5.dp,20.dp,20.dp,20.dp)
                    .padding(top = 25.dp)
            ){
                Row {

                    IconButton(onClick = {navController.navigate(Menu)}) {
                        Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = null)
                    }

                    Text("EXERCICIOS", color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Bold)
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {menuIsOpen = true}) {
                Icon(Icons.Filled.Add,contentDescription = null)
            }
        }

    ) {innerpadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Gray)
                .padding(innerpadding)
                .padding(10.dp)
        ) {

            items(exercicios){ exercicio ->

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

                    Text( exercicio.nome , color = Color.White, fontSize = 25.sp)

                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Gray,
                            contentColor = Color.Black
                        ),
                        onClick = {null}) {
                        Text("Editar", fontSize = 15.sp)
                    }

                }

                Spacer(Modifier.size(10.dp))

            }

        }

        if (menuIsOpen){
            ModalBottomSheet(onDismissRequest = {menuIsOpen = false},
                modifier = Modifier
                    .background(color = LightGray)
                    .padding(10.dp)
            ) {
                Text("teste")
            }
        }

    }

}

@Preview(showSystemUi = true)
@Composable
fun ExerciciosPreview(){
    Exercicios(rememberNavController())
}