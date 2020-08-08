package app.datacollect.twitchchatadapter.event.assembler

import app.datacollect.twitchchatadapter.event.domain.HostDisabledEventV1
import app.datacollect.twitchchatadapter.event.domain.RawEvent
import org.springframework.stereotype.Component

@Component
class HostDisabledEventV1Assembler {
    fun assemble(rawEvent: RawEvent): HostDisabledEventV1 {
        val inputParts = rawEvent.rawData.split(" ".toRegex()).toTypedArray()
        return HostDisabledEventV1(
                rawEvent.id, inputParts[2].substring(1), rawEvent.time.toString())
    }
}
