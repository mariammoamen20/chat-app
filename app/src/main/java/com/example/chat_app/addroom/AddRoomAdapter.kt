package com.example.chat_app.addroom

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.chat_app.R
import com.example.chat_app.databinding.RoomItemBinding
import com.example.chat_app.generated.callback.OnClickListener
import com.example.chat_app.model.Room


class AddRoomAdapter(var listItem : List<Room?>?) : RecyclerView.Adapter<AddRoomAdapter.AddRoomViewHolder>() {
    class AddRoomViewHolder(val view_data_binding: RoomItemBinding) :
        RecyclerView.ViewHolder(view_data_binding.root) {
               fun bind(room:Room?){
                   view_data_binding.item = room
                   view_data_binding.invalidateAll()
               }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddRoomViewHolder {
     val roomItemBinding:RoomItemBinding =
         DataBindingUtil.inflate(
             LayoutInflater.from(parent.context),
             R.layout.room_item,
             parent,
             false
         )
        return AddRoomViewHolder(roomItemBinding)
    }

    override fun onBindViewHolder(holder: AddRoomViewHolder, position: Int) {
        holder.bind(listItem!![position])
        onItemClickListener.let {
            holder.itemView.setOnClickListener {
                onItemClickListener?.onClickItem(position,listItem!![position]!!)
            }
        }
    }

    override fun getItemCount(): Int {
        return listItem?.size ?: 0
    }
    fun changeData(listRoom : List<Room>){
        this.listItem = listRoom
        notifyDataSetChanged()
    }
    var onItemClickListener: OnItemCLickListener?=null
    interface OnItemCLickListener{
        fun onClickItem(position: Int , room:Room)
    }
}