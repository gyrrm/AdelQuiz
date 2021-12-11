package com.university.homework.adelquiz.constant

import com.university.homework.adelquiz.R
import com.university.homework.adelquiz.model.Question

object Constants {

    const val USER_NAME: String = "userName"
    const val TOTAL_QUESTIONS: String = "totalQuestions"
    const val CORRECT_ANSWERS: String = "correctAnswers"

    fun getAllQuestion(): ArrayList<Question> {

        val questionsArrayList = ArrayList<Question>()

        val firstQuestion = Question(1, "Which animal can you see on the picture?",
            R.drawable.ic_cat, "Dog", "Cat", "Cow", "Snake", 2)

        questionsArrayList.add(firstQuestion)

        val secondQuestion = Question(2, "Which animal can you see on the picture?",
            R.drawable.ic_cow, "Dog", "Cat", "Cow", "Snake", 3)

        questionsArrayList.add(secondQuestion)

        val thirdQuestion = Question(3, "Which animal can you see on the picture?",
            R.drawable.ic_dog, "Cat", "Horse", "Dog", "Cow", 3)

        questionsArrayList.add(thirdQuestion)

        val fourthQuestion = Question(4, "Which animal can you see on the picture?",
            R.drawable.ic_bird, "Cat", "Bird", "Snake", "Cow", 2)

        questionsArrayList.add(fourthQuestion)

        val fifthQuestion = Question(5, "Which animal can you see on the picture?",
            R.drawable.ic_horse, "Dog", "Bird", "Snake", "Horse", 4)

        questionsArrayList.add(fifthQuestion)

        return questionsArrayList
    }
}