package com.example.recyclerviewapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewapp.model.UserModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val userList : List<UserModel> = listOf(
            UserModel(name = "Jhon", email = "john@mail.com", avatar = "https://i.imgur.com/LDOO4Qs.jpg"),
            UserModel(name = "Maria", email = "maria@mail.com", avatar = "https://i.imgur.com/DTfowdu.jpg"),
            UserModel(name = "Raghad Abusnaneh", email = "raghadnewuser@test.com", avatar = "https://picsum.photos/800"),
            UserModel(name = "Dev Nizam Uddin", email = "dev.nizamuddin@gmail.com", avatar = "https://api.escuelajs.co/api/v1/files/acd6.png"),
            UserModel(name = "ruja", email = "ruja@gmail.com", avatar = "https://Gmail.com")
        )

        val recylerView : RecyclerView = findViewById<RecyclerView>(R.id.user_recyclerView)
        // this creates a vertical layout Manager
        recylerView.layoutManager = LinearLayoutManager(this)
        recylerView.adapter = UserAdapter(userList)



    }
}