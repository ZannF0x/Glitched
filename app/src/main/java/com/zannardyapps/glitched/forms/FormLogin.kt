package com.zannardyapps.glitched.forms

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.zannardyapps.glitched.R
import com.zannardyapps.glitched.databinding.ActivityFormLoginBinding
import com.zannardyapps.glitched.datastore.DataStoreMenager
import com.zannardyapps.glitched.forms.viewmodels.LoginViewModel
import com.zannardyapps.glitched.screenstore.StoreScreen
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FormLogin : AppCompatActivity() {
    private lateinit var binding: ActivityFormLoginBinding

    private lateinit var editTextEmail: EditText
    private lateinit var editTextSenha: EditText
    private lateinit var buttonEntrar: Button
    private lateinit var textViewCadastre: TextView

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        initComponentsLayout()
        observeLiveData()

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
                    viewModel.loginUser(
                        editTextEmail.text.toString(),
                        editTextSenha.text.toString())

                    //Save Note
                   GlobalScope.launch {
                       DataStoreMenager.saveNote(
                           this@FormLogin,
                           "email",
                           editTextEmail.text.toString())
                   }
                }

            }

        }

        textViewCadastre.setOnClickListener {
            openFormRegister()
        }
    }

    // Method Observe Livedata

    private fun observeLiveData(){
        var message: String = ""
        // LOGADO
        viewModel.currentUser.observe(this){ currentUser ->
            if (currentUser != null){
                initAlertDialog {
                    openStoreScreen()
                }
            }
        }
        // ERRO
        viewModel.error.observe(this){ error ->
            message = when(error){
                is FirebaseAuthInvalidCredentialsException -> {
                    "Senha ou Email invalidos!"
                }
                is FirebaseNetworkException -> {
                    "Erro! Verifique sua conexão com a Internet."
                }
                else -> {
                    "Erro ao Logar usuário."
                }
            }

            Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
                .setBackgroundTint(Color.WHITE)
                .setTextColor(Color.BLACK).show()
        }
    }

    // Method Custom AlertDialog

    private fun initAlertDialog(action: () -> Unit ){
        val view: View = View.inflate(this, R.layout.dialog_view_windows, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(view)
        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val buttonEntrar: Button = view.findViewById(R.id.buttonDialogEntrar)
        val buttonCancelar: Button = view.findViewById(R.id.buttonDialogCancelar)
        val textEmailSaved: TextView = view.findViewById(R.id.emailLogado)

        GlobalScope.launch(Dispatchers.IO) {
            val noteValueSaved = DataStoreMenager.getToNote(this@FormLogin, "email")
            GlobalScope.launch(Dispatchers.Main) {
                textEmailSaved.text = noteValueSaved.toString()
            }
        }

        buttonEntrar.setOnClickListener {
            action()
        }
        buttonCancelar.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun initComponentsLayout(){
        editTextEmail = binding.editTextEmail
        editTextSenha = binding.editTextSenha
        buttonEntrar = binding.buttonEntrar
        textViewCadastre = binding.textCadastrese
    }

    //methods open Activities

    private fun openFormRegister(){
        val intent = Intent(this, FormRegister::class.java)
        startActivity(intent)
    }

    private fun openStoreScreen(){
        val intent = Intent(this, StoreScreen::class.java)
        startActivity(intent)
        finish()
    }
}