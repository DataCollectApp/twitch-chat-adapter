package app.datacollect.twitchchatadapter.event.domain

import org.json.JSONObject
import java.util.*

class ClearChatEventV1 : EventData {

    val id: UUID
    val targetUsername: String
    val targetUserId: Long
    val channel: String
    val roomId: Long
    val seconds: Long
    val time: String

    constructor(
            id: UUID,
            targetUsername: String,
            targetUserId: Long,
            channel: String,
            roomId: Long,
            seconds: Long,
            time: String) {
        this.id = id
        this.targetUsername = targetUsername
        this.targetUserId = targetUserId
        this.channel = channel
        this.roomId = roomId
        this.seconds = seconds
        this.time = time
    }

    constructor(jsonObject: JSONObject) {
        this.id = UUID.fromString(jsonObject.getString("id"))
        this.targetUsername = jsonObject.getString("targetUsername")
        this.targetUserId = jsonObject.getLong("targetUserId")
        this.channel = jsonObject.getString("channel")
        this.roomId = jsonObject.getLong("roomId")
        this.seconds = jsonObject.getLong("seconds")
        this.time = jsonObject.getString("time")
    }


    override fun getEventType(): EventType {
        return EventType.CLEAR_CHAT_SNAPSHOT
    }

    override fun getObjectType(): ObjectType {
        return ObjectType.CLEAR_CHAT
    }

    override fun getVersion(): Version {
        return Version.V1
    }

    override fun toJson(): JSONObject {
        return JSONObject()
                .put("id", id.toString())
                .put("targetUsername", targetUsername)
                .put("targetUserId", targetUserId)
                .put("channel", channel)
                .put("roomId", roomId)
                .put("seconds", seconds)
                .put("time", time)
    }
}
