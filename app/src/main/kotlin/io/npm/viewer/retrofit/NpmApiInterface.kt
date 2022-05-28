package io.npm.viewer.retrofit

import io.npm.viewer.model.ServiceModel
import org.jsoup.nodes.Document
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NpmApiInterface {

    @GET("search")
    fun getSearchPackageByName(@Query("q") packageName: String): Call<Document>

}