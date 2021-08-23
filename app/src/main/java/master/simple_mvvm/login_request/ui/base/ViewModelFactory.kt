package master.simple_mvvm.login_request.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import master.simple_mvvm.login_request.data.repository.AuthRepository
import master.simple_mvvm.login_request.data.repository.BaseRepository
import master.simple_mvvm.login_request.data.repository.UserRepository
import master.simple_mvvm.login_request.ui.auth.AuthViewModel
import master.simple_mvvm.login_request.ui.home.HomeViewModel

class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as UserRepository) as T

            else -> throw IllegalArgumentException("ViewModelClass Not Found")


        }


    }
}