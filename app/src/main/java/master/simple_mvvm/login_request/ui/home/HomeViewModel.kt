package master.simple_mvvm.login_request.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import master.simple_mvvm.login_request.data.repository.UserRepository
import master.simple_mvvm.login_request.data.response.LoginResponse
import master.simple_mvvm.login_request.other.Resource
import master.simple_mvvm.login_request.ui.base.BaseViewModel

class HomeViewModel(
    private val repository: UserRepository
) : BaseViewModel(repository) {


    private val _user: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val user: LiveData<Resource<LoginResponse>>
        get() = _user

    fun getUser() = viewModelScope.launch {
        _user.value = Resource.Loading
        _user.value = repository.getUser()
    }
}