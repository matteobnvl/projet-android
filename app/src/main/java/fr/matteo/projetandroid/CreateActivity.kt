package fr.matteo.projetandroid

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class CreateActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        if (readSharedPref("email") != "") {
            setHeaderTitle(getString(R.string.txtCompte))
            showBack()
        } else {
            setHeaderTitle(getString(R.string.txtCreate))
        }

        val edLastName = findViewById<EditText>(R.id.editTextNom)
        val edFirstName = findViewById<EditText>(R.id.editTextPrenom)
        val edEmail = findViewById<EditText>(R.id.editTextEmail)
        val edAddress=findViewById<EditText>(R.id.editTextAdress)
        val edZipcode = findViewById<EditText>(R.id.editTextCodePostal)
        val edCity = findViewById<EditText>(R.id.editTextVille)
        val edCardRef = findViewById<EditText>(R.id.editTextCarteFidelite)

        edLastName.setText( readSharedPref("lastName"))
        edFirstName.setText( readSharedPref("firstName"))
        edEmail.setText( readSharedPref("email"))
        edAddress.setText( readSharedPref("address"))
        edZipcode.setText( readSharedPref("zipcode"))
        edCity.setText( readSharedPref("city"))
        edCardRef.setText( readSharedPref("cardRef"))

        val buttonSave=findViewById<Button>(R.id.buttonSave)

        buttonSave.setOnClickListener {
            writeSharedPref("lastName",edLastName.text.toString())
            writeSharedPref("firstName",edFirstName.text.toString())
            writeSharedPref("email",edEmail.text.toString())
            writeSharedPref("address",edAddress.text.toString())
            writeSharedPref("zipcode",edZipcode.text.toString())
            writeSharedPref("city",edCity.text.toString())
            writeSharedPref("cardRef",edCardRef.text.toString())

            startActivity(Intent(this,TabBarActivity::class.java))
            this.finish()
        }
    }

    fun writeSharedPref(key:String,value:String){
        val sharedPreferences: SharedPreferences = getSharedPreferences("account", Context.MODE_PRIVATE)
        val editor =sharedPreferences.edit()
        editor.putString(key,value)
        editor.apply()
    }
}