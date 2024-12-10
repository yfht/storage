package com.example.a05_06_withcontext.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope

data class StateHolder(val playProgress:MutableIntState,
                       val playPercent:MutableFloatState,
                       val playingState:MutableState<Boolean>,
                       val lrcContent:MutableState<String>,
                       val scope:CoroutineScope){//协程范围
                       }
@Composable
fun rememberState(playProgress:MutableIntState = mutableIntStateOf(0),
                  playPercent:MutableFloatState = mutableFloatStateOf(0.0f),
                  playingState:MutableState<Boolean> = mutableStateOf(false),
                  lrcContent:MutableState<String> = mutableStateOf(""),
                  scope:CoroutineScope = rememberCoroutineScope()):StateHolder
= remember(playProgress,playPercent,playingState,lrcContent,scope){
    StateHolder(playProgress,playPercent,playingState,lrcContent,scope)
}