package app.datacollect.twitchchatadapter.event.assembler

import app.datacollect.twitchchatadapter.event.domain.GlobalClearChatEventV1
import app.datacollect.twitchchatadapter.event.domain.RawEvent
import org.springframework.stereotype.Component

@Component
class GlobalClearChatEventV1Assembler(private val metadataAssembler: MetadataAssembler) {
    fun assemble(rawEvent: RawEvent): GlobalClearChatEventV1 {
        val inputParts = rawEvent.rawData.split(" ".toRegex()).toTypedArray()
        val metadata = metadataAssembler.assemble(inputParts[0])
        return GlobalClearChatEventV1(
                rawEvent.id,
                inputParts[3].substring(1), metadata["room-id"]!!.toLong(),
                rawEvent.time.toString())
    }
}
