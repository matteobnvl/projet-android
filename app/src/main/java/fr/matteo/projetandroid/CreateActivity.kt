package fr.matteo.projetandroid

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import org.json.JSONObject

class CreateActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        val data = intent.getStringExtra("data")


        val buttonSave=findViewById<Button>(R.id.buttonSave)
        if (readSharedPref("email") != "") {
            setHeaderTitle(getString(R.string.txtCompte))
            buttonSave.text = getString(R.string.textSave)
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

        if (data != null){
            val jsonObject = JSONObject(data)
            edLastName.setText(jsonObject.getString("lastName"))
            edFirstName.setText(jsonObject.getString("firstName"))
            edEmail.setText(jsonObject.getString("email"))
            edAddress.setText(jsonObject.getString("address"))
            edZipcode.setText(jsonObject.getString("zipcode"))
            edCity.setText(jsonObject.getString("city"))
            edCardRef.setText(jsonObject.getString("cardRef"))
        } else {
            edLastName.setText( readSharedPref("lastName"))
            edFirstName.setText( readSharedPref("firstName"))
            edEmail.setText( readSharedPref("email"))
            edAddress.setText( readSharedPref("address"))
            edZipcode.setText( readSharedPref("zipcode"))
            edCity.setText( readSharedPref("city"))
            edCardRef.setText( readSharedPref("cardRef"))
        }

        buttonSave.setOnClickListener {
            writeSharedPref("lastName",edLastName.text.toString())
            writeSharedPref("firstName",edFirstName.text.toString())
            writeSharedPref("email",edEmail.text.toString())
            writeSharedPref("address",edAddress.text.toString())
            writeSharedPref("zipcode",edZipcode.text.toString())
            writeSharedPref("city",edCity.text.toString())
            writeSharedPref("cardRef",edCardRef.text.toString())

            val intent = Intent(this, TabBarActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
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