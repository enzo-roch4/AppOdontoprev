package br.com.kenzo.appodontoprev.api

import android.annotation.SuppressLint
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.auth.User

class UserRepository {
    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference.child("users")

    fun createUser(@SuppressLint("RestrictedApi") user: User, callback: (String) -> Unit) {
        val userId = database.push().key
        user.id = userId // Agora isso deve funcionar
        if (userId != null) {
            database.child(userId).setValue(user).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(userId)
                } else {
                    callback("Error: ${task.exception?.message}")
                }
            }
        }
    }

    fun getUser(userId: String, callback: (User?) -> Unit) {
        database.child(userId).get().addOnSuccessListener { dataSnapshot ->
            val user = dataSnapshot.getValue(User::class.java)
            callback(user)
        }.addOnFailureListener {
            callback(null)
        }
    }

    fun updateUser(userId: String, user: User, callback: (Boolean) -> Unit) {
        database.child(userId).setValue(user).addOnCompleteListener { task ->
            callback(task.isSuccessful)
        }
    }

    fun deleteUser(userId: String, callback: (Boolean) -> Unit) {
        database.child(userId).removeValue().addOnCompleteListener { task ->
            callback(task.isSuccessful)
        }
    }
}
