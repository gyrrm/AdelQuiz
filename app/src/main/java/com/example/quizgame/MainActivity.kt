package com.example.quizgame

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import com.example.quizgame.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        drawerLayout = binding.drawerLayout

        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }

    fun saveDatabase(userName: String, userScore: String) {

        var db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "userDatabase"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

        val userDao = db.userDao()
        UserRepository(userDao).addUser(UserEntity(userName, userScore))
        //userDao.insert(UserEntity( userName, userScore))
    }

    fun getFromDatabaseByName(userName: String): UserEntity {

        var db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "userDatabase"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

        val userDao = db.userDao()
        return UserRepository(userDao).getUser(userName)
    }

    fun updateUserEntity(userName: String, userScore: String) {

        var db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "userDatabase"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

        val userDao = db.userDao()
        UserRepository(userDao).updateUser(userName, userScore)
    }
}