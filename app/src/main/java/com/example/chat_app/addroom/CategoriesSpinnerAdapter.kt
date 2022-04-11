package com.example.chat_app.addroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.chat_app.R
import com.example.chat_app.model.Category

class CategoriesSpinnerAdapter(val catList: List<Category>) : BaseAdapter() {
    override fun getCount(): Int {
        return catList.size
    }

    override fun getItem(position: Int): Any {
        return catList[position]
    }


    override fun getItemId(position: Int): Long {
        return 0;
    }

    override fun getView(position: Int, view: View?, container: ViewGroup?): View {
        var myView = view
        val categoryViewHolder : CategoryViewHolder
        if (myView == null) {
            myView = LayoutInflater.from(container!!.context)
                .inflate(R.layout.category_item, container, false)
            //find view by id
            categoryViewHolder = CategoryViewHolder(myView) //initialize category view holder
            //save view holder
            myView.tag = categoryViewHolder
        }else {
            categoryViewHolder  = myView.tag as CategoryViewHolder
        }
val item  = catList[position]
        categoryViewHolder.title.setText(item.name)
        return myView!!
    }

    class CategoryViewHolder(val view : View){
        val title : TextView = view.findViewById(R.id.spinner_title)
    }
}