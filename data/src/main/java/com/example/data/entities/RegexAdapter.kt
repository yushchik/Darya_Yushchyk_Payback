package com.example.data.entities

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class RegexAdapter {
    @ToJson
    fun toJson(regex: Regex): String = regex.pattern
    @FromJson
    fun fromJson(value: String): Regex = value.toRegex()
}
