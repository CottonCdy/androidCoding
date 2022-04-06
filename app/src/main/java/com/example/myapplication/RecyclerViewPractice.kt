package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewPractice : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_practice)

        val chatList = mutableListOf<Chat>()

        chatList.add(Chat("안녕하세요", false))
        chatList.add(Chat("네 안녕하세요", true))
        chatList.add(Chat("반갑습니다", false))
        chatList.add(Chat("네 반갑습니다", true))
        chatList.add(Chat("안녕히 주무세요", false))
        chatList.add(Chat("네 안녕히 주무세요", false))

        val adapter = ChatRecyclerAdapter(chatList, layoutInflater)

        with(findViewById<RecyclerView>(R.id.chatRecyclerView)) {
            this.layoutManager = LinearLayoutManager(this@RecyclerViewPractice)
            this.adapter = adapter
        }

        (findViewById<Button>(R.id.button)).setOnClickListener {
            adapter.chatList.add(2, Chat("추가된 버전!!", false))
            adapter.notifyItemInserted(2)
        }

    }
}

class ChatRecyclerAdapter(
    var chatList: MutableList<Chat>,
    var inflater: LayoutInflater
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        when (chatList.get(position).is_right) {
            true -> return 0
            false -> return 1
        }
    }

    inner class LeftViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val chatContent = itemView.findViewById<TextView>(R.id.chatTextLeft)
    }

    inner class RightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val chatContent = itemView.findViewById<TextView>(R.id.chatTextRight)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            1 -> {
                val view = inflater.inflate(R.layout.chat_item01, parent, false)
                return LeftViewHolder(view)
            }
            else -> {
                val view = inflater.inflate(R.layout.chat_item02, parent, false)
                return RightViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chat = chatList.get(position)
        when (chat.is_right) {
            true -> (holder as RightViewHolder).chatContent.text = chat.message
            false -> (holder as LeftViewHolder).chatContent.text = chat.message
        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }
}

class Chat(
    val message: String,
    val is_right: Boolean
)