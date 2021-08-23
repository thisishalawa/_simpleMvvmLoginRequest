package master.simple_mvvm.login_request.ui.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.launch
import master.simple_mvvm.login_request.other.TAG

abstract class BaseFragment<B : ViewBinding> : Fragment() {

    protected lateinit var binding: B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = getFragmentBinding(inflater, container)

        return binding.root
    }


    fun logout() = lifecycleScope.launch {
        Log.d(TAG, "logout _____ ______")

    }

    /*
    *
    * ab
    * */
    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B


}