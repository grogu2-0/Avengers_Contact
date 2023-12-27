package com.example.activitylifecycle

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

lateinit var etMobileNumber: EditText
lateinit var etPassword: EditText
lateinit var btnLogin: Button
lateinit var tvForgetPassword: TextView
lateinit var tvRegister: TextView
val validMobileNumber = "12345678"
val validPassword = arrayOf("thor", "bruce","steve","thanos")

lateinit var sharedPreferences: SharedPreferences

class loginActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        var isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        setContentView(R.layout.activity_login)
        if(isLoggedIn){
            var intent = Intent(this@loginActivity,AvengersActivity::class.java)
            startActivity(intent)
            finish()
        }


        etMobileNumber = findViewById(R.id.etMobileNumber)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvForgetPassword = findViewById(R.id.tvForgetPassword)
        tvRegister = findViewById(R.id.tvRegisterYourself)



        btnLogin.setOnClickListener{

            val mobileNumber = etMobileNumber.text.toString()
            val password = etPassword.text.toString()

            if (mobileNumber == validMobileNumber && validPassword.contains(password)){
                savePreferences()
                var intent = Intent(this@loginActivity,AvengersActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this@loginActivity,"Incorrect Credentials",Toast.LENGTH_LONG).show()
            }

        }


    }
    override fun onPause(){
        super.onPause()
        finish()
    }

    fun savePreferences(){
        sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
    }
}