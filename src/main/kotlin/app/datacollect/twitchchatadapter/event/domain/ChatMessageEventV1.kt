package app.datacollect.twitchchatadapter.event.domain

import org.json.JSONObject
import java.util.*

class ChatMessageEventV1 : EventData {

    val id: UUID
    val username: String
    val displayName: String
    val message: String
    val channel: String
    val userId: Long
    val roomId: Long
    val mod: Boolean
    val subscriber: Boolean
    val turbo: Boolean
    val time: String

    constructor(id: UUID,
                username: String,
                displayName: String,
                message: String,
                channel: String,
                userId: Long,
                roomId: Long,
                mod: Boolean,
                subscriber: Boolean,
                turbo: Boolean,
                time: String) {
        this.id = id
        this.username = username
        this.displayName = displayName
        this.message = message
        this.channel = channel
        this.userId = userId
        this.roomId = roomId
        this.mod = mod
        this.subscriber = subscriber
        this.turbo = turbo
        this.time = time
    }

    constructor(jsonObject: JSONObject) {
        this.id = UUID.fromString(jsonObject.getString("id"))
        this.username = jsonObject.getString("username")
        this.displayName = jsonObject.getString("displayName")
        this.message = jsonObject.getString("message")
        this.channel = jsonObject.getString("channel")
        this.userId = jsonObject.getLong("userId")
        this.roomId = jsonObject.getLong("roomId")
        this.mod = jsonObject.getBoolean("mod")
        this.subscriber = jsonObject.getBoolean("subscriber")
        this.turbo = jsonObject.getBoolean("turbo")
        this.time = jsonObject.getString("time")
    }

    override fun getEventType(): EventType {
        return EventType.CHAT_MESSAGE_SNAPSHOT
    }

    override fun getObjectType(): ObjectType {
        return ObjectType.CHAT_MESSAGE
    }

    override fun getVersion(): Version {
        return Version.V1
    }

    override fun toJson(): JSONObject {
        return JSONObject()
                .put("id", id.toString())
                .put("username", username)
                .put("displayName", displayName)
                .put("message", message)
                .put("channel", channel)
                .put("userId", userId)
                .put("roomId", roomId)
                .put("mod", mod)
                .put("subscriber", subscriber)
                .put("turbo", turbo)
                .put("time", time)
    }
}
