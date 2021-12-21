package com.example.quizgame

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.quizgame.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {

    private lateinit var binding: FragmentQuizBinding
    private lateinit var viewModel: QuizViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_quiz, container, false
        )

        Log.i("QuizFragment", "Called ViewModelProvider.get")
        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)
        binding.quizViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val args = QuizFragmentArgs.fromBundle(requireArguments())

        binding.apply {
            tvFirstOption.setOnClickListener { onClick(it) }
            tvSecondOption.setOnClickListener { onClick(it) }
            tvThirdOption.setOnClickListener { onClick(it) }
            tvFourthOption.setOnClickListener { onClick(it) }
            btnAnswerButton.setOnClickListener { onClick(it) }
        }

        return binding.root
    }

    private fun setQuestion() {
//        szintén LiveData küzdés -->
//        val index = viewModel.roundCounter.value?.minus(1)
//        val questions = viewModel.myQuestions.value
//        val currentQuestion = questions?.get(index!!)
        val question = viewModel.myQuestions[viewModel.roundCounter -1]

        setDefaultOptionTheme()

        if (viewModel.roundCounter == viewModel.maxRoundNumber) {
            binding.btnAnswerButton.text = "FINISH"
        } else {
            binding.btnAnswerButton.text = "CHECK ANSWER"
        }

        binding.pbProgressBarOfQuiz.progress = viewModel.roundCounter
        binding.tvProgressBarText.text =
            "${viewModel.roundCounter}" + "/" + binding.pbProgressBarOfQuiz.max

        binding.apply {
            ivImageQuestion.setImageResource(question.image)
            tvFirstOption.text = question.answers[0]
            tvSecondOption.text = question.answers[1]
            tvThirdOption.text = question.answers[2]
            tvFourthOption.text = question.answers[3]
        }
    }

    private fun setDefaultOptionTheme() {
        val options = ArrayList<TextView>()
        options.add(0, binding.tvFirstOption)
        options.add(1, binding.tvSecondOption)
        options.add(2, binding.tvThirdOption)
        options.add(3, binding.tvFourthOption)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.tv_background_for_options)
        }
    }

    private fun setSelectedOptionTheme(tv: TextView, selectedOptionPosition: Int) {

        setDefaultOptionTheme()

        viewModel.globalVarSelectedPosition = selectedOptionPosition

        tv.setTextColor(Color.WHITE)
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            requireContext(),
            R.drawable.tv_background_for_selected_option
        )
    }

    private fun answerView(answer: Int, viewForAnswer: Int) {
        when (answer) {
            1 -> {
                binding.tvFirstOption.background =
                    ContextCompat.getDrawable(requireContext(), viewForAnswer)
            }
            2 -> {
                binding.tvSecondOption.background =
                    ContextCompat.getDrawable(requireContext(), viewForAnswer)
            }
            3 -> {
                binding.tvThirdOption.background =
                    ContextCompat.getDrawable(requireContext(), viewForAnswer)
            }
            4 -> {
                binding.tvFourthOption.background =
                    ContextCompat.getDrawable(requireContext(), viewForAnswer)
            }
        }
    }

    private fun onClick(p0: View?) {
        when (p0?.id) {
            binding.tvFirstOption.id -> {
                setSelectedOptionTheme(binding.tvFirstOption, 1)
            }
            binding.tvSecondOption.id -> {
                setSelectedOptionTheme(binding.tvSecondOption, 2)
            }
            binding.tvThirdOption.id -> {
                setSelectedOptionTheme(binding.tvThirdOption, 3)
            }
            binding.tvFourthOption.id -> {
                setSelectedOptionTheme(binding.tvFourthOption, 4)
            }
            binding.btnAnswerButton.id -> {
                // USER DOES NOT CHOOSE ANSWER
                if (viewModel.globalVarSelectedPosition == 0) {
                    viewModel.roundCounter++

                    when {
                        // WHEN GAME CAN STEP TO NEXT ROUND
                        viewModel.roundCounter <= viewModel.maxRoundNumber -> {
                            setQuestion()
                        }
                        // WHEN IT WAS LAST ROUND
                        else -> {
                            view?.findNavController()?.navigate(
                                QuizFragmentDirections.actionQuizFragmentToResultFragment(
                                    viewModel.myPlayer.name,
                                    viewModel.myPlayer.score
                                )
                            )
                        }
                    }
                    // USER CHOOSES AN ANSWER
                } else {
                    // szintén LiveData küzdés
//                    val index = viewModel.roundCounter -1
//                    Log.d("Index: ", "$index")
//                    val questions = viewModel.myQuestions
//                    val currentQuestion = questions?.get(index!!)
//                    Log.d("An answer chosen", "${currentQuestion!!.id}")

                    val question = viewModel.myQuestions[viewModel.roundCounter -1]

                    // ANSWER IS INCORRECT
                    if (question.correctAnswer != viewModel.globalVarSelectedPosition) {
                        answerView(
                            viewModel.globalVarSelectedPosition,
                            R.drawable.tv_background_for_incorrect_option
                        )
                        // ANSWER IS CORRECT
                    } else {
                        viewModel.myPlayer.score++
                    }
                    answerView(question.correctAnswer, R.drawable.tv_background_for_correct_option)

                    if (viewModel.roundCounter == viewModel.maxRoundNumber) {
                        binding.btnAnswerButton.text = "FINISH QUIZ"
                    } else {
                        binding.btnAnswerButton.text = "NEXT QUESTION"
                    }
                    viewModel.globalVarSelectedPosition = 0
                }
            }
        }
    }
}