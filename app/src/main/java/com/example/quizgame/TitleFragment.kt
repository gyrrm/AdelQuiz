package com.example.quizgame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.quizgame.databinding.FragmentTitleBinding

/**
 * A simple [Fragment] subclass.
 * Use the [TitleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TitleFragment : Fragment() {

    private lateinit var binding: FragmentTitleBinding
    private val myPlayer: Player = Player("Init Name", 0)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_title,container,false)

        binding.myPlayer = myPlayer

        binding.btnStart.setOnClickListener {
            setPlayerName(it)
        }
        return binding.root
    }

    private fun setPlayerName(view: View) {

        binding.apply {
            myPlayer?.name = etPersonName.text.toString()
        }

        if(myPlayer.name.isEmpty()){
            Toast.makeText(context, "Please enter your name!", Toast.LENGTH_LONG).show()
        } else {
            view?.findNavController()?.navigate(TitleFragmentDirections.actionTitleFragmentToQuizFragment(myPlayer.name))
        }
    }
}