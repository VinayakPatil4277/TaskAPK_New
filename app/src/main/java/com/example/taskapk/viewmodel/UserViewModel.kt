/*
package com.example.taskapk.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapk.data.User
import com.example.taskapk.data.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    val allUsers: Flow<List<User>> = repository.allUsers

    fun insert(user: User) = viewModelScope.launch {
        repository.insert(user)
    }

    fun update(user: User) = viewModelScope.launch {
        repository.update(user)
    }

    fun delete(user: User) = viewModelScope.launch {
        repository.delete(user)
    }

    suspend fun getUserByLoginId(loginUserId: String): User? {
        return repository.getUserByLoginId(loginUserId)
    }

    suspend fun validateUser(user: User, isUpdate: Boolean = false): ValidationResult {
        val excludeId = if (isUpdate) user.id else 0

        if (user.loginUserId.isBlank()) {
            return ValidationResult(false, "Login User ID is required")
        }

        if (!repository.isLoginUserIdUnique(user.loginUserId, excludeId)) {
            return ValidationResult(false, "Login User ID already exists")
        }

        if (user.fullName.isBlank()) {
            return ValidationResult(false, "Full name is required")
        }

        if (user.phoneNumber.isBlank()) {
            return ValidationResult(false, "Phone number is required")
        }

        if (!isValidPhoneNumber(user.phoneNumber)) {
            return ValidationResult(false, "Invalid phone number format")
        }

        if (!repository.isPhoneNumberUnique(user.phoneNumber, excludeId)) {
            return ValidationResult(false, "Phone number already exists")
        }

        if (user.email.isBlank()) {
            return ValidationResult(false, "Email is required")
        }

        if (!isValidEmail(user.email)) {
            return ValidationResult(false, "Invalid email format")
        }

        if (!repository.isEmailUnique(user.email, excludeId)) {
            return ValidationResult(false, "Email already exists")
        }

        return ValidationResult(true, "")
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
        return email.matches(emailRegex.toRegex())
    }

    private fun isValidPhoneNumber(phoneNumber: String): Boolean {
        val phoneRegex = "^[0-9]{10}\$"
        return phoneNumber.matches(phoneRegex.toRegex())
    }
}

data class ValidationResult(val isValid: Boolean, val errorMessage: String)*/

package com.example.taskapk.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapk.data.User
import com.example.taskapk.data.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    val allUsers: Flow<List<User>> = repository.allUsers

    fun insert(user: User) = viewModelScope.launch {
        repository.insert(user)
    }

    fun update(user: User) = viewModelScope.launch {
        repository.update(user)
    }

    fun delete(user: User) = viewModelScope.launch {
        repository.delete(user)
    }

    suspend fun getUserByLoginId(loginUserId: String): User? {
        return repository.getUserByLoginId(loginUserId)
    }

    fun generateLoginUserId(fullName: String): String {
        if (fullName.isBlank()) return ""
        val parts = fullName.trim().split(" ")
        val firstName = parts.first().lowercase()
        val lastName = parts.getOrNull(1)?.first()?.lowercase() ?: ""
        return "$firstName.$lastName"
    }

    suspend fun validateUser(user: User, isUpdate: Boolean = false): ValidationResult {
        val excludeId = if (isUpdate) user.id else 0

        if (user.loginUserId.isBlank()) {
            return ValidationResult(false, "Login User ID is required")
        }

        if (!repository.isLoginUserIdUnique(user.loginUserId, excludeId)) {
            return ValidationResult(false, "Login User ID already exists")
        }

        if (user.fullName.isBlank()) {
            return ValidationResult(false, "Full name is required")
        }

        if (user.address.isBlank()) {
            return ValidationResult(false, "Address is required")
        }

        if (user.contact.isBlank()) {
            return ValidationResult(false, "Contact number is required")
        }

        if (!isValidPhoneNumber(user.contact)) {
            return ValidationResult(false, "Invalid contact number format")
        }

        if (user.phoneNumber.isBlank()) {
            return ValidationResult(false, "Mobile number is required")
        }

        if (!isValidPhoneNumber(user.phoneNumber)) {
            return ValidationResult(false, "Invalid mobile number format")
        }

        if (!repository.isPhoneNumberUnique(user.phoneNumber, excludeId)) {
            return ValidationResult(false, "Mobile number already exists")
        }

        if (user.email.isBlank()) {
            return ValidationResult(false, "Email is required")
        }

        if (!isValidEmail(user.email)) {
            return ValidationResult(false, "Invalid email format")
        }

        if (!repository.isEmailUnique(user.email, excludeId)) {
            return ValidationResult(false, "Email already exists")
        }

        if (user.remarks.isBlank()) {
            return ValidationResult(false, "Remarks are required")
        }

        return ValidationResult(true, "")
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
        return email.matches(emailRegex.toRegex())
    }

    private fun isValidPhoneNumber(phoneNumber: String): Boolean {
        val phoneRegex = "^[0-9]{10}\$"
        return phoneNumber.matches(phoneRegex.toRegex())
    }
}

data class ValidationResult(val isValid: Boolean, val errorMessage: String)
