package com.zannardyapps.glitched.screenstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
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

    // menu methods
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //return super.onCreateOptionsMenu(menu)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_storescreen, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //return super.onOptionsItemSelected(item)
        return when(item.itemId){
            R.id.configSettings -> {
                Toast.makeText(this, "Configurações...", Toast.LENGTH_LONG).show()
                true
            }
            R.id.configExit -> {
                returnFormLogin()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // method to return FormLogin
    private fun returnFormLogin(){
        viewModel.logout()
        val intent = Intent(this, FormLogin::class.java)
        startActivity(intent)
        finish()
    }
}