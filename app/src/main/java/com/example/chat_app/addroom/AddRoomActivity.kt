package com.example.chat_app.addroom

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import com.example.chat_app.R
import com.example.chat_app.base.BaseActivity
import com.example.chat_app.databinding.ActivityAddRoomBinding
import com.example.chat_app.model.Category

class AddRoomActivity : BaseActivity<ActivityAddRoomBinding, AddRoomViewModel>(), Navigator {
    lateinit var categorySpinnerAdapter: CategoriesSpinnerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view_data_binding.vm = view_model
        view_model.navigator = this

        categorySpinnerAdapter = CategoriesSpinnerAdapter(view_model.categoryList)
        view_data_binding.spinner.adapter = categorySpinnerAdapter
        view_data_binding.spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
          view_model.selectedCategory = view_model.categoryList[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
        view_model.addRoom.observe(this) {
                   if(it){
                       showDialog("Room Added Successfully",
                       posActionName = "OK",
                           posAction = DialogInterface.OnClickListener{
                               dialog, which ->
                               dialog.dismiss()
                               finish()   //finish el activity
                           },
                           cancelable = false // user can't cancel dialog
                           )
                   }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_add_room
    }

    override fun initViewModel(): AddRoomViewModel {
        return ViewModelProvider(this).get(AddRoomViewModel::class.java)
    }
}