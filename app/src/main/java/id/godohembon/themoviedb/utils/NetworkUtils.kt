package id.godohembon.themoviedb.utils

import org.json.JSONObject
import retrofit2.Response

sealed class ResourceState<out T> {
    class Loading<out T> : ResourceState<T>()
    data class Success<out T>(val data: T) : ResourceState<T>()
    data class Failure<out T>(
        val throwable: Throwable?,
        val responseCode: Int?,
        val message: String?,
        val errorJsonObj: JSONObject? = null
    ) : ResourceState<T>()
}

suspend fun <I, O> safeApiCall(
    mapper: Mapper<I, O>,
    call: suspend () -> Response<I>
): ResourceState<O> {

    var responseCode: Int? = null
    var errorMessage: String? = null
    var objError: JSONObject? = null

    try {
        val response = call.invoke()
        if (response.isSuccessful) {
            response.body()?.let {
                return ResourceState.Success(mapper.map(it))
            } ?: run {
                return ResourceState.Success(Unit as O)
            }
        }

        responseCode = response.code()

        objError = JSONObject(response.errorBody()?.string())
        errorMessage = objError.getString("error")
        return ResourceState.Failure(
            null,
            responseCode = responseCode,
            message = errorMessage,
            errorJsonObj = objError
        )
    } catch (e: Exception) {
        return ResourceState.Failure(
            throwable = e,
            responseCode = responseCode,
            message = errorMessage,
            errorJsonObj = objError
        )
    }
}