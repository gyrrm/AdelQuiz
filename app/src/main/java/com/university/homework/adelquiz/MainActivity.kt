package com.university.homework.adelquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.university.homework.adelquiz.constant.Constants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        btnStartingTest.setOnClickListener {
            if(acetUserName.text.toString().isEmpty()){
                Toast.makeText(this@MainActivity, "Please, enter your name", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this@MainActivity, QuizActivity::class.java)
                intent.putExtra(Constants.USER_NAME, acetUserName.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}