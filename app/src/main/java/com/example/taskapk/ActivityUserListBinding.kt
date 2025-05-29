/*
package com.example.taskapk

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.taskapk.data.User
import com.example.taskapk.databinding.ActivityAddEditUserBinding
import com.example.taskapk.viewmodel.UserViewModel
import kotlinx.coroutines.launch

class AddEditUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddEditUserBinding
    private val viewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as UserApplication).repository)
    }

    private var currentUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Check if we're editing an existing user
        val loginUserId = intent.getStringExtra("LOGIN_USER_ID")
        if (loginUserId != null) {
            // Load existing user data
            loadUserData(loginUserId)
        }

        binding.btnModify.setOnClickListener {
            saveOrUpdateUser()
        }

        binding.btnClose.setOnClickListener {
            finish()
        }
    }

    private fun loadUserData(loginUserId: String) {
        lifecycleScope.launch {
            val user = viewModel.getUserByLoginId(loginUserId)
            user?.let {
                currentUser = it
                populateForm(it)
            }
        }
    }

    private fun populateForm(user: User) {
        binding.etLogin.setText(user.loginUserId)
        binding.etName.setText(user.fullName)
        binding.etAddress.setText(user.location)
        binding.etMobile.setText(user.phoneNumber)
        binding.etEmail.setText(user.email)
        binding.swTab.isChecked = user.tabEnabled
        binding.swOtpVerify.isChecked = user.otpVerified
        binding.swIsActive.isChecked = user.isActive
        // Note: Designation field is not in your XML but is in your User class
    }

    private fun saveOrUpdateUser() {
        val loginUserId = binding.etLogin.text.toString().trim()
        val fullName = binding.etName.text.toString().trim()
        val phoneNumber = binding.etMobile.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val location = binding.etAddress.text.toString().trim()
        val designation = "Waterbill Clerk" // Default value since field not in XML
        val tabEnabled = binding.swTab.isChecked
        val otpVerified = binding.swOtpVerify.isChecked
        val isActive = binding.swIsActive.isChecked

        val user = User(
            id = currentUser?.id ?: 0,
            loginUserId = loginUserId,
            fullName = fullName,
            phoneNumber = phoneNumber,
            email = email,
            location = location,
            designation = designation,
            tabEnabled = tabEnabled,
            otpVerified = otpVerified,
            isActive = isActive
        )

        lifecycleScope.launch {
            val validationResult = viewModel.validateUser(user, currentUser != null)
            if (validationResult.isValid) {
                if (currentUser == null) {
                    viewModel.insert(user)
                    Toast.makeText(this@AddEditUserActivity, "User added successfully", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.update(user)
                    Toast.makeText(this@AddEditUserActivity, "User updated successfully", Toast.LENGTH_SHORT).show()
                }
                finish()
            } else {
                Toast.makeText(this@AddEditUserActivity, validationResult.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}*/

package com.example.taskapk

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.example.taskapk.data.User
import com.example.taskapk.databinding.ActivityAddEditUserBinding
import com.example.taskapk.viewmodel.UserViewModel
import kotlinx.coroutines.runBlocking

class AddEditUserActivity : ComponentActivity() {
    private lateinit var binding: ActivityAddEditUserBinding
    private val viewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as UserApplication).repository)
    }
    private var userId: Int = 0 // 0 for new user, non-zero for editing

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Populate User Level spinner
        val userLevels = arrayOf("Waterbill Clerk", "Admin", "Manager")
        binding.spinnerLevel.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            userLevels
        )

        // Auto-generate loginUserId from fullName for new users
        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (userId == 0) { // Only for new users
                    val name = s.toString().trim()
                    val generatedId = viewModel.generateLoginUserId(name)
                    binding.etLogin.setText(generatedId)
                }
            }
        })

        // Check if editing an existing user
        userId = intent.getIntExtra("USER_ID", 0)
        if (userId != 0) {
            loadUserData(userId)
            binding.etLogin.isEnabled = false // Disable Login User ID for editing
        } else {
            binding.etLogin.isEnabled = false // Disable for new users (auto-generated)
        }

        // Modify button
        binding.btnModify.setOnClickListener {
            saveUser()
        }

        // Close button
        binding.btnClose.setOnClickListener {
            finish()
        }
    }

    private fun loadUserData(userId: Int) {
        runBlocking {
            val user = viewModel.getUserByLoginId(intent.getStringExtra("LOGIN_USER_ID") ?: "")
            user?.let {
                binding.etLogin.setText(it.loginUserId)
                binding.etName.setText(it.fullName)
                binding.etAddress.setText(it.address)
                binding.etContact.setText(it.contact)
                binding.etMobile.setText(it.phoneNumber)
                binding.etEmail.setText(it.email)
                binding.etRemarks.setText(it.remarks)
                binding.swOtpVerify.isChecked = it.otpVerified
                binding.swTab.isChecked = it.tabEnabled
                binding.swIsActive.isChecked = it.isActive
                binding.spinnerLevel.setSelection(
                    (binding.spinnerLevel.adapter as ArrayAdapter<String>).getPosition(it.designation)
                )
            }
        }
    }

    private fun saveUser() {
        val user = User(
            id = userId,
            loginUserId = binding.etLogin.text.toString().trim(),
            fullName = binding.etName.text.toString().trim(),
            phoneNumber = binding.etMobile.text.toString().trim(),
            email = binding.etEmail.text.toString().trim(),
            address = binding.etAddress.text.toString().trim(),
            contact = binding.etContact.text.toString().trim(),
            remarks = binding.etRemarks.text.toString().trim(),
            otpVerified = binding.swOtpVerify.isChecked,
            tabEnabled = binding.swTab.isChecked,
            isActive = binding.swIsActive.isChecked,
            designation = binding.spinnerLevel.selectedItem.toString(),
            location = "Pune"
        )

        runBlocking {
            val validationResult = viewModel.validateUser(user, userId != 0)
            if (validationResult.isValid) {
                if (userId == 0) {
                    viewModel.insert(user)
                    Toast.makeText(this@AddEditUserActivity, "User added successfully", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.update(user)
                    Toast.makeText(this@AddEditUserActivity, "User updated successfully", Toast.LENGTH_SHORT).show()
                }
                finish()
            } else {
                binding.etLogin.error = null
                binding.etName.error = null
                binding.etAddress.error = null
                binding.etContact.error = null
                binding.etMobile.error = null
                binding.etEmail.error = null
                binding.etRemarks.error = null

                when {
                    validationResult.errorMessage.contains("Login User ID") -> binding.etLogin.error = validationResult.errorMessage
                    validationResult.errorMessage.contains("Full name") -> binding.etName.error = validationResult.errorMessage
                    validationResult.errorMessage.contains("Address") -> binding.etAddress.error = validationResult.errorMessage
                    validationResult.errorMessage.contains("Contact number") -> binding.etContact.error = validationResult.errorMessage
                    validationResult.errorMessage.contains("Mobile number") -> binding.etMobile.error = validationResult.errorMessage
                    validationResult.errorMessage.contains("Email") -> binding.etEmail.error = validationResult.errorMessage
                    validationResult.errorMessage.contains("Remarks") -> binding.etRemarks.error = validationResult.errorMessage
                }
            }
        }
    }
}
