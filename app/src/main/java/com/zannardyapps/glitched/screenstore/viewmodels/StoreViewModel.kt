package com.zannardyapps.glitched.screenstore.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.zannardyapps.glitched.forms.repositories.FirebaseRepository

class StoreViewModel(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val firebaseRepository: FirebaseRepository = FirebaseRepository(auth)
): ViewModel() {


    fun logout(){
        firebaseRepository.logout()
    }
}