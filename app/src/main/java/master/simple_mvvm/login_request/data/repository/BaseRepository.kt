package master.simple_mvvm.login_request.data.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import master.simple_mvvm.login_request.data.network.UserApi
import master.simple_mvvm.login_request.other.Resource
import master.simple_mvvm.login_request.other.TAG
import retrofit2.HttpException

abstract class BaseRepository {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        Resource.Failure(false, throwable.code(), throwable.response()?.errorBody())
                    }
                    else -> {
                        Resource.Failure(true, null, null)
                    }
                }
            }
        }
    }

    suspend fun logout(api: UserApi) = safeApiCall {
        Log.d(TAG, "BaseRepository _ logout ...  ")
        api.logout()
    }
}