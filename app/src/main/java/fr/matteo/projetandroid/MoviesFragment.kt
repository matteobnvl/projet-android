package fr.matteo.projetandroid

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.CacheControl
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MoviesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MoviesFragment : Fragment(), MoviesAdapter.OnMovieClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val movies = arrayListOf<Movies>()
    private lateinit var recyclerViewMovies: RecyclerView
    private lateinit var progressBar: ProgressBar
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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movies, container, false)

        recyclerViewMovies = view.findViewById(R.id.recyclerViewMovies)
        recyclerViewMovies.layoutManager = LinearLayoutManager(requireContext())
        moviesAdapter = MoviesAdapter(movies)
        moviesAdapter.onMovieClickListener = this
        recyclerViewMovies.adapter = moviesAdapter

        progressBar = view.findViewById(R.id.progressBar)
        progressBar.visibility = View.VISIBLE

        if (movies.isEmpty()) {
            fetchData()
        } else {
            progressBar.visibility = View.GONE
        }
        return view
    }

    private fun fetchData() {
        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        val mRequestUrl = "https://ugarit-online.000webhostapp.com/epsi/films/movies.json"
        val request = Request.Builder()
            .url(mRequestUrl)
            .get()
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                val data = response.body?.string()
                if (data != null) {
                    val jsMovies = JSONObject(data)
                    val jsArrayMovies = jsMovies.getJSONArray("movies")

                    for (i in 0 until jsArrayMovies.length()) {
                        val js = jsArrayMovies.getJSONObject(i)
                        val movie = Movies(
                            js.optInt("id", 0),
                            js.optString("title", ""),
                            js.optString("description", ""),
                            js.optInt("runTime", 0),
                            js.optString("graphicUrl", ""),
                            js.optString("backdropUrl", "")
                        )
                        movies.add(movie)
                    }

                    requireActivity().runOnUiThread {
                        moviesAdapter.notifyDataSetChanged()
                        progressBar.visibility = View.GONE
                    }
                }
            }
        })
    }

    override fun onMovieClick(movie: Movies) {
        val newIntent = Intent(requireContext(), MovieActivity::class.java)
        newIntent.putExtra("movie", movie)
        startActivity(newIntent)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MoviesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MoviesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}