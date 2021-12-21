package com.example.quizgame

class UserRepository(private val userDao: UserDao) {

    fun addUser(userEntity: UserEntity) {
        userDao.insert(userEntity)
    }

    fun updateUser(userName: String, userScore: String) {
        userDao.update(userName, userScore)
    }

    fun getUser(userName: String): UserEntity{
        return userDao.getUserByUserName(userName)
    }
}