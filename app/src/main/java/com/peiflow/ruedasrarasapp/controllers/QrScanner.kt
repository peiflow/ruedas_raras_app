package com.peiflow.ruedasrarasapp.controllers

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.webkit.URLUtil
import android.widget.Toast
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import com.peiflow.ruedasrarasapp.R
import com.peiflow.ruedasrarasapp.models.Hint
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.content_qr_scanner.*
import java.io.IOException

class QrScanner : AppCompatActivity() {
    lateinit var cameraView: SurfaceView
    lateinit var barcode: BarcodeDetector
    lateinit var cameraSource: CameraSource
    lateinit var holder: SurfaceHolder
    var barcodeText: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_scanner)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        cameraView = findViewById(R.id.cameraView)
        cameraView.setZOrderMediaOverlay(true)
        holder = cameraView.holder
        barcode = BarcodeDetector.Builder(this)
            .setBarcodeFormats(Barcode.QR_CODE).build()

        if (!barcode.isOperational) {
            Toast.makeText(
                applicationContext,
                "Couldn´t setup the detector",
                Toast.LENGTH_LONG
            ).show()
            this.finish()
        }

        startupCamera()

        barcode.setProcessor(object :
            Detector.Processor<Barcode> {
            override fun release() {}

            override fun receiveDetections(detections: Detector.Detections<Barcode>) {
                val barcodes = detections.detectedItems
                if (barcodes.size() > 0) {
                    val text: String = barcodes.valueAt(0).displayValue
                    println("barcodeText: $barcodeText // text: $text")
                    if (barcodeText != text) {
                        barcodeText = text
                        runOnUiThread {
                            barcodes.valueAt(0).displayValue = barcodes.valueAt(0).displayValue
                            //Toast.makeText(this@QrScanner, "QR Code read", Toast.LENGTH_LONG).show()
                            if (!URLUtil.isValidUrl(text)) {
                                //TODO check if text is a hint or not
                                tv_read_text.text = text
                                Hint.saveHint(this@QrScanner, text)
                            } else {
                                val openUrl = Intent(Intent.ACTION_VIEW)
                                openUrl.data = Uri.parse(text)
                                startActivity(openUrl)
                            }
                        }
                    }
                }
            }
        })
    }

    override fun onRestart() {
        startupCamera()
        super.onRestart()
    }

    override fun onBackPressed() {
        this.finish()
    }

    private fun startupCamera() {
        barcodeText = ""

        cameraSource = CameraSource.Builder(this, barcode)
            .setFacing(CameraSource.CAMERA_FACING_BACK)
            .setRequestedFps(24f)
            .setAutoFocusEnabled(true)
            .setRequestedPreviewSize(1920, 1080)
            .build()

        cameraView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                try {
                    if (ContextCompat.checkSelfPermission(
                            this@QrScanner,
                            Manifest.permission.CAMERA
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        cameraSource.start(cameraView.holder)
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

            override fun surfaceDestroyed(holder: SurfaceHolder) {}
        })
    }
}