package com.example.recyclerviewapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewapp.api_services.GetUser
import com.example.recyclerviewapp.api_services.RetroHelper
import com.example.recyclerviewapp.model.UserModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        var userList : MutableList<UserModel>  = mutableListOf()
//
//        val userApi  = RetroHelper.getInstance().create(GetUser::class.java)
//
//        GlobalScope.launch {
//            userList = userApi.getUsers() as MutableList<UserModel>
//
//
//        }
//        val userList : List<UserModel> = listOf(
//            UserModel(name = "Jhon", email = "john@mail.com", avatar = "https://i.imgur.com/LDOO4Qs.jpg"),
//            UserModel(name = "Maria", email = "maria@mail.com", avatar = "https://i.imgur.com/DTfowdu.jpg"),
//            UserModel(name = "Raghad Abusnaneh", email = "raghadnewuser@test.com", avatar = "https://picsum.photos/800"),
//            UserModel(name = "Dev Nizam Uddin", email = "dev.nizamuddin@gmail.com", avatar = "https://api.escuelajs.co/api/v1/files/acd6.png"),
//            UserModel(name = "ruja", email = "ruja@gmail.com", avatar = "https://Gmail.com")
//        )

        recyclerView  = findViewById<RecyclerView>(R.id.user_recyclerView)
        // this creates a vertical layout Manager
        recyclerView.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter(mutableListOf())
        recyclerView.adapter = userAdapter//UserAdapter(userList)

        fetchUsers()

    }
    private fun fetchUsers() {
        val userApi = RetroHelper.getInstance().create(GetUser::class.java)

        lifecycleScope.launch {
            try {
                val response = userApi.getUsers()
                if (response.isSuccessful) {
                    response.body()?.let { users ->
                        userAdapter.updateUsers(users)
                    }
                } else {
                    Log.e("MainActivity", "Error: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("MainActivity", "Exception: ${e.message}")
            }
        }
    }
}