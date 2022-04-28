package com.codingwithmitch.cleannotes.business.domain.util

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


// this dateUtil may should be in the framework package because of the dagger annotations and firestore
@Singleton
class DateUtil
@Inject
constructor(
    private val dateFormat: SimpleDateFormat
) {
    // Date format: "2019-07-23 HH:mm:ss"

    //2019-07-23
    fun removeTimeFromDateString(sd: String): String {
        return sd.substring(0, sd.indexOf(" "))
    }

    fun convertFirebaseTimestampToStringData(timestamp: Timestamp): String {
        return dateFormat.format(timestamp.toDate())
    }

    fun convertStringDateToFirebaseTimestamp(date: String): Timestamp {
        return Timestamp(dateFormat.parse(date) ?: Date())
    }

    fun getCurrentTimestamp(): String {
        return dateFormat.format(Date())
    }

}
