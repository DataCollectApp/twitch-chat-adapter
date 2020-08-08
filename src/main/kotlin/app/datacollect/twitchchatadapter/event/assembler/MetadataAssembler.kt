package app.datacollect.twitchchatadapter.event.assembler

import org.springframework.stereotype.Component
import java.util.*
import java.util.stream.Collectors

@Component
class MetadataAssembler {
    fun assemble(metadata: String): Map<String, String> {
        return Arrays.stream(metadata.substring(1).split(";").toTypedArray())
                .map { part: String -> part.split("=").toTypedArray() }
                .filter { keyValue: Array<String> -> keyValue.size > 1 }
                .collect(Collectors.toMap({ keyValue: Array<String> -> keyValue[0] }) { keyValue: Array<String> -> keyValue[1] })
    }
}
