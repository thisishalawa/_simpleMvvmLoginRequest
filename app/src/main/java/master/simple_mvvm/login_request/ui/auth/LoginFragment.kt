package master.simple_mvvm.login_request.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import master.simple_mvvm.login_request.R
import master.simple_mvvm.login_request.data.network.AuthApi
import master.simple_mvvm.login_request.data.repository.AuthRepository
import master.simple_mvvm.login_request.databinding.FragmentLoginBinding
import master.simple_mvvm.login_request.other.*
import master.simple_mvvm.login_request.other.TAG
import master.simple_mvvm.login_request.ui.base.BaseFragment
import master.simple_mvvm.login_request.ui.home.HomeActivity

class LoginFragment : BaseFragment<
        AuthViewModel,
        FragmentLoginBinding,
        AuthRepository>() {

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java))

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        binding.progressbar.visible(false)
        binding.buttonLogin.enable(false)

        binding.buttonLogin.setOnClickListener {
            login()
        }
        binding.editTextTextPassword.addTextChangedListener {
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            binding.buttonLogin.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.progressbar.visible(it is Resource.Loading)

            when (it) {
                is Resource.Success -> {
                    Log.d(
                        TAG, "onActivityCreated: " +
                                "Login Success ... \n\n you can also -> \n\n" +
                                "lifecycleScope.launch {" +
                                "viewModel.saveAuthToken(it.value.user.access_token!!)}\n==="
                    )

                    moveToHome()

                }
                is Resource.Failure -> handleApiError(it) {
                   // login()
                    moveToHome()
                }
                Resource.Loading ->
                    Log.d(TAG, "onActivityCreated: loading..")
            }
        })


    }

    private fun login() {
        val email = binding.editTextTextEmailAddress.text.toString().trim()
        val password = binding.editTextTextPassword.text.toString().trim()

        Log.d(TAG, "login ..  ${email}")
        viewModel.login(email, password)

    }

    private fun moveToHome() {
        requireActivity().startNewActivity(HomeActivity::class.java)

    }

}
