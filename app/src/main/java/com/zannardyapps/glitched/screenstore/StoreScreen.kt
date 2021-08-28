package com.zannardyapps.glitched.screenstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.zannardyapps.glitched.R
import com.zannardyapps.glitched.forms.FormLogin
import com.zannardyapps.glitched.screenstore.viewmodels.StoreViewModel

class StoreScreen : AppCompatActivity() {

    private val viewModel: StoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_screen)


    }

    private fun returnFormLogin(){
        viewModel.logout()
        val intent = Intent(this, FormLogin::class.java)
        startActivity(intent)
        finish()
    }
}