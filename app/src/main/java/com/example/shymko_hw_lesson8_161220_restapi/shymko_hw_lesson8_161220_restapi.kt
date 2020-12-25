package com.example.shymko_hw_lesson8_161220_restapi

import com.google.gson.Gson
import retrofit2.*
import retrofit2.http.*
import java.util.*
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
/*
За допомогою Retrofit скачати JSON за посиланням:
https://cat-fact.herokuapp.com/facts

Конвертувати його в Кotlin об'єкти на ввести результати в консоль.

·        Поле status є обов'язковим.
·        Деякі з полів JSON проігноруйте (на ваш вибір)
    
*/

data class Status(
    var verified: Boolean = false,
    var sentCount: Int = 0,
    var feedback: String? = null
)

data class CatRoot(
    var status: Status? = null,
    var type: String? = null,
    var deleted: Boolean = false,
    var _id: String? = null,
    var user: String? = null,
    var text: String? = null,
    var __v: Int = 0,
    var source: String? = null,
    var updatedAt: Date? = null,
    var createdAt: Date? = null,
    var used: Boolean = false
)

interface CatInterface{
    @GET("facts")
    fun getCat(): Call<MutableList<CatRoot>>
}

fun main() {
    val retrofit:Retrofit=Retrofit.Builder()
        .baseUrl("https://cat-fact.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitCreate:CatInterface=retrofit.create(CatInterface::class.java)
    val retrofitExecute = retrofitCreate.getCat().execute()
    val catsMutableList:MutableList<CatRoot>? = retrofitExecute.body()

    catsMutableList?.forEach {
        println(it)
    }
}