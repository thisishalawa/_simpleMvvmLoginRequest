package master.simple_mvvm.login_request.other

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import master.simple_mvvm.login_request.ui.auth.LoginFragment
import master.simple_mvvm.login_request.ui.base.BaseFragment

fun <A : Activity> Activity.startNewActivity(activity: Class<A>) {
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.enable(enabled: Boolean) {
    isEnabled = enabled
    alpha = if (enabled) 1f else 0.5f
}

fun View.snackbar(message: String, action: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction("Retry") {
            it()
        }
    }
    snackbar.show()
}

fun Fragment.handleApiError(
    failure: Resource.Failure,
    retry: (() -> Unit)? = null
) {
    when {
        failure.isNetworkError ->
            requireView().snackbar(
                "NetworkError ..",
                retry
            )
        failure.errorCode == 401 -> {
            Log.d(
                TAG, "handleApiError: \n\n" +
                        "failure.errorCode == 401"
            )
            if (this is LoginFragment) {
                requireView().snackbar("You've entered incorrect email or password")
            } else {
                requireView().snackbar(
                    "Auto login \n " +
                            "incorrect email or password " +
                            "logout ####### and move to auth activity"
                )
                (this as BaseFragment<*, *, *>).logout()

            }
        }
        else -> {
            val error = failure.errorBody?.string().toString()
            Log.d(
                TAG, "handleApiError: \n\n" +
                        "error"
            )
            requireView().snackbar(error)
        }
    }
}

