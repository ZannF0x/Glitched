package com.zannardyapps.glitched.forms

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.zannardyapps.glitched.databinding.ActivityFormRegisterBinding
import com.zannardyapps.glitched.forms.viewmodels.RegisterViewModel

class FormRegister : AppCompatActivity() {
    private lateinit var binding: ActivityFormRegisterBinding

    private lateinit var editTextEmail: EditText
    private lateinit var editTextSenha: EditText
    private lateinit var buttonEntrar: Button

    private val viewModel: RegisterViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        initComponentsLayout()
        observeLiveData()

        buttonEntrar.setOnClickListener {

            when {

                editTextEmail.text.isEmpty() -> {
                    editTextEmail.error = "Erro! Preencha o campo de E-mail."
                    if (editTextEmail.text.isNotEmpty()) editTextEmail.error = null
                }

                editTextSenha.text.isEmpty() -> {
                    editTextSenha.error = "Erro! Preencha o campo de Senha."
                    if (editTextSenha.text.isNotEmpty()) editTextSenha.error = null
                }

                else -> {
                    viewModel.registerUser(
                        editTextEmail.text.toString(),
                        editTextSenha.text.toString()
                    )

                }
            }

        }

    }

    private fun observeLiveData(){
        var message: String = ""

        // DEU BOM
        viewModel.currentUser.observe(this){ currentUser ->
            message = "Usuário Cadastrado com sucesso!"

            Snackbar.make(binding.root, message, Snackbar.LENGTH_INDEFINITE)
                .setBackgroundTint(Color.WHITE)
                .setTextColor(Color.BLACK).setAction("Ok"){
                    onBackPressed()
                }
                .show()
        }

        // DEU RUIM
        viewModel.error.observe(this){ exception ->

            when (exception) {
                is FirebaseAuthWeakPasswordException -> {
                    message = "Erro! Digite uma senha com no mínimo 6 digitos."
                }
                is FirebaseNetworkException -> {
                    message = "Erro! Verifique sua conexão com a Internet."

                }
                is FirebaseAuthUserCollisionException -> {
                    message = "Erro! E-mail ou Senha já existente."
                    //
                }
                else -> {
                    message = "Erro ao cadastrar Usuário."

                }
            }
            Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
                .setBackgroundTint(Color.WHITE)
                .setTextColor(Color.BLACK).show()
        }
    }

    private fun initComponentsLayout(){
        editTextEmail = binding.editTextEmail
        editTextSenha = binding.editTextSenha
        buttonEntrar = binding.buttonEntrar
    }
}