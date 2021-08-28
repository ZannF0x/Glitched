package com.zannardyapps.glitched.forms.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.zannardyapps.glitched.forms.repositories.FirebaseRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel(
    private var auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private var firebaseRepository: FirebaseRepository = FirebaseRepository(auth)
): ViewModel() {

    private val _currentUser = MutableLiveData<FirebaseUser>(auth.currentUser)
    val currentUser : LiveData<FirebaseUser> = _currentUser

    private val _error = MutableLiveData<Exception>()
    val error : LiveData<Exception> = _error

    fun loginUser(email: String, password: String){
        viewModelScope.launch {
            try {
                _currentUser.value = firebaseRepository.loginUser(email, password)
            }catch(e: Exception){
                _error.value = e
            }
        }
    }

}