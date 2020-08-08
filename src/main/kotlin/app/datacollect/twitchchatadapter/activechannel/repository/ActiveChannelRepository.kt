package app.datacollect.twitchchatadapter.activechannel.repository

import app.datacollect.twitchchatadapter.activechannel.domain.ActiveChannel
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.time.Instant

@Repository
class ActiveChannelRepository(private val jdbcTemplate: NamedParameterJdbcTemplate) {

    fun insertActiveChannel(channel: String, joinedTime: Instant) {
        jdbcTemplate.update("INSERT INTO active_channel (channel, joined_time) VALUES (:channel, :joined_time)",
                mapOf("channel" to channel, "joined_time" to joinedTime.toString()))
    }

    fun getActiveChannels(): List<ActiveChannel> {
        return jdbcTemplate.query("SELECT id, channel, joined_time FROM active_channel ORDER BY id", this::mapRow)
    }

    private fun mapRow(resultSet: ResultSet, rowNum: Int): ActiveChannel {
        return ActiveChannel(resultSet.getLong("id"), resultSet.getString("channel"), Instant.parse(resultSet.getString("joined_time")))
    }
}
