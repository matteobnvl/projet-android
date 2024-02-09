package fr.matteo.projetandroid

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Espi","############### onCreate ###############"+javaClass.simpleName)
    }

    override fun onStart() {
        super.onStart()
        Log.d("Espi","############### onStart ###############"+javaClass.simpleName)
    }

    override fun onStop() {
        super.onStop()
        Log.d("Espi","############### onStop ###############"+javaClass.simpleName)
    }

    override fun onResume() {
        super.onResume()
        Log.d("Espi","############### onResume ###############"+javaClass.simpleName)
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Espi","############### onRestart ###############"+javaClass.simpleName)
    }

    override fun onPause() {
        super.onPause()
        Log.d("Espi","############### onPause ###############"+javaClass.simpleName)
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("Espi","############### onDestroy ###############"+javaClass.simpleName)
    }


    fun showBack(){
        val imageViewBack = findViewById<ImageView>(R.id.imageViewBack)
        imageViewBack.visibility=View.VISIBLE
        imageViewBack.setOnClickListener {
            this.finish()
        }
    }

    fun showProfil(){
        val imageViewProfil = findViewById<ImageView>(R.id.imageViewProfil)
        imageViewProfil.visibility = View.VISIBLE
        imageViewProfil.setOnClickListener {
            startActivity(Intent(this,CreateActivity::class.java))
        }
    }

    fun showLogoHeader(){
        val imageViewLogo = findViewById<ImageView>(R.id.imageViewLogoHeader)
        imageViewLogo.visibility = View.VISIBLE
    }

    fun setHeaderTitle(title:String?){
        if(title!=null) {
            val textViewNature = findViewById<TextView>(R.id.textViewTitle)
            textViewNature.visibility = View.VISIBLE
            textViewNature.text = title
        }
    }

    fun readSharedPref(key:String):String{
        val sharedPreferences: SharedPreferences = getSharedPreferences("account", Context.MODE_PRIVATE)
        return sharedPreferences.getString(key,"").toString()
    }
}