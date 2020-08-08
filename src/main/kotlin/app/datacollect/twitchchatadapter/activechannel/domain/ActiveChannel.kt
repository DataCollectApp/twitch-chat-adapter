package app.datacollect.twitchchatadapter.activechannel.domain

import java.time.Instant

data class ActiveChannel(val id: Long, val channel: String, val joinedTime: Instant)
