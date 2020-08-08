package app.datacollect.twitchchatadapter.event.domain

import org.json.JSONObject
import java.util.*

class UserJoinEventV1 : EventData {

    val id: UUID
    val username: String
    val channel: String
    val time: String

    constructor(id: UUID, username: String, channel: String, time: String) {
        this.id = id
        this.username = username
        this.channel = channel
        this.time = time
    }

    constructor(jsonObject: JSONObject) {
        this.id = UUID.fromString(jsonObject.getString("id"))
        this.username = jsonObject.getString("username")
        this.channel = jsonObject.getString("channel")
        this.time = jsonObject.getString("time")
    }

    override fun getEventType(): EventType {
        return EventType.USER_JOIN_SNAPSHOT
    }

    override fun getObjectType(): ObjectType {
        return ObjectType.USER_JOIN
    }

    override fun getVersion(): Version {
        return Version.V1
    }

    override fun toJson(): JSONObject {
        return JSONObject()
                .put("id", id.toString())
                .put("username", username)
                .put("channel", channel)
                .put("time", time)
    }
}
