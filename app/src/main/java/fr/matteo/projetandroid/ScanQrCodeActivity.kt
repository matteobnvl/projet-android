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

        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        options.setPrompt("Scan a QR code")
        options.setCameraId(0)
        options.setBeepEnabled(false)
        options.setBarcodeImageEnabled(true)

        barcodeLauncher.launch(options)
    }
}
