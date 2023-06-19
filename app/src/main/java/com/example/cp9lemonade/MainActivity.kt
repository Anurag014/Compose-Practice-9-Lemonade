package com.example.cp9lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeApp()
        }
    }
}

@Composable
fun LemonadeApp() {

    var currentStep by remember { mutableStateOf(1) }

    var squeezeCount by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp)
            .background(Color.White)
        ) {
            when (currentStep) {
                1 -> {
                    LemonTextAndImage(
                        text = "Tap the lemon tree to select a lemon",
                        drawableResourceId = R.drawable.lemon_tree,
                        contentDescription = "Lemon Tree",
                        onImageClick = {
                            currentStep = 2
                            squeezeCount = (2..4).random()
                        }
                    )
                }
                2 -> {
                    LemonTextAndImage(
                        text = "Keep taping the lemon to squeeze it",
                        drawableResourceId = R.drawable.lemon_squeeze,
                        contentDescription = "Lemon",
                        onImageClick = {
                            squeezeCount--
                            if (squeezeCount == 0) {
                                currentStep = 3
                            }
                        }
                    )
                }

                3 -> {
                    LemonTextAndImage(
                        text = "Tap the lemonade to drink it",
                        drawableResourceId = R.drawable.lemon_drink,
                        contentDescription = "Lemonade",
                        onImageClick = {
                            currentStep = 4
                        }
                    )
                }
                4 -> {
                    LemonTextAndImage(
                        text = "Tap the empty glass to start again",
                        drawableResourceId = R.drawable.lemon_restart,
                        contentDescription = "Empty glass",
                        onImageClick = {
                            currentStep = 1
                        }
                    )}}
    }
}

@Composable
fun LemonTextAndImage(
    text: String,
    drawableResourceId: Int,
    contentDescription: String,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = onImageClick,
                shape = RoundedCornerShape(20.dp)
            ) {
                Image(
                    painter = painterResource(drawableResourceId),
                    contentDescription = contentDescription,
                    modifier = Modifier
                        .width(250.dp)
                        .height(250.dp)
                        .padding(2.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = text,
                fontSize = 18.sp
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun LemonPreview() {
    LemonadeApp()
}