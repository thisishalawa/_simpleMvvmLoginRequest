package master.simple_mvvm.login_request.ui.base

import androidx.lifecycle.ViewModel
import master.simple_mvvm.login_request.data.repository.BaseRepository

abstract class BaseViewModel(
    private val repository: BaseRepository
) : ViewModel() {


}