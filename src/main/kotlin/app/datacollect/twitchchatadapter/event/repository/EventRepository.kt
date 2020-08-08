package app.datacollect.twitchchatadapter.event.repository

import app.datacollect.twitchchatadapter.event.domain.RawEvent
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class EventRepository(private val jdbcTemplate: NamedParameterJdbcTemplate) {
    fun saveEvent(rawEvent: RawEvent) {
        jdbcTemplate.update("""INSERT INTO event (id, event_type, object_type, version, time, raw_data) 
                    VALUES(:id, :event_type, :object_type, :version, :time, :raw_data)""",
                mapOf(
                        "id" to rawEvent.id,
                        "event_type" to rawEvent.eventType.name,
                        "object_type" to rawEvent.objectType.name,
                        "version" to rawEvent.version.name,
                        "time" to rawEvent.time.toString(),
                        "raw_data" to rawEvent.rawData))
    }
}
