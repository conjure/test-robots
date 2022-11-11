package uk.co.conjure.testrobots

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class DemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)
    }

    override fun onStart() {
        super.onStart()
        findViewById<Button>(R.id.btn_press_me).setOnClickListener {
            findViewById<TextView>(R.id.tv_message).visibility = View.VISIBLE
        }
    }
}