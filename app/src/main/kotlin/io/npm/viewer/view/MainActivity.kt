package io.npm.viewer.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import io.npm.viewer.ui.theme.NpmViewerTheme
import io.npm.viewer.ui.theme.Purple40
import io.npm.viewer.viewmodel.MainActivityViewModel
import org.json.JSONObject

class MainActivity : ComponentActivity() {

    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        setContent {
            NpmViewerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val owner = this

                    Box {
                        Surface(color = Purple40, modifier = Modifier.fillMaxSize()) {

                        }

                        Surface(
                            modifier = Modifier
                                .height(650.dp)
                                .fillMaxHeight()
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(80.dp).copy(
                                topEnd = ZeroCornerSize,
                                topStart = ZeroCornerSize
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Spacer(modifier = Modifier.padding(16.dp))

                                var text by remember { mutableStateOf("Hello") }

                                OutlinedTextField(value = text, onValueChange = {
                                    text = it
                                }, modifier = Modifier.fillMaxWidth())

                                OutlinedButton(onClick = {
                                    Toast.makeText(applicationContext, "검색!", Toast.LENGTH_SHORT).show()

                                    val data = mainActivityViewModel.getSearchData(text)

                                    data!!.observe(owner) { data ->
//                                        println(data.message)
                                        val test = Regex("window.__context__ = (.*)").find(data.message!!)?.groupValues?.get(1)
                                        println(test)
                                        if (test != null) {
                                            val str = JSONObject(test).toString(2)
                                            println(str)
                                        }
                                        return@observe
                                    }
                                }, border = BorderStroke(1.dp, Purple40),
                                    shape = RoundedCornerShape(50),
                                    colors = ButtonDefaults.outlinedButtonColors(
                                        contentColor = Purple40
                                    )) {
                                    Text(text = "검색")
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NpmViewerTheme {
        Greeting("Android")
    }
}