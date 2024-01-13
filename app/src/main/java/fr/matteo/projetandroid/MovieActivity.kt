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

        setHeaderTitle(movie?.title)
        val imageView=findViewById<ImageView>(R.id.imageViewMovie)
        val textViewRunTime = findViewById<TextView>(R.id.textViewRunTime)
        val textViewDescription = findViewById<TextView>(R.id.textViewDescription)

        textViewRunTime.text = movie?.runTime.toString()
        textViewDescription.text = movie?.description
        Glide.with(this).load(movie?.backdropUrl).into(imageView);
        showBack()
        buttonShop.setOnClickListener {
            if (movie != null) {
                Shop.addMovieInShop(movie)
                Toast.makeText(this, "Film ajouté au panier", Toast.LENGTH_SHORT).show()

            }
        }
    }
}