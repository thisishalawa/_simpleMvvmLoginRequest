package master.simple_mvvm.login_request.data.repository

import master.simple_mvvm.login_request.data.network.UserApi

class UserRepository(
    private val api: UserApi
) : BaseRepository() {

    suspend fun getUser() = safeApiCall {
        api.getUser()
    }

}