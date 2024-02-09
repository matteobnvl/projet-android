package fr.matteo.projetandroid

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

class ScanQrCodeActivity : AppCompatActivity() {

    // Utilisez ActivityResultLauncher pour gérer le résultat du scan
    private val barcodeLauncher: ActivityResultLauncher<ScanOptions> = registerForActivityResult(
        ScanContract()
    ) { result ->
        if (result.contents == null) {
            Toast.makeText(this@ScanQrCodeActivity, "Cancelled", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(
                this@ScanQrCodeActivity,
                "Scanned: " + result.contents,
                Toast.LENGTH_LONG
            ).show()
            val intent = Intent(this, CreateActivity::class.java)
            intent.putExtra("data", result.contents)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_qr_code)

        // Configurer les options de scan
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        options.setPrompt("Scan a QR code")
        options.setCameraId(0)  // Utiliser la caméra arrière
        options.setBeepEnabled(false)
        options.setBarcodeImageEnabled(true)

        // Lancer le scanner
        barcodeLauncher.launch(options)
    }
}
