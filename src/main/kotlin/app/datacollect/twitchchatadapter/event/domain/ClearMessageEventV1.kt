package app.datacollect.twitchchatadapter.event.domain

import org.json.JSONObject
import java.util.*

class ClearMessageEventV1 : EventData {

    val id: UUID
    val targetUsername: String
    val channel: String
    val message: String
    val time: String

    constructor(id: UUID, targetUsername: String, channel: String, message: String, time: String) {
        this.id = id
        this.targetUsername = targetUsername
        this.channel = channel
        this.message = message
        this.time = time
    }

    constructor(jsonObject: JSONObject) {
        this.id = UUID.fromString(jsonObject.getString("id"))
        this.targetUsername = jsonObject.getString("targetUsername")
        this.channel = jsonObject.getString("channel")
        this.message = jsonObject.getString("message")
        this.time = jsonObject.getString("time")
    }

    override fun getEventType(): EventType {
        return EventType.CLEAR_MESSAGE_SNAPSHOT
    }

    override fun getObjectType(): ObjectType {
        return ObjectType.CLEAR_MESSAGE
    }

    override fun getVersion(): Version {
        return Version.V1
    }

    override fun toJson(): JSONObject {
        return JSONObject()
                .put("id", id.toString())
                .put("targetUsername", targetUsername)
                .put("channel", channel)
                .put("message", message)
                .put("time", time)
    }
}
