package app.datacollect.twitchchatadapter.event.domain

import org.json.JSONObject

abstract class EventData {

    abstract fun getEventType(): EventType

    abstract fun getObjectType(): ObjectType

    abstract fun getVersion(): Version

    abstract fun toJson(): JSONObject
}
