package master.simple_mvvm.login_request.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import master.simple_mvvm.login_request.R
import master.simple_mvvm.login_request.databinding.FragmentLoginBinding
import master.simple_mvvm.login_request.other.TAG
import master.simple_mvvm.login_request.other.enable
import master.simple_mvvm.login_request.other.visible
import master.simple_mvvm.login_request.ui.base.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        binding.progressbar.visible(false)
        binding.buttonLogin.enable(false)


        binding.editTextTextPassword.addTextChangedListener {
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            binding.buttonLogin.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.buttonLogin.setOnClickListener {
            login()
        }


    }

    private fun login() {
        val email = binding.editTextTextEmailAddress.text.toString().trim()
        val password = binding.editTextTextPassword.text.toString().trim()

        Log.d(TAG, "login ..  ${email}")

    }

}
