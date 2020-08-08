package app.datacollect.twitchchatadapter.event.domain

import org.json.JSONObject
import java.util.*

class GlobalClearChatEventV1 : EventData {

    val id: UUID
    val channel: String
    val roomId: Long
    val time: String

    constructor(id: UUID, channel: String, roomId: Long, time: String) {
        this.id = id
        this.channel = channel
        this.roomId = roomId
        this.time = time
    }

    constructor(jsonObject: JSONObject) {
        this.id = UUID.fromString(jsonObject.getString("id"))
        this.channel = jsonObject.getString("channel")
        this.roomId = jsonObject.getLong("roomId")
        this.time = jsonObject.getString("time")
    }

    override fun getEventType(): EventType {
        return EventType.GLOBAL_CLEAR_CHAT_SNAPSHOT
    }

    override fun getObjectType(): ObjectType {
        return ObjectType.GLOBAL_CLEAR_CHAT
    }

    override fun getVersion(): Version {
        return Version.V1
    }

    override fun toJson(): JSONObject {
        return JSONObject()
                .put("id", id.toString())
                .put("channel", channel)
                .put("roomId", roomId)
                .put("time", time)
    }
}
