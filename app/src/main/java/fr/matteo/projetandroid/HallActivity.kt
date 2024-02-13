package fr.matteo.projetandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class HallActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hall)

        val title= intent.extras!!.getString("title", "");
        setTitle(title)
        val adresse1= intent.extras!!.getString("adresse1", "");
        val adresse2= intent.extras!!.getString("adresse2", "");
        val ville= intent.extras!!.getString("ville", "");
        val description= intent.extras!!.getString("description", "pas de description disponible");
        val parkingInfo= intent.extras!!.getString("parkingInfo", "");
        val transport= intent.extras!!.getString("transport", "");

        showBack()

        val textViewTitle = findViewById<TextView>(R.id.textViewTitle)
        val textViewAddress = findViewById<TextView>(R.id.textViewAddress)
        val textViewCity = findViewById<TextView>(R.id.textViewCity)
        val textViewDescription = findViewById<TextView>(R.id.textViewDescription)
        val textViewParkingInfo = findViewById<TextView>(R.id.textViewParkingInfo)
        val textViewTransport = findViewById<TextView>(R.id.textViewTransport)

        val descritpionText = getString(R.string.description)
        val parkingText = getString(R.string.parking)
        val transportText = getString(R.string.transport)

        textViewTitle.text = title
        textViewAddress.text = adresse1 +" "+ adresse2
        textViewCity.text = ville
        textViewDescription.text =descritpionText + description
        textViewParkingInfo.text =parkingText + parkingInfo
        textViewTransport.text =transportText + transport

        textViewTitle.visibility = View.VISIBLE

    }
}