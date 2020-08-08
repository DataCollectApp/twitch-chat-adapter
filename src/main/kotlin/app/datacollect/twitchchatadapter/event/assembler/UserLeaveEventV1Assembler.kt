package app.datacollect.twitchchatadapter.event.assembler

import app.datacollect.twitchchatadapter.event.domain.RawEvent
import app.datacollect.twitchchatadapter.event.domain.UserLeaveEventV1
import org.springframework.stereotype.Component

@Component
class UserLeaveEventV1Assembler {
    fun assemble(rawEvent: RawEvent): UserLeaveEventV1 {
        val inputParts = rawEvent.rawData.split(" ".toRegex()).toTypedArray()
        return UserLeaveEventV1(
                rawEvent.id,
                inputParts[0].split("!".toRegex()).toTypedArray()[0].substring(1),
                inputParts[2].substring(1),
                rawEvent.time.toString())
    }
}