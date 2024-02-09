package fr.matteo.projetandroid

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (readSharedPref("email") == "") {
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            },2500)
        } else {
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                startActivity(Intent(this,TabBarActivity::class.java))
                finish()
            },2500)
        }
    }
}