package com.example.chat_app.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.chat_app.R
import com.example.chat_app.databinding.ItemMessageReciveBinding
import com.example.chat_app.databinding.ItemMessageSendBinding
import com.example.chat_app.model.Message
import com.example.chat_app.objects.DataUtils

class MessageAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val items = mutableListOf<Message?>()

    class SendViewHolder(
        val viewDataBinding:
        ItemMessageSendBinding
    ) : RecyclerView.ViewHolder(viewDataBinding.root) {
        fun bind(message: Message?) {
            viewDataBinding.item = message
            viewDataBinding.invalidateAll()
        }
    }

    class RecieveViewHolder(
        val viewDataBinding:
        ItemMessageReciveBinding
    ) : RecyclerView.ViewHolder(viewDataBinding.root) {
        fun bind(message: Message?) {
            viewDataBinding.item = message
            viewDataBinding.invalidateAll()
        }
    }

    val SEND = 1;
    val RECIEVE = 2;
    override fun getItemViewType(position: Int): Int {
        val message = items[position]
        if (message?.senderId == DataUtils.user?.id) {
            return SEND
        } else {
            return RECIEVE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == SEND) {
            val itemBinding: ItemMessageSendBinding =
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_message_send,
                    parent,
                    false
                )
            return SendViewHolder(itemBinding)
        }
        val itemBinding: ItemMessageReciveBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_message_recive,
                parent,
                false
            )
        return RecieveViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SendViewHolder) {
            holder.bind(items[position])
        } else if (holder is RecieveViewHolder) {
            holder.bind(items[position])
        }
        /*   val type = getItemViewType(position)
            if(type == SEND){
            holder as SendViewHolder

            }else if(type == RECIEVE){
            holder as RecieveViewHolder
            }*/
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun appendMessage(newMessageList: MutableList<Message>) {
        items.addAll(newMessageList)
        notifyItemRangeInserted(items.size + 1, newMessageList.size)
    }
}