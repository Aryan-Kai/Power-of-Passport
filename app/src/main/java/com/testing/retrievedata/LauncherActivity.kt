package com.testing.retrievedata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import java.util.*
import kotlin.concurrent.timerTask

class LauncherActivity : AppCompatActivity() {
    lateinit var top_anim: Animation
    lateinit var bottom_anim: Animation
    lateinit var img_top: ImageView
    lateinit var txt_bottom: TextView
    lateinit var timer:Timer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_launcher)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()
        top_anim = AnimationUtils.loadAnimation(this,R.anim.top_animation)
        bottom_anim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation)
        img_top = findViewById(R.id.img_open)
        txt_bottom = findViewById(R.id.txt_bottom)
        img_top.startAnimation(top_anim)
        timer = Timer()
        txt_bottom.startAnimation(bottom_anim)
        //btn_start.startAnimation(bottom_anim)
        val intent = Intent(this,LoginActivity::class.java)
        timer.schedule(timerTask {
            startActivity(intent)
            finish()
        },3000)
    }
}