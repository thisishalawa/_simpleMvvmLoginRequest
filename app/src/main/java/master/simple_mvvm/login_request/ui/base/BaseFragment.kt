package master.simple_mvvm.login_request.ui.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.launch
import master.simple_mvvm.login_request.MainActivity
import master.simple_mvvm.login_request.data.network.RemoteDataSource
import master.simple_mvvm.login_request.data.network.UserApi
import master.simple_mvvm.login_request.data.repository.BaseRepository
import master.simple_mvvm.login_request.other.TAG
import master.simple_mvvm.login_request.other.startNewActivity

abstract class BaseFragment<
        VM : BaseViewModel,
        B : ViewBinding,
        R : BaseRepository>
    : Fragment() {

    protected lateinit var viewModel: VM
    protected lateinit var binding: B
    protected val remoteDataSource = RemoteDataSource()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = getFragmentBinding(inflater, container)
        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this, factory).get(getViewModel())
        return binding.root

    }


    fun logout() = lifecycleScope.launch {
        Log.d(TAG, "logout _____ ______")
        Log.d(TAG, "logout , delete database and get out\n" +
                "requireActivity().startNewActivity(AuthActivity::class.java)")

        val authToken = " get it from database .. "

        val api = remoteDataSource.buildApi(UserApi::class.java, authToken)
        viewModel.logout(api)
        requireActivity().startNewActivity(MainActivity::class.java)

    }

    /*
    *
    * ab
    * */
    abstract fun getViewModel(): Class<VM>

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B

    abstract fun getFragmentRepository(): R

}