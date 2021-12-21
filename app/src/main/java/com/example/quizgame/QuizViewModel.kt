package com.example.quizgame

import android.util.Log
import kotlinx.serialization.*
import androidx.lifecycle.ViewModel
import kotlinx.serialization.json.Json
import org.json.JSONObject
import java.net.URL
import java.text.DecimalFormat

class QuizViewModel : ViewModel() {

    // Ez lenne a LiveData rész, de nem akart működni....
//    private val _myPlayer = MutableLiveData<Player>()
//    val myPlayer: LiveData<Player>
//        get() = _myPlayer
//
//    fun setMyPlayerName(playerName: String) {
//        _myPlayer.value?.name = playerName
//    }
//
//    fun setMyPlayerScore(playerScore: Int) {
//        _myPlayer.value?.score = playerScore
//    }
//
//    private val _myQuestions = MutableLiveData<MutableList<Question>>()
//    val myQuestions: LiveData<MutableList<Question>>
//        get() = _myQuestions
//
//    private val _roundCounter = MutableLiveData<Int>()
//    val roundCounter: LiveData<Int>
//        get() = _roundCounter
//
//    fun setRoundCounter(inputNumber: Int) {
//        _roundCounter.value = inputNumber
//    }
//
//    private val _globalVarSelectedPosition = MutableLiveData<Int>()
//    val globalVarSelectedPosition: LiveData<Int>
//        get() = _globalVarSelectedPosition
//
//    fun setGlobalVarSelectedPosition(inputNumber: Int) {
//        _globalVarSelectedPosition.value = inputNumber
//    }

    var maxRoundNumber: Int = 5
    var myPlayer = Player("Init Name", 0)
    var myQuestions: MutableList<Question> = mutableListOf()
    var roundCounter: Int = 1
    var globalVarSelectedPosition: Int = 0

    init {
        Log.i("QuizViewModel", "QuizViewModel created!")

        myQuestions.add(0, Question(1, R.drawable.ic_cat, listOf("Dog", "Cat", "Cow", "Snake"), 2))
        myQuestions.add(1,Question(2, R.drawable.ic_cow, listOf("Dog", "Cat", "Cow", "Snake"), 3))
        myQuestions.add(2, Question(3, R.drawable.ic_dog, listOf("Cat", "Horse", "Dog", "Cow"), 3))
        myQuestions.add(3, Question(4, R.drawable.ic_bird, listOf("Cat", "Bird", "Snake", "Cow"), 2))
        myQuestions.add(4, Question(5, R.drawable.ic_horse, listOf("Dog", "Bird", "Snake", "Horse"),4))

        //myPlayer.name = QuizFragmentArgs.fromBundle(Bundle()).playerName

    }

    override fun onCleared() {
        super.onCleared()
        Log.i("QuizViewModel", "QuizViewModel destroyed!")
    }
}