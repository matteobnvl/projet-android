package fr.matteo.projetandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setHeaderTitle(getString(R.string.txtCreate))

        val buttonLogin=findViewById<Button>(R.id.qrCode)
        buttonLogin.setOnClickListener {
            startActivity(Intent(this,CreateActivity::class.java))
        }

        val buttonStudents=findViewById<Button>(R.id.buttonCreate)
        buttonStudents.setOnClickListener {
            startActivity(Intent(this,CreateActivity::class.java))
        }

    }
}