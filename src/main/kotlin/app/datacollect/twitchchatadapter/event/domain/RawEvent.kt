package app.datacollect.twitchchatadapter.event.domain

import java.time.Instant
import java.util.*

data class RawEvent(
        val id: UUID,
        val eventType: EventType,
        val objectType: ObjectType,
        val version: Version,
        val rawData: String,
        val time: Instant)
