package master.simple_mvvm.login_request

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import master.simple_mvvm.login_request.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}