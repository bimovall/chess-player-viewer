package org.example.chess_player_viewer.utils

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.periodUntil
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

object DateUtils {

    @OptIn(ExperimentalTime::class)
    fun getDifferenceTime(timeStamp: Long): String {
        val currentTime = Clock.System.now()
        val fromTime = Instant.fromEpochSeconds(timeStamp)
        val difference: Duration = currentTime - fromTime
        val period = fromTime.periodUntil(currentTime, TimeZone.currentSystemDefault())
        val totalMinutes = difference.inWholeMinutes
        return when {
            period.years > 0 -> {
                if (period.years == 1) "1 year ago" else "${period.years} years ago"
            }
            period.months > 0 -> {
                if (period.months == 1) "1 month ago" else "${period.months} months ago"
            }
            period.days > 0 -> {
                if (period.days == 1) "1 day ago" else "${period.days} days ago"
            }
            totalMinutes >= 60 -> {
                val hours = totalMinutes / 60
                if (hours == 1L) "1 hour ago" else "$hours hours ago"
            }
            totalMinutes >= 1 -> {
                if (totalMinutes == 1L) "1 minute ago" else "$totalMinutes minutes ago"
            }
            else -> "Just now"
        }
    }

    @OptIn(ExperimentalTime::class)
    fun convertTimestampToDateTime(
        timeStamp: Long,
        outputFormat: DateTimeFormat<LocalDateTime>
    ): String {
        val dateTime = Instant.fromEpochSeconds(timeStamp)
        val localTime = dateTime.toLocalDateTime(TimeZone.currentSystemDefault())
        val formattedTime = localTime.format(outputFormat)
        return formattedTime
    }
}