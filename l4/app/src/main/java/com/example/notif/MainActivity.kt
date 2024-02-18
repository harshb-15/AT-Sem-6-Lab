package com.example.notif

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import com.example.notif.ui.theme.NotifTheme

// ... (your existing imports)

var score by mutableIntStateOf(0)
var showDialog by mutableStateOf(false)
var showScoreDialog by mutableStateOf(false)
var quesNumber by mutableIntStateOf(0)
var questions = arrayListOf("What is India's Capital?", "What is your name?")
var answers = arrayListOf("Delhi", "Harsh")
var options = arrayListOf(
    arrayOf("Delhi", "Gujarat"),
    arrayOf("Harsh", "Bhansali")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotifTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (!showScoreDialog) {
                            Question(i = quesNumber)
                            SimpleButton(i = quesNumber, btn = 0)
                            SimpleButton(i = quesNumber, btn = 1)
                        }
                        if (showDialog) {
                            // Confirmation Dialog
                            AlertDialog(
                                onDismissRequest = {
                                    showDialog = false
                                },
                                title = {
                                    Text("Confirm Submission")
                                },
                                text = {
                                    Text("Are you sure you want to submit?")
                                },
                                confirmButton = {
                                    TextButton(
                                        onClick = {
                                            showDialog = false
                                            showScoreDialog = true
                                        }
                                    ) {
                                        Text("Yes")
                                    }
                                },
                                dismissButton = {
                                    TextButton(
                                        onClick = {
                                            showDialog = false
                                        }
                                    ) {
                                        Text("No")
                                    }
                                }
                            )
                        }
                        if (showScoreDialog) {
                            // Score Dialog
                            AlertDialog(
                                onDismissRequest = {
                                    showScoreDialog = false
                                },
                                title = {
                                    Text("Quiz Result")
                                },
                                text = {
                                    Text("Your score is $score out of ${questions.size}.")
                                },
                                confirmButton = {
                                    TextButton(
                                        onClick = {
                                            // Reset the state for a new quiz
                                            score = 0
                                            quesNumber = 0
                                            showScoreDialog = false
                                        }
                                    ) {
                                        Text("OK")
                                    }
                                }
                            )
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun SimpleButton(i: Int, btn: Int) {
    var option = options[i][btn]
    var ans = answers[i]

    Button(
        onClick = {
            if (ans == option) {
                score++
            }
            if (quesNumber < questions.size - 1) {
                quesNumber++
            } else {
                showDialog = true
            }
        }
    ) {
        Text(text = "$option")
    }
}

// ... (your existing composable functions)


@Composable
fun Question(i: Int) {
    var ques = questions[i]
    Text(text = "$ques")
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    NotifTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                quesNumber = quesNumber;
                Question(i = quesNumber)
                SimpleButton(i = quesNumber, btn = 0)
                SimpleButton(i = quesNumber, btn = 1)
            }
        }
    }
}
