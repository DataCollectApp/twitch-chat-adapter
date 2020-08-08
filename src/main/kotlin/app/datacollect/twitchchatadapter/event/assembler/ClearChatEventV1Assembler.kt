package app.datacollect.twitchchatadapter.event.assembler

import app.datacollect.twitchchatadapter.event.domain.ClearChatEventV1
import app.datacollect.twitchchatadapter.event.domain.RawEvent
import org.springframework.stereotype.Component
import java.util.*

@Component
class ClearChatEventV1Assembler(private val metadataAssembler: MetadataAssembler) {
    fun assemble(rawEvent: RawEvent): ClearChatEventV1 {
        val inputParts = rawEvent.rawData.split(" ".toRegex()).toTypedArray()
        val metadata = metadataAssembler.assemble(inputParts[0])
        return ClearChatEventV1(
                rawEvent.id,
                inputParts[4].substring(1), metadata["target-user-id"]!!.toLong(),
                inputParts[3].substring(1), metadata["room-id"]!!.toLong(),
                Optional.ofNullable(metadata["ban-duration"]).map { s: String -> s.toLong() }.orElse(-1L),
                rawEvent.time.toString())
    }
}
