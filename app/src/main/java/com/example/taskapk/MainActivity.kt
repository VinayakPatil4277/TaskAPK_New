package com.example.taskapk

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskapk.data.UserRepository
import com.example.taskapk.databinding.ActivityUserListBinding
import com.example.taskapk.viewmodel.UserViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/*class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityUserListBinding
    private val viewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as UserApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup RecyclerView
        val adapter = UserListAdapter()
        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = adapter
        }

        // In onCreate()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.allUsers.collect { users ->
                    adapter.submitList(users)
                }
            }
        }

        binding.headerUser.btnAddNew.setOnClickListener {
            val intent = Intent(this, AddEditUserActivity::class.java)
            startActivity(intent)
        }
    }
}*/
class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityUserListBinding
    private val viewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as UserApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup RecyclerView
        val adapter = UserListAdapter(
            onEditClick = { user ->
                val intent = Intent(this, AddEditUserActivity::class.java).apply {
                    putExtra("USER_ID", user.id)
                    putExtra("LOGIN_USER_ID", user.loginUserId)
                }
                startActivity(intent)
            },
            onDeleteClick = { user ->
                viewModel.delete(user)
            }
        )
        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = adapter
        }

        // Observe users
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.allUsers.collect { users ->
                    adapter.submitList(users)
                }
            }
        }

        // Add New button
        binding.headerUser.btnAddNew.setOnClickListener {
            val intent = Intent(this, AddEditUserActivity::class.java)
            startActivity(intent)
        }
    }
}

class UserViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}