package app.datacollect.twitchchatadapter.event.domain

import org.json.JSONObject
import java.util.*

class HostDisabledEventV1 : EventData {

    val id: UUID
    val channel: String
    val time: String

    constructor(id: UUID, channel: String, time: String) {
        this.id = id
        this.channel = channel
        this.time = time
    }

    constructor(jsonObject: JSONObject) {
        this.id = UUID.fromString(jsonObject.getString("id"))
        this.channel = jsonObject.getString("channel")
        this.time = jsonObject.getString("time")
    }

    override fun getEventType(): EventType {
        return EventType.HOST_DISABLED
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
                .put("time", time)
    }
}
