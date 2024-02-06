package com.example.myapplication


import android.content.ContentValues.TAG
import android.media.MediaPlayer
import android.media.browse.MediaBrowser
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.myapplication.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.io.IOException


class MainActivity : AppCompatActivity() {


   private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //-----------all clear---------//
        binding.allclear.setOnClickListener{
            binding.input.text = ""
            binding.result.text = ""
    }


        //------------- backspace ---------//

        binding.backspace.setOnClickListener{
            binding.input.text = binding.input.text.toString().dropLast(1)

            try {
                val lastchar =  binding.input.text.toString().last()

                if(lastchar.isDigit()){
                    ExpressionBuilder(binding.input.text.toString()).build()
                }
            }catch (e:Exception){

                binding.result.text = ""
                Log.e("Last char ERROR", e.toString())
            }
        }


        //-----------------equals--------->
        binding.equals.setOnClickListener() {
            val expression = ExpressionBuilder(binding.input.text.toString()).build()
            val result = expression.evaluate()

            val mediaPlayer = MediaPlayer()
            val songPath: Any = when {
                result == 7.0 -> "android.resource://${packageName}/${R.raw.mahi}"
                result == 18.0 -> "android.resource://${packageName}/${R.raw.kohli}"
                result == 45.0 -> "android.resource://${packageName}/${R.raw.rohit}"
                result == 10.0 -> "android.resource://${packageName}/${R.raw.sachin10}"
                result == 77.0 -> "android.resource://${packageName}/${R.raw.shubman}"
                result == 5.0 -> "android.resource://${packageName}/${R.raw.gambhir}"
                result == 33.0 -> "android.resource://${packageName}/${R.raw.siren}"

                else -> {"android.resource://${packageName}/${R.raw.sileenenen}"}
            }

                try {
                    mediaPlayer.setDataSource(this, Uri.parse(songPath.toString()))
                    mediaPlayer.prepareAsync()
                } catch (e: IOException) {
                    Log.e("MediaPlayer", "Error setting data source or preparing media player", e)
                    return@setOnClickListener
                }

                mediaPlayer.setOnErrorListener { mp, what, extra ->
                    Log.e("MediaPlayer", "Error occurred: $what, $extra")
                    return@setOnErrorListener false
                }

                mediaPlayer.setOnPreparedListener {
                    mediaPlayer.start()
                }

                mediaPlayer.setOnCompletionListener {
                    // Release the MediaPlayer resources when playback is completed
                    mediaPlayer.release()
                }


            // Display the result regardless of whether a song is played
            val resultString: String = result.toString()
            binding.result.text = resultString
        }




        //----------numbers-----------------//
        binding.zero.setOnClickListener(){
            binding.input.append("0")
        }

        binding.one.setOnClickListener(){
            binding.input.append("1")
        }

        binding.two.setOnClickListener(){
            binding.input.append("2")
        }

        binding.three.setOnClickListener(){
            binding.input.append("3")
        }

        binding.four.setOnClickListener(){
            binding.input.append("4")
        }

        binding.five.setOnClickListener(){
            binding.input.append("5")
        }

        binding.six.setOnClickListener(){
            binding.input.append("6")
        }

        binding.seven.setOnClickListener(){
            binding.input.append("7")
        }

        binding.eight.setOnClickListener(){
            binding.input.append("8")
        }

        binding.nine.setOnClickListener(){
            binding.input.append("9")
        }

        //----------------------------operator---------------------------//


        binding.plus.setOnClickListener(){
            binding.input.append("+")
        }

        binding.subtract.setOnClickListener(){
            binding.input.append("-")
        }

        binding.divide.setOnClickListener(){
            binding.input.append("/")
        }

        binding.multiply.setOnClickListener(){
            binding.input.append("*")
        }





    }

}

private fun Any.isNotEmpty(): Boolean {
    return this != null
}





