package master.simple_mvvm.login_request.data.network

import master.simple_mvvm.login_request.data.response.LoginResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {

    @GET("user")
    suspend fun getUser(): LoginResponse

    @POST("logout")
    suspend fun logout(): ResponseBody

}