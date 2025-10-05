package dev.seano.mcpn.util

import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object DateUtils {
    fun formatDateTimeWithZone(dateString: String): String {
        val odt = OffsetDateTime.parse(dateString)
        val ldt = odt.atZoneSameInstant(ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy")
        return ldt.format(formatter)
    }
}
