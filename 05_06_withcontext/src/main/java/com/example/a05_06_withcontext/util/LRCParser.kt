package com.example.a05_06_withcontext.util

import android.content.Context
import android.util.Log
import com.example.a05_06_withcontext.model.LRC
import java.io.ByteArrayOutputStream

object LRCParser {
    fun loadLRCFile(context: Context,lrcId:Int): String {
        val inputStream = context.resources.openRawResource(lrcId)
        var bytes:ByteArray = ByteArray(inputStream.available())
        inputStream.read(bytes)
        val outputSream =ByteArrayOutputStream()
        outputSream.write(bytes)
        outputSream.close()
        inputStream.close()
        return outputSream.toString()
    }

    fun parse(context: Context,lrcId: Int):LRC{
        val lrc =LRC()
        val lrcContext = loadLRCFile(context,lrcId)
        val lines = lrcContext.split("\n")
        val reg = Regex("[\\[|\\]]")
        for (line in lines){
            var content=line.replace(reg," ").trim()
            var info = content.split(" ")
            when{
                "ti" in info[0]->lrc.title = if(info.size>=2) "${info[1]}" else ""
                "al" in info[0]->lrc.album = if(info.size>=2) "专辑:${info[1]}" else ""
                "ar" in info[0]->lrc.author = if(info.size>=2) "${info[1]}" else ""
                "by" in info[0]->lrc.by = if(info.size>=2) "歌词编辑:${info[1]}" else ""
                else ->lrc.lrcMap.put(convertLongTime(info[0]),info[1])
            }
        }
        return lrc
    }
    fun convertLongTime(timeStr:String):Int{
        var timeList =timeStr.replace(".",":").split(":")
        var first =Integer.valueOf(timeList[0])
        var second =Integer.valueOf(timeList[1])
        var third =Integer.valueOf(timeList[2])
        Log.d("MusicService","${first},${second},${third}")
        var result =first*60*1000+second*1000
        return result
    }
}