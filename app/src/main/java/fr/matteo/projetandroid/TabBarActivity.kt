package fr.matteo.projetandroid

import android.os.Bundle
import android.widget.TextView

class TabBarActivity : BaseActivity() {

    val moviesFragment = MoviesFragment.newInstance("", "")
    val mapFragment = MapFragment.newInstance("", "")
    val cardFragment = CardFragment.newInstance("", "")
    val shopFragment = ShopFragment.newInstance("", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabbar)
        showProfil()
        showLogoHeader()

        val movies = findViewById<TextView>(R.id.textViewMovies)
        val map = findViewById<TextView>(R.id.textViewMap)
        val card = findViewById<TextView>(R.id.textViewCard)
        val shop = findViewById<TextView>(R.id.textViewShop)

        showMovies()

        movies.setOnClickListener {
            showMovies()
        }

        map.setOnClickListener {
            showMap()
        }

        card.setOnClickListener {
            showCard()
        }

        shop.setOnClickListener {
            showShop()
        }
    }

    private fun showMovies(){
        val frManager=supportFragmentManager
        val fragmentTra= frManager.beginTransaction()
        fragmentTra.addToBackStack("Movies")
        fragmentTra.replace(R.id.layoutContent,moviesFragment)
        fragmentTra.commit()
    }

    private fun showMap(){
        val frManager=supportFragmentManager
        val fragmentTra= frManager.beginTransaction()
        fragmentTra.addToBackStack("Map")
        fragmentTra.replace(R.id.layoutContent,mapFragment)
        fragmentTra.commit()
    }

    private fun showCard(){
        val frManager=supportFragmentManager
        val fragmentTra= frManager.beginTransaction()
        fragmentTra.addToBackStack("Card")
        fragmentTra.replace(R.id.layoutContent,cardFragment)
        fragmentTra.commit()
    }

    private fun showShop(){
        val frManager=supportFragmentManager
        val fragmentTra= frManager.beginTransaction()
        fragmentTra.addToBackStack("Shop")
        fragmentTra.replace(R.id.layoutContent,shopFragment)
        fragmentTra.commit()
    }
}