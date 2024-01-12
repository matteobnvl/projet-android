package fr.matteo.projetandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity2 : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        showProfil()
        showBack()
    }
}