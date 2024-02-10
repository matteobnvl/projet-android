package fr.matteo.projetandroid

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
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

    private val cinemas = ArrayList<Halls>()

    private fun fetchCinemas() {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .url("https://ugarit-online.000webhostapp.com/epsi/films/salles.json")
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val data = response.body?.string()
                if (data != null) {
                    Log.d("MapFragment", "Response from URL: $data") // Log the response
                    val jsonCinemas = JSONObject(data)
                    val jsArrayCinemas = jsonCinemas.getJSONArray("salles")
                    for (i in 0 until jsArrayCinemas.length()) {
                        val cinemaJson = jsArrayCinemas.getJSONObject(i)
                        val cinema = Halls(
                            cinemaJson.getInt("id"),
                            cinemaJson.getString("name"),
                            cinemaJson.getString("address1"),
                            cinemaJson.optString("address2"),
                            cinemaJson.getString("city"),
                            cinemaJson.getDouble("latitude"),
                            cinemaJson.getDouble("longitude"),
                            cinemaJson.optString("parkingInfo"),
                            cinemaJson.optString("description"),
                            cinemaJson.optString("publicTransport")
                        )
                        cinemas.add(cinema)
                    }
                } else {
                    Log.e("MapFragment", "Empty response body")
                }

                activity?.runOnUiThread {
                    addMarkersToMap()
                }
            }
        })
    }
    private fun addMarkersToMap() {
        val markerWidth = 100
        val originalMarkerIcon = BitmapFactory.decodeResource(resources, R.drawable.movieticket)
        val resizedMarkerIcon =
            Bitmap.createScaledBitmap(originalMarkerIcon, markerWidth, markerWidth, false)
        val markerIcon = BitmapDescriptorFactory.fromBitmap(resizedMarkerIcon)
        googleMap.clear()
        cinemas.forEach { cinema ->
            val markerOptions = MarkerOptions()
                .position(LatLng(cinema.latitude.toDouble(), cinema.longitude.toDouble()))
                .title(cinema.name)
                .icon(markerIcon)
            googleMap.addMarker(markerOptions)?.tag = cinema
        }
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(48.854885, 2.338646), 5f))
    }


    lateinit var googleMap: GoogleMap

    private val callback = OnMapReadyCallback { googleMap ->
        this.googleMap = googleMap
        fetchCinemas()
        googleMap.setOnInfoWindowClickListener { marker ->
            val cinema = marker.tag as Halls
            cinema?.let {
                val intent = Intent(context, HallActivity::class.java)
                intent.putExtra("title", cinema.name)
                intent.putExtra("adresse1", cinema.address1)
                intent.putExtra("adresse2", cinema.address2)
                intent.putExtra("ville", cinema.city)
                intent.putExtra("description", cinema.description)
                intent.putExtra("parkingInfo", cinema.parkingInfo)
                intent.putExtra("transport", cinema.PublicTransport)

                startActivity(intent)
            }
        }
    }

    private var param1: String? = null
    private var param2: String? = null

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
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MapFragement.
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