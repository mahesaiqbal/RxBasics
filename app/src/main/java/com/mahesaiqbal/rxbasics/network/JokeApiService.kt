package com.mahesaiqbal.rxbasics.network

import com.mahesaiqbal.rxbasics.model.JokeApiResponse
import io.reactivex.Single
import retrofit2.http.GET

interface JokeApiService {
    @GET("jokes/random")
    fun randomJoke(): Single<JokeApiResponse>
}