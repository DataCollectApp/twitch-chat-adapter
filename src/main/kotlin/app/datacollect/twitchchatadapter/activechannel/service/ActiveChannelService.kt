package app.datacollect.twitchchatadapter.activechannel.service

import app.datacollect.twitchchatadapter.activechannel.repository.ActiveChannelRepository
import app.datacollect.twitchchatadapter.twitchconnection.TwitchConnection
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class ActiveChannelService(private val activeChannelRepository: ActiveChannelRepository,
                           private val twitchConnection: TwitchConnection) {

    fun insertActiveChannel(channel: String) {
        activeChannelRepository.insertActiveChannel(channel, Instant.now())
        twitchConnection.send("JOIN #$channel")
    }

    fun joinActiveChannels() {
        logger.debug("Joining active channels")
        val activeChannels = activeChannelRepository.getActiveChannels()
        activeChannels.forEach { twitchConnection.send("JOIN #${it.channel}") }
        logger.info("Joined {} active channels", activeChannels.size)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(ActiveChannelService::class.java)
    }
}
