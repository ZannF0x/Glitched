package com.zannardyapps.glitched.forms.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.zannardyapps.glitched.forms.repositories.FirebaseRepository
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val firebaseRepository: FirebaseRepository = FirebaseRepository(auth)
): ViewModel() {

    private val _currentUser =  MutableLiveData<FirebaseUser>()
    val currentUser: LiveData<FirebaseUser> = _currentUser

    private val _error =  MutableLiveData<Exception>()
    val error: LiveData<Exception> = _error

    fun registerUser(email: String, password: String){
        viewModelScope.launch {
            try{
                _currentUser.value = firebaseRepository.registerUser(email, password)
                auth.signOut()
            }catch(e: Exception){
                _error.value = e
            }
        }
    }
}