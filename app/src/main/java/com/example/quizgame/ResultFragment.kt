package com.example.quizgame

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.quizgame.databinding.FragmentResultBinding
import com.loopj.android.http.HttpGet
import cz.msebera.android.httpclient.HttpResponse
import cz.msebera.android.httpclient.client.HttpClient
import kotlinx.coroutines.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultFragment : Fragment() {

    inner class HTTPAsyncTask : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg urls: String?): String {
            return httpGet(urls[0])
        }
        override fun onPostExecute(result: String?) {
            binding.tvCelsius.text = "Debrecen: " + result + " C"
        }
    }


    private lateinit var binding: FragmentResultBinding
    private lateinit var myPlayer: Player

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_result,container,false)

        val args = ResultFragmentArgs.fromBundle(requireArguments())
        myPlayer = Player(args.playerName, args.playerScore)

        binding.tvResultMessage.text = "Congrats " + myPlayer.name + "!"
        val numberOfTotalQuestion = 5
        val numberOfCorrectQuestion = myPlayer.score

        binding.tvScore.text = "You scored ${myPlayer.score} out of 5"

        binding.againButton.setOnClickListener{view: View->
            view.findNavController().navigate(ResultFragmentDirections.actionResultFragmentToQuizFragment(args.playerName))
        }

        HTTPAsyncTask().execute("http://api.openweathermap.org/data/2.5/weather?q=Budapest&appid=dbe03ce01204eaac221e37363257cd8b")

        return binding.root
    }

    private fun httpGet(myURL: String?): String {

        val inputStream:InputStream
        val result:String

        // create URL
        val url:URL = URL(myURL)

        // create HttpURLConnection
        val conn:HttpURLConnection = url.openConnection() as HttpURLConnection

        // make GET request to the given URL
        conn.connect()

        // receive response as inputStream
        inputStream = conn.inputStream

        // convert inputstream to string
        if(inputStream != null)
            result = convertInputStreamToString(inputStream)
        else
            result = "Did not work!"

        val weatherAsJson = JSONObject(result.toString())

        val weatherAsKelvin = weatherAsJson.getJSONObject("main").getString("temp")

        val weatherAsCelsius = weatherAsKelvin.split(".")[0].toInt() - 273

        return weatherAsCelsius.toString()
    }

    fun convertInputStreamToString(inputStream: InputStream): String{
        val reader = BufferedReader(inputStream.reader())
        var content: String
        try {
            content = reader.readText()
        } finally {
            reader.close()
        }

        return content
    }
}