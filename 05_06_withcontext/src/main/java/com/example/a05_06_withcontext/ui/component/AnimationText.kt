package com.example.a05_06_withcontext.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AnimationText(content:MutableState<String>){
    val density = LocalDensity.current
    AnimatedVisibility(
        visible = true,
        enter = slideInHorizontally {
            with(density){-10.dp.roundToPx()}
        } + expandHorizontally(
            expandFrom = Alignment.Start)
          + fadeIn(
            initialAlpha = 0.8f   ),
        exit = slideOutHorizontally() + shrinkHorizontally() + fadeOut() ){
        Text(modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            text= "${content.value}",
            color = Color.Magenta,
            fontSize = 20.sp,
            maxLines = 5)

    }

}