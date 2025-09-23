package dev.seano.mcpn.network

sealed class NetworkResponse<T> {
    data class Success<T>(val data: T) : NetworkResponse<T>()

    data class Failure<T>(val error: Throwable) : NetworkResponse<T>()

    class Loading<Nothing> : NetworkResponse<Nothing>()
}
