package com.fake.quotes.data.remote

import com.fake.quotes.data.model.CatFact
import retrofit2.http.GET

interface ApiService {
    @GET("fact")
    suspend fun getFact(): CatFact
}
