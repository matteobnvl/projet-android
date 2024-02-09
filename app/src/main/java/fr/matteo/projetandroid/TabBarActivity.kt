package fr.matteo.projetandroid

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

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

        val movies = findViewById<ImageView>(R.id.imageViewTabMovies)
        val map = findViewById<ImageView>(R.id.imageViewTabMap)
        val card = findViewById<ImageView>(R.id.imageViewTabCard)
        val shop = findViewById<ImageView>(R.id.imageViewTabShop)

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
        Glide.with(this).load(R.drawable.videocam).into(findViewById<ImageView>(R.id.imageViewTabMovies));

        Glide.with(this).load(R.drawable.storefront_outline).into(findViewById<ImageView>(R.id.imageViewTabMap));
        Glide.with(this).load(R.drawable.qr_code_outline).into(findViewById<ImageView>(R.id.imageViewTabCard));
        Glide.with(this).load(R.drawable.cart_outline).into(findViewById<ImageView>(R.id.imageViewTabShop));
    }

    private fun showMap(){
        val frManager=supportFragmentManager
        val fragmentTra= frManager.beginTransaction()
        fragmentTra.addToBackStack("Map")
        fragmentTra.replace(R.id.layoutContent,mapFragment)
        fragmentTra.commit()

        Glide.with(this).load(R.drawable.storefront).into(findViewById<ImageView>(R.id.imageViewTabMap));

        Glide.with(this).load(R.drawable.videocam_outline).into(findViewById<ImageView>(R.id.imageViewTabMovies))
        Glide.with(this).load(R.drawable.qr_code_outline).into(findViewById<ImageView>(R.id.imageViewTabCard));
        Glide.with(this).load(R.drawable.cart_outline).into(findViewById<ImageView>(R.id.imageViewTabShop));
    }

    private fun showCard(){
        val frManager=supportFragmentManager
        val fragmentTra= frManager.beginTransaction()
        fragmentTra.addToBackStack("Card")
        fragmentTra.replace(R.id.layoutContent,cardFragment)
        fragmentTra.commit()

        Glide.with(this).load(R.drawable.qr_code).into(findViewById<ImageView>(R.id.imageViewTabCard));

        Glide.with(this).load(R.drawable.videocam_outline).into(findViewById<ImageView>(R.id.imageViewTabMovies))
        Glide.with(this).load(R.drawable.storefront_outline).into(findViewById<ImageView>(R.id.imageViewTabMap));
        Glide.with(this).load(R.drawable.cart_outline).into(findViewById<ImageView>(R.id.imageViewTabShop));
    }

    private fun showShop(){
        val frManager=supportFragmentManager
        val fragmentTra= frManager.beginTransaction()
        fragmentTra.addToBackStack("Shop")
        fragmentTra.replace(R.id.layoutContent,shopFragment)
        fragmentTra.commit()

        Glide.with(this).load(R.drawable.cart).into(findViewById<ImageView>(R.id.imageViewTabShop));

        Glide.with(this).load(R.drawable.videocam_outline).into(findViewById<ImageView>(R.id.imageViewTabMovies))
        Glide.with(this).load(R.drawable.storefront_outline).into(findViewById<ImageView>(R.id.imageViewTabMap));
        Glide.with(this).load(R.drawable.qr_code_outline).into(findViewById<ImageView>(R.id.imageViewTabCard));
    }
}