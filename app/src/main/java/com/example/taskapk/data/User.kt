package com.example.taskapk.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val loginUserId: String,
    val fullName: String,
    val phoneNumber: String,
    val email: String,
    val address: String = "", // Added for form
    val contact: String = "", // Added for form
    val remarks: String = "", // Added for form
    val transferMatter: String = "NA",
    val tabEnabled: Boolean = false,
    val otpVerified: Boolean = false,
    val isActive: Boolean = true,
    val designation: String = "Waterbill Clerk",
    val location: String = "Pune"
)