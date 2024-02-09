package fr.matteo.projetandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide

class MovieActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        val movie = intent.getParcelableExtra<Movies>("movie")
        val buttonShop = findViewById<Button>(R.id.buttonShopMovie)

        if (movie?.let { Shop.isMovieInShop(it) } == true) {
            buttonShop.text = getString(R.string.presentInShop)
        }

        setHeaderTitle(movie?.title)
        val imageView=findViewById<ImageView>(R.id.imageViewMovie)
        val textViewRunTime = findViewById<TextView>(R.id.textViewRunTime)
        val textViewDescription = findViewById<TextView>(R.id.textViewDescription)

        textViewRunTime.text = "Durée : " + movie?.runTime.toString() + " min"
        textViewDescription.text = "Description : " + movie?.description
        Glide.with(this).load(movie?.backdropUrl).into(imageView);
        showBack()
        buttonShop.setOnClickListener {
            if (movie != null && !movie.let { Shop.isMovieInShop(it) }) {
                Shop.addMovieInShop(movie)
                buttonShop.text = getString(R.string.addInShop)
                Toast.makeText(this, "Le film '${movie.title}' a été ajouté à votre panier", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Le film '${movie?.title}' est déjà dans votre panier", Toast.LENGTH_LONG).show()
            }
        }
    }
}