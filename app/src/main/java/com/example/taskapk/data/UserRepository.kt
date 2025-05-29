package com.example.taskapk.data

import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {
    val allUsers: Flow<List<User>> = userDao.getAllUsers()

    suspend fun insert(user: User) {
        userDao.insertUser(user)
    }

    suspend fun update(user: User) {
        userDao.updateUser(user)
    }

    suspend fun delete(user: User) {
        userDao.deleteUser(user)
    }

    suspend fun getUserByLoginId(loginUserId: String): User? {
        return userDao.getUserByLoginId(loginUserId)
    }

    suspend fun isLoginUserIdUnique(loginUserId: String, excludeId: Int = 0): Boolean {
        return userDao.countUsersWithLoginId(loginUserId, excludeId) == 0
    }

    suspend fun isEmailUnique(email: String, excludeId: Int = 0): Boolean {
        return userDao.countUsersWithEmail(email, excludeId) == 0
    }

    suspend fun isPhoneNumberUnique(phoneNumber: String, excludeId: Int = 0): Boolean {
        return userDao.countUsersWithPhoneNumber(phoneNumber, excludeId) == 0
    }
}