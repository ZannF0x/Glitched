package com.zannardyapps.glitched.forms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.zannardyapps.glitched.databinding.ActivityFormRegisterBinding

class FormRegister : AppCompatActivity() {
    private lateinit var binding: ActivityFormRegisterBinding

    private lateinit var editTextEmail: EditText
    private lateinit var editTextSenha: EditText
    private lateinit var buttonEntrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        initComponentsLayout()

    }

    private fun initComponentsLayout(){
        editTextEmail = binding.editTextEmail
        editTextSenha = binding.editTextSenha
        buttonEntrar = binding.buttonEntrar
    }
}