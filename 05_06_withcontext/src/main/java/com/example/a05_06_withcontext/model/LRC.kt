package com.example.a05_06_withcontext.model

data class LRC (
    var author:String="",
    var title:String="",
    var album:String="",
    var by:String="",
    var lrcMap:MutableMap<Int,String> = mutableMapOf<Int,String>())