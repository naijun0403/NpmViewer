package io.npm.viewer.retrofit

import io.npm.viewer.retrofit.converter.HtmlConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit

object RetrofitClient {

    private const val NPM_HOST = "https://npmjs.com/"

    val retrofitClient: Retrofit.Builder by lazy {
        val client = OkHttpClient.Builder()
        Retrofit.Builder()
            .baseUrl(NPM_HOST)
            .client(client.build())
            .addConverterFactory(HtmlConverterFactory.create(NPM_HOST))
    }

    val npm: NpmApiInterface by lazy {
        retrofitClient
            .build()
            .create(NpmApiInterface::class.java)
    }

}