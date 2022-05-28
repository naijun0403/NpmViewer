package io.npm.viewer.retrofit.converter

import okhttp3.ResponseBody
import org.jsoup.nodes.Document
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type


class HtmlConverterFactory private constructor(private val baseUrl: String) : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type?,
        annotations: Array<Annotation?>?, retrofit: Retrofit?
    ): Converter<ResponseBody, Document> {
        return HtmlResponseBodyConverter(baseUrl)
    }

    companion object {
        fun create(baseUrl: String): HtmlConverterFactory {
            return HtmlConverterFactory(baseUrl)
        }
    }
}