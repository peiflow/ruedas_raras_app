package com.peiflow.ruedasrarasapp.controllers

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.peiflow.ruedasrarasapp.R
import kotlinx.android.synthetic.main.content_partners.*

class Partners : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_partners)

        ib_ajaba.setOnClickListener {
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://goo.gl/maps/jNuwbCKJw5KAih1v9")
            startActivity(openUrl)
        }

        ib_amaq.setOnClickListener{
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("http://www.amaq.es/")
            startActivity(openUrl)
        }
        ib_ayto.setOnClickListener{
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://www.toroayto.es/")
            startActivity(openUrl)
        }
        ib_ballesteros.setOnClickListener{
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://www.embutidosballesteros.es/")
            startActivity(openUrl)
        }
        ib_cajarural.setOnClickListener{
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://www.ruralvia.com/")
            startActivity(openUrl)
        }
        ib_cepsa.setOnClickListener{
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://goo.gl/maps/GEf7piNfZWZiMxj47")
            startActivity(openUrl)
        }
        ib_colegiata.setOnClickListener{
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://goo.gl/maps/VgKP2RaERnZb2k9r6")
            startActivity(openUrl)
        }
        ib_dorado.setOnClickListener{
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://goo.gl/maps/kZXsrCH484wEkVu27")
            startActivity(openUrl)
        }
        ib_elocas.setOnClickListener{
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("http://canaloneselocas.com/")
            startActivity(openUrl)
        }
        ib_gonzalez.setOnClickListener{
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("http://www.gonzalezhiguera.es/")
            startActivity(openUrl)
        }
        ib_heinken.setOnClickListener{
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://goo.gl/maps/o2ax7TeS93dZcgNE8")
            startActivity(openUrl)
        }
        ib_jayda.setOnClickListener{
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://goo.gl/maps/1ZjMBeoDJ3Lh6reG9")
            startActivity(openUrl)
        }
        ib_latarce.setOnClickListener{
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("http://bodegalatarce.com/")
            startActivity(openUrl)
        }
        ib_noales.setOnClickListener{
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://www.noalespavirema.com/")
            startActivity(openUrl)
        }
        ib_oportunidadestic.setOnClickListener{
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("//https://oportunidadestic.com/")
            startActivity(openUrl)
        }
        ib_paintball.setOnClickListener{
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://www.paintballtoro.es/")
            startActivity(openUrl)
        }
        ib_paucar.setOnClickListener{
            val openUrl = Intent(Intent.ACTION_VIEW)
            //openUrl.data = Uri.parse("")
            //startActivity(openUrl)
        }
        ib_paucar.setOnClickListener{
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("http://www.prefabricadosduero.es/pd/")
            startActivity(openUrl)
        }
        ib_play.setOnClickListener{
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://goo.gl/maps/HRuiYVwd5ucmNxUq5")
            startActivity(openUrl)
        }
        ib_polo.setOnClickListener{
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://www.facebook.com/pages/category/Medical-Center/Clinica-De-Fisioterapia-Victor-Polo-1552445411672517/")
            startActivity(openUrl)
        }
        ib_relo.setOnClickListener{
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://goo.gl/maps/swBzhteEKEFdB8dZ9")
            startActivity(openUrl)
        }
        ib_reyes.setOnClickListener{
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://goo.gl/maps/2XkevCMrARXkdGxF6")
            startActivity(openUrl)
        }
        ib_tecnotaller.setOnClickListener{
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://www.tecnotaller.es/")
            startActivity(openUrl)
        }
        ib_torreduero.setOnClickListener{
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://goo.gl/maps/f2Scv77X1kCLSoLQA")
            startActivity(openUrl)
        }
        ib_ultimo.setOnClickListener{
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://goo.gl/maps/5YiMQYkvcp94GnMg6")
            startActivity(openUrl)
        }
        ib_vdiez.setOnClickListener {
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://goo.gl/maps/xbNDp2tTgwia75869")
            startActivity(openUrl)
        }
    }
}