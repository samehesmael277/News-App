package com.sameh.news.data.local

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.sameh.news.data.remote.Source

@TypeConverters
class ArticleTypeConverter {

    @TypeConverter
    fun fromSource(source: Source?): String? {
        return Gson().toJson(source)
    }

    @TypeConverter
    fun toSource(sourceString: String?): Source? {
        return Gson().fromJson(sourceString, Source::class.java)
    }
}