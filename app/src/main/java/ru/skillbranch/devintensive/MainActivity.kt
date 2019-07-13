package ru.skillbranch.devintensive


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("M_MainActiviti","onCreate")

    }

    override fun onRestart() {
        super.onRestart()
        Log.d("M_MainActiviti","onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d("M_MainActiviti","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("M_MainActiviti","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("M_MainActiviti","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("M_MainActiviti","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("M_MainActiviti","onDestroy")
    }
}
