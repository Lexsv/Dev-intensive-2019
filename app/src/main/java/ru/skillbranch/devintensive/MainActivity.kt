package ru.skillbranch.devintensive


import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.models.Bender
import java.util.*

class MainActivity() : AppCompatActivity(), View.OnClickListener {
    lateinit var benderImage: ImageView
    lateinit var textView: TextView
    lateinit var messageEt: EditText
    lateinit var sendBtn: ImageView
    lateinit var  benderObjects: Bender

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("M_MainActiviti","onCreate")
        benderImage = iv_bender
        textView = tv_text
        messageEt = et_message
        sendBtn = iv_send

        val status = savedInstanceState?.getString("STATUS") ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString("QUESION") ?: Bender.Question.NAME.name
        benderObjects = Bender(Bender.Status.valueOf(status),Bender.Question.valueOf(question))

        val (r,g,b) = benderObjects.status.color
        benderImage.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)

        textView.text = benderObjects.askQuestion()
        sendBtn.setOnClickListener(this)

    }



    override fun onClick(v: View?) {
        if (v?.id == R.id.iv_send){
            val (phrase,color) = benderObjects.listenAnswer(messageEt.text.toString().toLowerCase())
            val (r,g,b) = color
            benderImage.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)
            textView.text = phrase
            messageEt.setText("")
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString("STATUS", benderObjects.status.name)
        outState?.putString("QUESION", benderObjects.question.name)

    }
}
