package fr.matteo.projetandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MoviesAdapter (private val movies: ArrayList<Movies>, private val cellMovieType: CellMovieType): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    interface OnMovieClickListener {
        fun onMovieClick(movie: Movies)
    }

    var onMovieClickListener: OnMovieClickListener? = null
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val textViewTitleMovies = view.findViewById<TextView>(R.id.textViewTitleMovies)
        val imageViewMovies = view.findViewById<ImageView>(R.id.imageViewMovies)
        val textViewDescriptionShop = view.findViewById<TextView?>(R.id.textViewDescriptionShop)
        var currentMovie: Movies? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = when (cellMovieType) {
            CellMovieType.MOVIE -> R.layout.cell_movies
            CellMovieType.SHOP -> R.layout.cell_shop
        }

        val view = LayoutInflater.from(parent.context)
            .inflate(layout, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun updateMoviesList(newMovies: List<Movies>) {
        movies.clear()
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = this.movies.get(position)
        holder.textViewTitleMovies.text = movie.title
        holder.textViewDescriptionShop?.text = movie.description
        Glide.with(holder.imageViewMovies.context).load(movie.graphicUrl).into(holder.imageViewMovies)
        holder.currentMovie = movie

        holder.textViewTitleMovies.setOnClickListener {
            val selectedMovie = holder.currentMovie
            selectedMovie?.let {
                onMovieClickListener?.onMovieClick(it)
            }
        }
    }
}