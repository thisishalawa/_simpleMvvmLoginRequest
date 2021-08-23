package master.simple_mvvm.login_request.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.coroutines.runBlocking
import master.simple_mvvm.login_request.data.network.UserApi
import master.simple_mvvm.login_request.data.repository.UserRepository
import master.simple_mvvm.login_request.data.response.User
import master.simple_mvvm.login_request.databinding.FragmentHomeBinding
import master.simple_mvvm.login_request.other.Resource
import master.simple_mvvm.login_request.other.visible
import master.simple_mvvm.login_request.ui.base.BaseFragment


class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, UserRepository>() {

    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): UserRepository {
        val token = runBlocking { "userPreferences.authToken.first()" }
        val api = remoteDataSource.buildApi(UserApi::class.java, token)
        return UserRepository(api)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressbar.visible(false)

        viewModel.getUser()

        viewModel.user.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    binding.progressbar.visible(false)
                    updateUI(it.value.user)
                }
                is Resource.Loading -> {
                    binding.progressbar.visible(true)
                }
            }
        })

        binding.buttonLogout.setOnClickListener {
            logout()
        }
    }

    /*
    *
    * action
    * */

    private fun updateUI(user: User) {
        with(binding) {
            textViewId.text = user.id.toString()
            textViewName.text = user.name
            textViewEmail.text = user.email
        }
    }
}