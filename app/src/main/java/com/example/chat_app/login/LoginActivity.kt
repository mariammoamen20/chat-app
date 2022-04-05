package com.example.chat_app.login

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.chat_app.R
import com.example.chat_app.base.BaseActivity
import com.example.chat_app.databinding.ActivityLoginBinding
import com.example.chat_app.home.HomeActivity
import com.example.chat_app.registration.RegistrationActivity

class LoginActivity : BaseActivity<ActivityLoginBinding,LoginViewModel>(),Navigator {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view_data_binding.vm=view_model
        view_model.navigator = this

}

    override fun getLayoutId(): Int {
      return R.layout.activity_login
    }

    override fun initViewModel(): LoginViewModel {
        return ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun openHomeScreen() {
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }

    override fun openRegistrationScreen() {
        val intent = Intent(this,RegistrationActivity::class.java)
        startActivity(intent)    }
}