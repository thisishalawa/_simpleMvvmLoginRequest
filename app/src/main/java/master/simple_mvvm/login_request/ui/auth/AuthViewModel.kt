package master.simple_mvvm.login_request.ui.auth

import master.simple_mvvm.login_request.data.repository.AuthRepository
import master.simple_mvvm.login_request.ui.base.BaseViewModel

class AuthViewModel(
    private val repository: AuthRepository
) : BaseViewModel(repository) {



}