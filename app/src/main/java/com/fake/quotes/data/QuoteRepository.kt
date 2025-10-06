package com.fake.quotes.data

import com.fake.quotes.data.model.CatFact
import com.fake.quotes.data.remote.NetworkModule

class QuoteRepository {
    private val api = NetworkModule.api
    suspend fun random(): CatFact = api.getFact()
}
