package master.simple_mvvm.login_request.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import master.simple_mvvm.login_request.data.repository.AuthRepository
import master.simple_mvvm.login_request.data.response.LoginResponse
import master.simple_mvvm.login_request.other.Resource
import master.simple_mvvm.login_request.ui.base.BaseViewModel

class AuthViewModel(
    private val repository: AuthRepository
) : BaseViewModel(repository) {

    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse



    fun login(
        email: String,
        password: String
    ) = viewModelScope.launch {
        _loginResponse.value = Resource.Loading
        _loginResponse.value = repository.login(email, password)
    }


}