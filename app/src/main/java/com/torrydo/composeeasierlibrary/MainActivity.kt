package com.torrydo.composeeasierlibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.torrydo.compose_easier.view.ButtonEz
import com.torrydo.compose_easier.view.CheckBoxEz
import com.torrydo.compose_easier.view.SwitchEz
import com.torrydo.compose_easier.view.TextFieldEz
import com.torrydo.composeeasierlibrary.ui.theme.ComposeEasierLibraryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeEasierLibraryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        ButtonDemo()
    }

}


@Composable
private fun ButtonDemo(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.height(250.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Button(onClick = {}){
            Text(text = "Default Button")
        }

        ButtonEz.Flat(onClick = {}) {
            Text(text = "Flat Button")
        }

        ButtonEz.Outline(onClick = {}) {
            Text(text = "Outlined Button")
        }

        ButtonEz.Gradient(onClick = {}) {
            Text(text = "Gradient Button")
        }


    }
}

@Composable
private fun CheckBoxDemo(
    modifier: Modifier = Modifier
) {
    var state by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
    ) {

        CheckBoxEz.RoundedCorner(checked = state, onChange = { state = it })

        Spacer(modifier = Modifier.height(20.dp))

        CheckBoxEz.RoundedCorner(checked = state.not(), onChange = { state = it })


    }
}

@Composable
private fun SwitchDemo(
    modifier: Modifier = Modifier
) {
    var state by remember { mutableStateOf(false) }

    var state2 by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Column {
            SwitchEz.Border(isOn = state, onChange = { state = it })

            Spacer(modifier = Modifier.height(20.dp))

            SwitchEz.Border(isOn = state.not())
        }


        Spacer(modifier = Modifier.width(60.dp))

        Column {
            SwitchEz.Fill(isOn = state2, onChange = { state2 = it })

            Spacer(modifier = Modifier.height(20.dp))

            SwitchEz.Fill(isOn = state2.not())
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun TextFieldDemo(
    modifier: Modifier = Modifier
) {
    var strState by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(vertical = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        TextFieldEz.EditText(
            value = strState,
            placeHolderText = { Text(text = "type me!") },
            onValueChange = { strState = it },
            textStyle = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp
            ),
            modifier = Modifier.background(Color.Transparent)
        )

        TextField(
            value = strState, onValueChange = { strState = it },
            textStyle = TextStyle(fontSize = 10.sp),
            modifier = Modifier.height(40.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeEasierLibraryTheme {
        Greeting("Android")
    }
}