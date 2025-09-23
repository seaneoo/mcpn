package dev.seano.mcpn.network

import android.util.Log
import dev.seano.mcpn.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.http.isSuccess
import io.ktor.http.userAgent
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Single

@Single
class NetworkService {
    private val jsonConfig = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    private val userAgent =
        "${BuildConfig.APPLICATION_ID}/${BuildConfig.VERSION_NAME} (${BuildConfig.REPO}) ${System.getProperty("http.agent")}"

    private val httpClient: HttpClient by lazy {
        HttpClient(CIO) {
            install(ContentNegotiation) { json(jsonConfig) }
            install(Logging) {
                level = LogLevel.HEADERS
                logger =
                    object : Logger {
                        override fun log(message: String) {
                            Log.i("NetworkService", message)
                        }
                    }
            }
            defaultRequest { userAgent(userAgent) }
        }
    }

    internal suspend inline fun <reified T> get(
        urlString: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): T? {
        return try {
            val response = httpClient.get(urlString, block)
            if (!response.status.isSuccess()) null
            response.body<T>()
        } catch (_: Exception) {
            null
        }
    }
}
