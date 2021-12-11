package com.university.homework.adelquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.university.homework.adelquiz.constant.Constants
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val userName = intent.getStringExtra(Constants.USER_NAME)
        tvUserName.text = userName
        val numberOfTotalQuestion = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val numberOfCorrectQuestion = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

        tvScore.text = "You scored $numberOfCorrectQuestion out of $numberOfTotalQuestion"

        btnFinish.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}