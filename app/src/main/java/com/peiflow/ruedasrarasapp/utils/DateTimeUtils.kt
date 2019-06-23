package com.peiflow.ruedasrarasapp.utils

import java.text.SimpleDateFormat
import java.util.*

class DateTimeUtils {
    companion object {
        private val spanishLocale = Locale("es", "ES")
        fun convertDateTimeFormat(dateTime: String): String {
            val fmt = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
            val fmt2 = SimpleDateFormat("dd/MM/yyyy HH:mm")
            val date: Date
            date = fmt.parse(dateTime)
            return fmt2.format(date)
        }

        fun getSpanishLongDate(dateTime: String): String {
            val fmt = SimpleDateFormat("dd/MM/yyyy hh:mm:ss", spanishLocale)
            val date: Date = fmt.parse(dateTime)
            val fmtOut = SimpleDateFormat("EEEE dd/MM/yyyy", spanishLocale)
            val frmtDate: String = fmtOut.format(date)
            return frmtDate
        }

        fun getShortTime(dateTime: String): String {
            val spanish = Locale("es", "ES")
            val fmt = SimpleDateFormat("dd/MM/yyyy hh:mm:ss", spanish)
            val date: Date = fmt.parse(dateTime)
            val timeOut = SimpleDateFormat("HH:mm", spanish)
            val frmtTime: String = timeOut.format(date)
            return frmtTime
        }
    }
}