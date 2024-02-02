package fr.matteo.projetandroid

import android.os.Bundle
import android.widget.TextView

class TabBarActivity : BaseActivity() {

    //val tab1Fragment= Tab1Fragment.newInstance("","")
    private lateinit var abonnementTab: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabbar)
        showBack()
        //setHeaderTitle(getString(R.string.txtTabbar))

        //val tab1= findViewById<TextView>(R.id.textViewTab1)

        //showTab1()

        //tab1.setOnClickListener {
            //showTab1()
        //}

        abonnementTab = findViewById(R.id.textViewTabAbonnement)
        showAbonnementTab()
        abonnementTab.setOnClickListener {
            showAbonnementTab()
        }
    }

    //fun showTab1(){
        //val frManager=supportFragmentManager
        //val fragmentTra= frManager.beginTransaction()
        //fragmentTra.addToBackStack("Tab1")
        //fragmentTra.replace(R.id.layoutContent,tab1Fragment)
        //fragmentTra.commit()
    //}

    fun showAbonnementTab(){
        val fragment = AbonnementFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.layoutContent, fragment)
            .addToBackStack("Abonnement")
            .commit()
    }
}