package com.example.a05_06_withcontext.ui.screen

import android.animation.TypeConverter
import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a05_06_withcontext.R
import com.example.a05_06_withcontext.model.LRC
import com.example.a05_06_withcontext.model.Music
import com.example.a05_06_withcontext.ui.component.AnimationText
import com.example.a05_06_withcontext.util.LRCParser
import com.example.a05_06_withcontext.util.TypeConvertor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun PlayScreen(modifier: Modifier) {
    val context = LocalContext.current


    val music = Music(R.mipmap.demon, "小半", R.raw.song, R.raw.songlrc)
    val mediaPlayer = MediaPlayer.create(context, music.songId)
    val lrc: LRC = LRCParser.parse(context, R.raw.songlrc)

    val stateHolder = rememberState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            music.songName,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold, color = Color(0xFF000000),
            fontFamily = FontFamily(Font(R.font.lishu))
        )

        Image(
            modifier = modifier
                .width(240.dp)
                .height(240.dp),
            painter = painterResource(id = R.mipmap.album),
            contentDescription = null
        )

        Row(horizontalArrangement = Arrangement.Center) {
            Text("00:00", color = Color.White)

            LinearProgressIndicator(
                progress = { stateHolder.playPercent.value },
                trackColor = Color.Yellow,
                color = Color.Cyan
            )
            Text("${TypeConvertor.intToTimeStr(mediaPlayer.duration)}")
        }
        AnimationText(stateHolder.lrcContent)


        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            TextButton(onClick = {
                stateHolder.scope.launch {
                    withContext(Dispatchers.IO) {
                        mediaPlayer.start()
                        stateHolder.playingState.value = mediaPlayer.isPlaying
                    }
                    withContext(Dispatchers.Default) {
                        while (stateHolder.playingState.value) {
                            delay(1000)
                            stateHolder.playProgress.value = mediaPlayer.currentPosition
                            var timeTag = (stateHolder.playProgress.value / 1000) * 1000
                            stateHolder.lrcContent.value =
                                lrc.lrcMap.get(timeTag) ?: "${stateHolder.lrcContent.value}"
                            stateHolder.playPercent.value =
                                stateHolder.playProgress.value.toFloat() / mediaPlayer.duration
                        }
                    }
                }
            }) {
                Text("播放", fontSize = 24.sp)
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = "播放", tint = Color.White
                )
            }
            TextButton(onClick = {
                stateHolder.scope.launch {
                    stateHolder.playingState.value = false
                    mediaPlayer.stop()
                }
            }) {
                Text("停止", fontSize = 24.sp)
                Icon(
                    imageVector = Icons.Filled.Done,
                    tint = Color.White, contentDescription = "停止"
                )
            }
        }
    }
}
