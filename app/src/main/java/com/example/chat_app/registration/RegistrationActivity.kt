package com.example.chat_app.registration

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.chat_app.R
import com.example.chat_app.base.BaseActivity
import com.example.chat_app.databinding.ActivityRegistrationBinding
import com.example.chat_app.home.HomeActivity


class RegistrationActivity : BaseActivity<ActivityRegistrationBinding, RegistrationViewModel>(),
    Navigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view_data_binding.vm = view_model
        view_model.navigator = this

        //setContentView(R.layout.activity_registration)

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_registration
    }

    override fun initViewModel(): RegistrationViewModel {
        return ViewModelProvider(this).get(RegistrationViewModel::class.java)
    }

    override fun openHomeScreen() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }


}