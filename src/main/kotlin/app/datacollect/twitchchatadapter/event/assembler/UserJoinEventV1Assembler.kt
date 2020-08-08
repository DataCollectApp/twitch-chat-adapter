package app.datacollect.twitchchatadapter.event.assembler

import app.datacollect.twitchchatadapter.event.domain.RawEvent
import app.datacollect.twitchchatadapter.event.domain.UserJoinEventV1
import org.springframework.stereotype.Component

@Component
class UserJoinEventV1Assembler {
    fun assemble(rawEvent: RawEvent): UserJoinEventV1 {
        val inputParts = rawEvent.rawData.split(" ".toRegex()).toTypedArray()
        return UserJoinEventV1(
                rawEvent.id,
                inputParts[0].split("!".toRegex()).toTypedArray()[0].substring(1),
                inputParts[2].substring(1),
                rawEvent.time.toString())
    }
}
