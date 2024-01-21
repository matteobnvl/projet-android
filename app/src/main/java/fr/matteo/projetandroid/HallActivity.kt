package fr.matteo.projetandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class HallActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hall)
        showBack()
        val hall = intent.getSerializableExtra("hall") as? Halls
        setHeaderTitle(hall?.name)

        val textViewAddress = findViewById<TextView>(R.id.textViewAddress)
        val textViewCity = findViewById<TextView>(R.id.textViewCity)
        val textViewDescription = findViewById<TextView>(R.id.textViewDescription)
        val textViewParkingInfo = findViewById<TextView>(R.id.textViewParkingInfo)
        val textViewTransport = findViewById<TextView>(R.id.textViewTransport)

        textViewAddress.text = hall?.address1
        textViewCity.text = hall?.city
        textViewDescription.text = "Description : " + hall?.description
        textViewParkingInfo.text = "Parking info : " + hall?.parkingInfo
        textViewTransport.text = "Transport : " + hall?.PublicTransport
    }
}