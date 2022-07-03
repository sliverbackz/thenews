package com.zmt.thenews.helper

import android.annotation.SuppressLint
import android.content.Context
import com.zmt.thenews.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
object DateUtils {
    const val VALID_TILL_DATE_PATTERN = "dd MMM yyyy"

    fun validTill(date: Date?): String? {
        date ?: return null
        return SimpleDateFormat(VALID_TILL_DATE_PATTERN).format(date)
    }

    const val SERVER_FORMAT_FULL = "EEE MMM dd hh:mm:ss z yyyy"
    const val SERVER_FORMAT_FULL_WITH_AM_PM = "EEE MMM dd hh:mm:ss a z yyyy"
    const val UI_FORMAT_LONG = "EEE dd MMM yyyy hh:mm a"
    const val UI_FORMAT_SHORT = "dd MMM yyyy"
    const val UI_FORMAT_DAY_HOUR = "EEE dd MMM, hh:mm a"
    const val UI_FORMAT_HOUR = "hh:mm a"
    const val ANALYTICS_FORMAT = "MMM d, yyyy h:mm a"
    const val ARCHIVE_DATE = "yyyy-MM-dd hh:mm:ss"
    const val ARCHIVE_DATE_V2 = "MMMM dd,yyyy"
    const val MUTED_DATE = "MMMM dd, yyyy, h:mm a"
    const val ISO_DATE = "yyyy-MM-dd'T'HH:mm:ss Z"


    fun Long.formatDate(pattern: String): String {
        val date = Date(this)
        return date.formatDate(pattern)
    }

    fun Long?.formatDate(pattern: String): String? {
        if(this === null)
            return null
        return this.formatDate(pattern)
    }

    fun Date.formatDate(pattern: String): String {
        val to = SimpleDateFormat(pattern, Locale.getDefault())
        to.timeZone = TimeZone.getDefault()
        return to.format(this)
    }

    fun changeFormat(fromPattern: String, toPattern: String, date: String): String {
        val from = SimpleDateFormat(fromPattern, Locale.getDefault())
        val to = SimpleDateFormat(toPattern, Locale.getDefault())
        to.timeZone = TimeZone.getDefault()
        return try {
            val date = from.parse(date)
            to.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
            ""
        }
    }

    fun Long.toStringDate(pattern: String, inMillis: Boolean = true): String {
        val date = Date(if(inMillis)this else this * 1000)
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        return sdf.format(date)
    }

    fun String.getMilliseconds(pattern: String): Long? {
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        return try {
            val date = sdf.parse(this)
            date.time
        } catch (e: ParseException) {
            null
        }
    }

    fun Long.msToTimeAgo(context: Context): String {
        val seconds = (System.currentTimeMillis() - this) / 1000f

        return when (true) {
            seconds < 60 -> context.resources.getQuantityString(R.plurals.seconds_ago, seconds.toInt(), seconds.toInt())
            seconds < 3600 -> {
                val minutes = seconds / 60f
                context.resources.getQuantityString(R.plurals.minutes_ago, minutes.toInt(), minutes.toInt())
            }
            seconds < 86400 -> {
                val hours = seconds / 3600f
                context.resources.getQuantityString(R.plurals.hours_ago, hours.toInt(), hours.toInt())
            }
            seconds < 604800 -> {
                val days = seconds / 86400f
                context.resources.getQuantityString(R.plurals.days_ago, days.toInt(), days.toInt())
            }
            seconds < 2_628_000 -> {
                val weeks = seconds / 604800f
                context.resources.getQuantityString(R.plurals.weeks_ago, weeks.toInt(), weeks.toInt())
            }
            seconds < 31_536_000 -> {
                val months = seconds / 2_628_000f
                context.resources.getQuantityString(R.plurals.months_ago, months.toInt(), months.toInt())
            }
            else -> {
                val years = seconds / 31_536_000f
                context.resources.getQuantityString(R.plurals.years_ago, years.toInt(), years.toInt())
            }
        }
    }
}