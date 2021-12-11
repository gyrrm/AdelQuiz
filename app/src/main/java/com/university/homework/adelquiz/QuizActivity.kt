package com.university.homework.adelquiz

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.university.homework.adelquiz.constant.Constants
import com.university.homework.adelquiz.model.Question
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity(), View.OnClickListener {

    private var globalVarCurrentPosition: Int = 1
    private var globalVarQuestionList: ArrayList<Question>? = null
    private var globalVarSelectedPosition: Int = 0
    private var globalVarCorrectAnswersNumber: Int = 0
    private var globalVarUserName: String = "dummy"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        globalVarUserName = intent.getStringExtra(Constants.USER_NAME).toString()

        globalVarQuestionList = Constants.getAllQuestion()

        setQuestion()

        tvFirstOption.setOnClickListener(this)
        tvSecondOption.setOnClickListener(this)
        tvThirdOption.setOnClickListener(this)
        tvFourthOption.setOnClickListener(this)

        btnAnswerButton.setOnClickListener(this)
    }

    private fun setQuestion() {

        val question = globalVarQuestionList!!.get(globalVarCurrentPosition - 1)

        setDefaultOptionTheme()

        if(globalVarCurrentPosition == globalVarQuestionList!!.size) {
            btnAnswerButton.text = "FINISH"
        } else {
            btnAnswerButton.text = "CHOOSE YOU"
        }

        pbProgressBarOfQuiz.progress = globalVarCurrentPosition
        tvProgressBarProgress.text = "$globalVarCurrentPosition" + "/" + pbProgressBarOfQuiz.max

        tvQuestion.text = question.question
        ivImageQuestion.setImageResource(question.image)
        tvFirstOption.text = question.optionA
        tvSecondOption.text = question.optionB
        tvThirdOption.text = question.optionC
        tvFourthOption.text = question.optionD
    }

    private fun setDefaultOptionTheme() {
        val options = ArrayList<TextView>()
        options.add(0, tvFirstOption)
        options.add(1, tvSecondOption)
        options.add(2, tvThirdOption)
        options.add(3, tvFourthOption)

        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this@QuizActivity, R.drawable.tv_background_for_options)
        }
    }

    private fun setSelectedOptionTheme(tv: TextView, selectedOptionPosition: Int) {

        setDefaultOptionTheme()

        globalVarSelectedPosition = selectedOptionPosition

        tv.setTextColor(Color.WHITE)
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this@QuizActivity, R.drawable.tv_background_for_selected_option)
    }

    private fun answerView(answer: Int, viewForAnswer: Int) {
        when(answer) {
            1 -> {
                tvFirstOption.background = ContextCompat.getDrawable(this, viewForAnswer)
            }

            2 -> {
                tvSecondOption.background = ContextCompat.getDrawable(this, viewForAnswer)
            }

            3 -> {
                tvThirdOption.background = ContextCompat.getDrawable(this, viewForAnswer)
            }

            4 -> {
                tvFourthOption.background = ContextCompat.getDrawable(this, viewForAnswer)
            }
        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.tvFirstOption -> {
                setSelectedOptionTheme(tvFirstOption, 1)
            }

            R.id.tvSecondOption -> {
                setSelectedOptionTheme(tvSecondOption, 2)
            }

            R.id.tvThirdOption -> {
                setSelectedOptionTheme(tvThirdOption, 3)
            }

            R.id.tvFourthOption -> {
                setSelectedOptionTheme(tvFourthOption, 4)
            }

            R.id.btnAnswerButton -> {
                if(globalVarSelectedPosition == 0) {
                    globalVarCurrentPosition ++

                    when {
                        globalVarCurrentPosition <= globalVarQuestionList!!.size -> {
                            setQuestion()
                        } else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, globalVarUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, globalVarCorrectAnswersNumber)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, globalVarQuestionList!!.size)

                            startActivity(intent)
                        }
                    }
                } else {
                    val question = globalVarQuestionList?.get(globalVarCurrentPosition - 1)

                    if(question!!.chosenOne != globalVarSelectedPosition) {
                        answerView(globalVarSelectedPosition, R.drawable.tv_background_for_incorrect_option)
                    } else {
                        globalVarCorrectAnswersNumber++
                    }
                    answerView(question.chosenOne, R.drawable.tv_background_for_correct_option)

                    if(globalVarCurrentPosition == globalVarQuestionList!!.size) {
                        btnAnswerButton.text = "FINISH"
                    } else {
                        btnAnswerButton.text = "NEED ANOTHER QUESTION WHAT I DUNNO"
                    }
                    globalVarSelectedPosition = 0
                }
            }
        }
    }
}