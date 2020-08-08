package app.datacollect.twitchchatadapter.event.assembler

import app.datacollect.twitchchatadapter.event.domain.ChatMessageEventV1
import app.datacollect.twitchchatadapter.event.domain.RawEvent
import org.springframework.stereotype.Component

@Component
class ChatMessageEventV1Assembler(private val metadataAssembler: MetadataAssembler) {
    fun assemble(rawEvent: RawEvent): ChatMessageEventV1 {
        val inputParts = rawEvent.rawData.split(" ").toTypedArray()
        val metadata = metadataAssembler.assemble(inputParts[0])
        val messageBuilder = StringBuilder()
        for (i in 4 until inputParts.size) {
            messageBuilder.append(inputParts[i]).append(" ")
        }
        return ChatMessageEventV1(
                rawEvent.id,
                inputParts[1].split("!".toRegex()).toTypedArray()[0].substring(1),
                metadata["display-name"]!!,
                messageBuilder.substring(1).trim { it <= ' ' },
                inputParts[3].substring(1), metadata["user-id"]!!.toLong(), metadata["room-id"]!!.toLong(), metadata["mod"] == "1", metadata["subscriber"] == "1", metadata["turbo"] == "1",
                rawEvent.time.toString())
    }
}
