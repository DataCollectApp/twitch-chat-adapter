package app.datacollect.twitchchatadapter.event.assembler

import app.datacollect.twitchchatadapter.event.domain.ClearMessageEventV1
import app.datacollect.twitchchatadapter.event.domain.RawEvent
import org.springframework.stereotype.Component

@Component
class ClearMessageEventV1Assembler(private val metadataAssembler: MetadataAssembler) {
    fun assemble(rawEvent: RawEvent): ClearMessageEventV1 {
        val inputParts = rawEvent.rawData.split(" ".toRegex()).toTypedArray()
        val metadata = metadataAssembler.assemble(inputParts[0])
        val messageBuilder = StringBuilder()
        for (i in 4 until inputParts.size) {
            messageBuilder.append(inputParts[i]).append(" ")
        }
        return ClearMessageEventV1(
                rawEvent.id,
                metadata["login"]!!,
                inputParts[3].substring(1),
                messageBuilder.substring(1).trim { it <= ' ' },
                rawEvent.time.toString())
    }
}
