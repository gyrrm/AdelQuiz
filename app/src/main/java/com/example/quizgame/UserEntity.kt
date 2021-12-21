package com.example.quizgame

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class UserEntity (
    @PrimaryKey val userName: String,
    @ColumnInfo(name = "score") val userScore: String,
        )