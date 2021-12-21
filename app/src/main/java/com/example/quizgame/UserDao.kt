package com.example.quizgame

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Query("SELECT * FROM userentity")
    fun get(): UserEntity

    @Insert
    fun insert(vararg user: UserEntity)

    @Query("SELECT * FROM userentity WHERE userName LIKE :userName")
    fun getUserByUserName(userName: String): UserEntity

    @Query("UPDATE userentity SET userName = :userName, score = :userScore WHERE userName LIKE :userName")
    fun update(userName: String, userScore: String)
}