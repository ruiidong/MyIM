package com.example.base.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("result")
    val result: NewsResult?
)

data class NewsResult(
    @SerializedName("curpage")
    val curPage: Int,
    @SerializedName("allnum")
    val pageSize: Int,
    @SerializedName("newslist")
    val newsList: List<New>
)

@Entity(tableName="news")
data class New(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val uniqueKey: String,
    @SerializedName("ctime")
    val publishTime: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("source")
    val source: String,
    @SerializedName("picUrl")
    val picUrl: String,
    @SerializedName("url")
    val articleUrl: String
)

@Entity(tableName = "newRemoteKeys")
data class NewRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val uniqueKey:String,
    val prePage:Int?,
    val nextPage:Int?
)