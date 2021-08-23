package master.simple_mvvm.login_request.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import master.simple_mvvm.login_request.data.network.UserApi
import master.simple_mvvm.login_request.data.repository.BaseRepository

abstract class BaseViewModel(
    private val repository: BaseRepository
) : ViewModel() {


    suspend fun logout(api: UserApi) = withContext(Dispatchers.IO) { repository.logout(api) }



}