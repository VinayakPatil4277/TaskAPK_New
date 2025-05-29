package com.example.taskapk.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM users ORDER BY fullName ASC")
    fun getAllUsers(): Flow<List<User>>

    @Query("SELECT * FROM users WHERE loginUserId = :loginUserId")
    suspend fun getUserByLoginId(loginUserId: String): User?

    @Query("SELECT COUNT(*) FROM users WHERE loginUserId = :loginUserId AND id != :excludeId")
    suspend fun countUsersWithLoginId(loginUserId: String, excludeId: Int = 0): Int

    @Query("SELECT COUNT(*) FROM users WHERE email = :email AND id != :excludeId")
    suspend fun countUsersWithEmail(email: String, excludeId: Int = 0): Int

    @Query("SELECT COUNT(*) FROM users WHERE phoneNumber = :phoneNumber AND id != :excludeId")
    suspend fun countUsersWithPhoneNumber(phoneNumber: String, excludeId: Int = 0): Int
}