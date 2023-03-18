package julotestcase.sanjaya.common.utils

import java.time.Instant
import java.time.ZoneId

fun Long.secondToLocalDateTime() = Instant.ofEpochSecond(this).atZone(ZoneId.systemDefault()).toLocalDateTime()