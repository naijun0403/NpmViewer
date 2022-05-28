package io.npm.viewer.repository

import androidx.lifecycle.MutableLiveData
import io.npm.viewer.model.ServiceModel
import io.npm.viewer.retrofit.RetrofitClient
import org.jsoup.nodes.Document
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object NpmRepository {

    val service = MutableLiveData<ServiceModel>()

    fun getSearchDataFromNpm(packageName: String): MutableLiveData<ServiceModel> {
        val request = RetrofitClient.npm.getSearchPackageByName(packageName)

        request.enqueue(object : Callback<Document> {

            override fun onResponse(call: Call<Document>, response: Response<Document>) {
                val data = response.body()
                val msg = data!!.html().toString()

                service.value = ServiceModel(msg)
            }

            override fun onFailure(call: Call<Document>, t: Throwable) {
                t.printStackTrace()
            }

        })

        return service
    }

}