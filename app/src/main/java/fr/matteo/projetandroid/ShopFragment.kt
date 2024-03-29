package fr.matteo.projetandroid

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ShopFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShopFragment : Fragment(), MoviesAdapter.OnMovieClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var movies: ArrayList<Movies> = ArrayList(Shop.getShop())
    private lateinit var recyclerViewMovies: RecyclerView
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movies = ArrayList(Shop.getShop())
        val textViewShopVide = view.findViewById<TextView>(R.id.textViewShopVide)

        if (movies.isEmpty()) {
            textViewShopVide.visibility = View.VISIBLE
        }

        recyclerViewMovies = view.findViewById(R.id.recyclerViewMoviesShop)
        recyclerViewMovies.layoutManager = LinearLayoutManager(requireContext())
        moviesAdapter = MoviesAdapter(movies, CellMovieType.SHOP)
        moviesAdapter.onMovieClickListener = this
        recyclerViewMovies.adapter = moviesAdapter
        requireActivity().runOnUiThread {
            moviesAdapter.notifyDataSetChanged()
        }
    }

    override fun onMovieClick(movie: Movies) {
        Shop.removeMovieFromShop(movie)
        Toast.makeText(requireContext(), "Le film '${movie.title}' a été supprimé du panier", Toast.LENGTH_LONG).show()
        moviesAdapter.updateMoviesList(Shop.getShop())
        val textViewShopVide = view?.findViewById<TextView>(R.id.textViewShopVide)

        if (movies.isEmpty()) {
            textViewShopVide?.visibility = View.VISIBLE
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ShopFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ShopFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}