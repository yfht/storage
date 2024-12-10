package com.example.a05_06_withcontext.util

class TypeConvertor{
    companion object{
        fun intToTimeStr(timeInt: Int):String{
            val totalseconds=timeInt/1000
            val minutes=totalseconds/60
            val seconds=totalseconds%60
            return String.format("%02d:%02d",minutes,seconds)
        }

        fun strToLong(timeStr:String):Long{
            val s=timeStr.split(Regex("[.|:]"))
            val minute =Integer.valueOf(s[0])
            val second =Integer.valueOf(s[1])
            var millSecond=0
            if(s.size>=3)
                millSecond=Integer.valueOf(s[2])
            return minute*60*1000L+second*1000L+millSecond
        }
    }
}