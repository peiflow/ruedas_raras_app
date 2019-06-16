package com.peiflow.ruedasrarasapp

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.content_partners.*

class Partners : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_partners)

        ib_amaq.setOnClickListener(View.OnClickListener {
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("http://www.amaq.es/")
            startActivity(openUrl)
        })
        ib_ayto.setOnClickListener(View.OnClickListener {
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://www.toroayto.es/")
            startActivity(openUrl)
        })
        ib_ballesteros.setOnClickListener(View.OnClickListener {
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://www.embutidosballesteros.es/")
            startActivity(openUrl)
        })
        ib_cajarural.setOnClickListener(View.OnClickListener {
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://www.ruralvia.com/")
            startActivity(openUrl)
        })
        ib_cepsa.setOnClickListener(View.OnClickListener {
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://goo.gl/maps/GEf7piNfZWZiMxj47")
            startActivity(openUrl)
        })
        ib_colegiata.setOnClickListener(View.OnClickListener {
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://goo.gl/maps/VgKP2RaERnZb2k9r6")
            startActivity(openUrl)
        })
        ib_dorado.setOnClickListener(View.OnClickListener {
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://goo.gl/maps/kZXsrCH484wEkVu27")
            startActivity(openUrl)
        })
        ib_elocas.setOnClickListener(View.OnClickListener {
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("http://canaloneselocas.com/")
            startActivity(openUrl)
        })
        ib_jayda.setOnClickListener(View.OnClickListener {
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://goo.gl/maps/1ZjMBeoDJ3Lh6reG9")
            startActivity(openUrl)
        })
        ib_latarce.setOnClickListener(View.OnClickListener {
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("http://bodegalatarce.com/")
            startActivity(openUrl)
        })
        ib_noales.setOnClickListener(View.OnClickListener {
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://www.noalespavirema.com/")
            startActivity(openUrl)
        })
        ib_paintball.setOnClickListener(View.OnClickListener {
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://www.paintballtoro.es/")
            startActivity(openUrl)
        })
        ib_paucar.setOnClickListener(View.OnClickListener {
            /*val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("")
            startActivity(openUrl)*/
        })
        ib_play.setOnClickListener(View.OnClickListener {
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://goo.gl/maps/HRuiYVwd5ucmNxUq5")
            startActivity(openUrl)
        })
        ib_polo.setOnClickListener(View.OnClickListener {
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https@ //www.toroayto.es/")
            startActivity(openUrl)
        })
        ib_reyes.setOnClickListener(View.OnClickListener {
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://goo.gl/maps/2XkevCMrARXkdGxF6")
            startActivity(openUrl)
        })
        ib_tecnotaller.setOnClickListener(View.OnClickListener {
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://www.tecnotaller.es/")
            startActivity(openUrl)
        })
        ib_torreduero.setOnClickListener(View.OnClickListener {
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://goo.gl/maps/f2Scv77X1kCLSoLQA")
            startActivity(openUrl)
        })
        ib_ultimo.setOnClickListener(View.OnClickListener {
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://goo.gl/maps/5YiMQYkvcp94GnMg6")
            startActivity(openUrl)
        })
        ib_vdiez.setOnClickListener(View.OnClickListener {
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://goo.gl/maps/xbNDp2tTgwia75869")
            startActivity(openUrl)
        })
    }
}
