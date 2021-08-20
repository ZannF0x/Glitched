package com.zannardyapps.glitched.forms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.zannardyapps.glitched.databinding.ActivityFormLoginBinding

class FormLogin : AppCompatActivity() {
    private lateinit var binding: ActivityFormLoginBinding

    private lateinit var editTextEmail: EditText
    private lateinit var editTextSenha: EditText
    private lateinit var buttonEntrar: Button
    private lateinit var textViewCadastre: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        initComponentsLayout()

        buttonEntrar.setOnClickListener {

            when{

                editTextEmail.text.isEmpty() -> {
                    editTextEmail.error = "Erro! Preencha o campo de E-mail."
                    if (editTextEmail.text.isNotEmpty()) editTextEmail.error = null
                }

                editTextSenha.text.isEmpty() -> {
                    editTextSenha.error = "Erro! Preencha o campo de Senha."
                    if (editTextSenha.text.isNotEmpty()) editTextSenha.error = null
                }

                else -> {
                    // TODO: 20/08/2021 Implementer FirebaseAuth
                }

            }

        }

        textViewCadastre.setOnClickListener {
            openFormRegister()
        }
    }

    private fun initComponentsLayout(){
        editTextEmail = binding.editTextEmail
        editTextSenha = binding.editTextSenha
        buttonEntrar = binding.buttonEntrar
        textViewCadastre = binding.textCadastrese
    }

    private fun openFormRegister(){
        val intent = Intent(this, FormRegister::class.java)
        startActivity(intent)
    }
}