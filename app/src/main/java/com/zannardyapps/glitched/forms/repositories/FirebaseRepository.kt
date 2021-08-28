package com.zannardyapps.glitched.forms.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class FirebaseRepository(
    private val auth: FirebaseAuth
) {

    // Login
    suspend fun loginUser(email: String, password: String): FirebaseUser {
        return auth.signInWithEmailAndPassword(email, password).await().user as FirebaseUser
    }

    // Register
    suspend fun registerUser(email: String, password: String): FirebaseUser {
        return auth.createUserWithEmailAndPassword(email, password).await().user as FirebaseUser
    }

    //logout
    fun logout(){
        auth.signOut()
    }
}