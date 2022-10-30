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

    var state by remember { mutableStateOf(false) }

    var state2 by remember { mutableStateOf(false) }

    var strState by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
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