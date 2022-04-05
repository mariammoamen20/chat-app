package com.example.chat_app.base

import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

open abstract class BaseActivity<DB : ViewDataBinding, VM : BaseViewModel<*>> : AppCompatActivity() {
    lateinit var view_data_binding: DB
    lateinit var view_model: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view_data_binding = DataBindingUtil.setContentView(this, getLayoutId())
        view_model = initViewModel()
        subscribeToLiveData()
    }

    fun subscribeToLiveData() {
        view_model.messageLiveData.observe(this) {
          showDialog(it ,"Ok")
        }

        view_model.showLoading.observe(this) {
              if(it) showLoading() else hideLoading()
        }
    }

    val defaultAction = DialogInterface.OnClickListener { dialog, _ -> dialog?.dismiss() }

    var alertDialog: AlertDialog? = null
    fun showDialog(
        message: String,
        posActionName: String? = null,
        posAction: DialogInterface.OnClickListener? = null,
        negActionName: String? = null,
        negAction: DialogInterface.OnClickListener? = null,
        cancelable: Boolean = true
    ) {
        val builder = AlertDialog.Builder(this).setMessage(message)
        if (posActionName != null) {
            builder.setPositiveButton(
                posActionName ,
                posAction ?: defaultAction
            )
        }

        if (negActionName != null) {
            builder.setPositiveButton(
                negActionName, negAction ?: defaultAction
            )
        }

        builder.setCancelable(cancelable)
        alertDialog = builder.show()
    }
   fun hideAlertDialog(){
       alertDialog?.dismiss()
       alertDialog=null
   }
   var progressDialog : ProgressDialog ?=null
   fun showLoading(){
   progressDialog =
       ProgressDialog(this)
       progressDialog?.setMessage("Loading....")
       progressDialog?.setCancelable(false)
       progressDialog?.show()
   }
    fun hideLoading(){
        progressDialog?.dismiss()
        progressDialog = null
    }
    abstract fun getLayoutId(): Int
    abstract fun initViewModel(): VM
}