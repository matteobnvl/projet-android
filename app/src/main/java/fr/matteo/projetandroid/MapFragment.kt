package fr.matteo.projetandroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
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
 * Use the [MapFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MapFragment : Fragment() {
    lateinit var googleMap: GoogleMap
    private var param1: String? = null
    private var param2: String? = null
    private val halls = arrayListOf<Halls>()

    private val callback = OnMapReadyCallback { googleMap ->
        fetchData()
        Log.d("MATTEO", "here")
        for (hall in halls) {
            Log.d("Response", hall.toString())
            val city = MarkerOptions()
            val cityLatLng = LatLng(hall.latitude, hall.longitude)
            city.title("Salle " + hall.name)
            city.position(cityLatLng)
            val marker = googleMap.addMarker(city)
            if (marker != null) {
                marker.tag = hall
            }

        }

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(48.854885, 2.338646), 5f))

        googleMap.setOnInfoWindowClickListener { marker ->
            val hall = marker.tag as? Halls
            val newIntent = Intent(requireContext(), HallActivity::class.java)
            newIntent.putExtra("hall", hall)
            startActivity(newIntent)
        }
        this.googleMap = googleMap

    }

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
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchData()
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun fetchData() {
        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        val mRequestUrl = "https://ugarit-online.000webhostapp.com/epsi/films/salles.json"
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
                    val hallsJson = JSONObject(data)
                    Log.d("toto", hallsJson.toString())
                    val jsArrayHalls = hallsJson.getJSONArray("salles")

                    for (i in 0 until jsArrayHalls.length()) {
                        val js = jsArrayHalls.getJSONObject(i)
                        val hall = Halls(
                            js.optInt("id", 0),
                            js.optString("name", "name"),
                            js.optString("address1", "address1"),
                            js.optString("address2", "address2"),
                            js.optString("city", "city"),
                            js.optDouble("latitude", 0.0),
                            js.optDouble("longitude", 0.0),
                            js.optString("parkingInfo", "parkingInfo"),
                            js.optString("description", "description"),
                            js.optString("publicTransport", "publicTransport")
                        )
                        halls.add(hall)
                    }
                }
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MapFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MapFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}