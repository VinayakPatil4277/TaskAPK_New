/*
package com.example.taskapk

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapk.data.User
import com.example.taskapk.databinding.ItemUserBinding

class UserListAdapter : ListAdapter<User, UserListAdapter.UserViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.tvName.text = user.fullName
            binding.tvMobile.text = user.email
            binding.tvMobile.text = if (user.isActive) "Active" else "Inactive"
        }
    }

    class UserDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
}*/

package com.example.taskapk

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapk.data.User
import com.example.taskapk.databinding.ItemUserListBinding
import android.widget.Toast

class UserListAdapter(
    private val onEditClick: (User) -> Unit,
    private val onDeleteClick: (User) -> Unit
) : ListAdapter<User, UserListAdapter.UserViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class UserViewHolder(private val binding: ItemUserListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.tvLoginId.text = user.loginUserId
            binding.tvName.text = user.fullName
            binding.tvMobile.text = user.phoneNumber
            binding.tvTaxNo.text = user.transferMatter
            binding.tbTabEnable.isChecked = user.tabEnabled
            binding.tbOtpVerify.isChecked = user.otpVerified
            binding.tbIsActive.isChecked = user.isActive

            binding.btnEdit.setOnClickListener {
                onEditClick(user)
            }
            binding.btnDelete.setOnClickListener {
                onDeleteClick(user)
                Toast.makeText(binding.root.context, "User ${user.fullName} deleted", Toast.LENGTH_SHORT).show()
            }
            binding.btnPermissions.setOnClickListener {
                Toast.makeText(binding.root.context, "Permissions for ${user.loginUserId}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    class UserDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
}
