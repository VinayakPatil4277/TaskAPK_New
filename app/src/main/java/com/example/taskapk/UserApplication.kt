package com.example.taskapk

import android.app.Application
import com.example.taskapk.data.AppDatabase
import com.example.taskapk.data.UserRepository

class UserApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { UserRepository(database.userDao()) }
}