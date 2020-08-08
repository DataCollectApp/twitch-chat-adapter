package app.datacollect.twitchchatadapter.event.assembler

import app.datacollect.twitchchatadapter.event.domain.HostEnabledEventV1
import app.datacollect.twitchchatadapter.event.domain.RawEvent
import org.springframework.stereotype.Component

@Component
class HostEnabledEventV1Assembler {
    fun assemble(rawEvent: RawEvent): HostEnabledEventV1 {
        val inputParts = rawEvent.rawData.split(" ".toRegex()).toTypedArray()
        return HostEnabledEventV1(
                rawEvent.id,
                inputParts[2].substring(1),
                inputParts[3].substring(1),
                rawEvent.time.toString())
    }
}
