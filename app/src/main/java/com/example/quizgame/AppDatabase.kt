package com.example.quizgame

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 3)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
}