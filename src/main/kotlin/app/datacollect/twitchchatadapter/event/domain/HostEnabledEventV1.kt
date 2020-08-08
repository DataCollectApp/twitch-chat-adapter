package app.datacollect.twitchchatadapter.event.domain

import org.json.JSONObject
import java.util.*

class HostEnabledEventV1 : EventData {

    val id: UUID
    val channel: String
    val targetChannel: String
    val time: String

    constructor(id: UUID, channel: String, targetChannel: String, time: String) {
        this.id = id
        this.channel = channel
        this.targetChannel = targetChannel
        this.time = time
    }

    constructor(jsonObject: JSONObject) {
        this.id = UUID.fromString(jsonObject.getString("id"))
        this.channel = jsonObject.getString("channel")
        this.targetChannel = jsonObject.getString("targetChannel")
        this.time = jsonObject.getString("time")
    }

    override fun getEventType(): EventType {
        return EventType.HOST_ENABLED
    }

    override fun getObjectType(): ObjectType {
        return ObjectType.HOST
    }

    override fun getVersion(): Version {
        return Version.V1
    }

    override fun toJson(): JSONObject {
        return JSONObject()
                .put("id", id.toString())
                .put("channel", channel)
                .put("targetChannel", targetChannel)
                .put("time", time)
    }
}
