package app.datacollect.twitchchatadapter.collector

import app.datacollect.twitchchatadapter.activechannel.service.ActiveChannelService
import app.datacollect.twitchchatadapter.event.service.EventService
import app.datacollect.twitchchatadapter.twitchconnection.TwitchConnection
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class CollectorService(private val twitchConnection: TwitchConnection,
                       private val eventService: EventService,
                       private val activeChannelService: ActiveChannelService) {

    @Scheduled(fixedDelay = 10)
    fun listen() {
        val input = twitchConnection.read() ?: return
        val inputParts = input.split(" ").toTypedArray()
        if (shouldIgnore(inputParts[1])) {
            return
        }
        logInput(input)

        if (inputParts.isNotEmpty()) {
            when (inputParts[0]) {
                "PING" -> twitchConnection.send("PONG :tmi.twitch.tv")
                ":tmi.twitch.tv" -> {
                    when (inputParts[1]) {
                        "001" -> twitchConnection.send("CAP REQ :twitch.tv/membership")
                    }
                    when (inputParts[4]) {
                        ":twitch.tv/membership" -> twitchConnection.send("CAP REQ :twitch.tv/tags")
                        ":twitch.tv/tags" -> twitchConnection.send("CAP REQ :twitch.tv/commands")
                        ":twitch.tv/commands" -> {
                            logger.info("Initialization complete")
                            activeChannelService.joinActiveChannels()
                        }
                    }
                }
            }
            if (inputParts.size >= 3) {
                when (inputParts[2]) {
                    "PRIVMSG" -> eventService.saveChatMessageEvent(input)
                    "CLEARMSG" -> eventService.saveClearMessageEvent(input)
                    "CLEARCHAT" -> {
                        if (inputParts.size == 5) {
                            eventService.saveClearChatEvent(input)
                        } else if (inputParts.size == 4) {
                            eventService.saveGlobalClearChatEvent(input)
                        }
                    }
                    "USERNOTICE" -> eventService.saveUserNoticeEvent(input)
                }
                when (inputParts[1]) {
                    "JOIN" -> eventService.saveUserJoinEvent(input)
                    "PART" -> eventService.saveUserLeaveEvent(input)
                    "HOSTTARGET" -> {
                        if (!input.contains(":-")) {
                            eventService.saveHostEnabledEvent(input)
                        } else {
                            eventService.saveHostDisabledEvent(input)
                        }
                    }
                }
            }
        }

        // inputParts[1] == 001 -> Logged in, send: CAP REQ :twitch.tv/membership
        // inputParts[1] == CAP * ACK && inputParts[2] == :twitch.tv/membership -> CAP REQ :twitch.tv/tags
        // inputParts[1] == CAP * ACK && inputParts[2] == :twitch.tv/tags -> CAP REQ :twitch.tv/commands
        // inputParts[1] == CAP * ACK && inputParts[2] == :twitch.tv/commands -> Initialization complete, log?
        // inputParts[1] == JOIN -> Join event
        // inputParts[1] == PART -> Leave event
        // inputParts[1] == HOSTTARGET ->
        //                             -> input !contains :- -> Host enabled event
        //                             -> else -> Host disabled event
        // inputParts[2] == PRIVMSG -> Chat message event
        // inputParts[2] == CLEARMSG -> Clear message event
        // inputParts[2] == CLEARCHAT ->
        //                            -> inputParts.size == 5 -> Clear chat event
        //                            -> inputParts.size == 4 -> Global clear chat event
        // input == PING :tmi.twitch.tv  -> PONG :tmi.twitch.tv
    }

    private fun logInput(input: String) {
        logger.debug("FROM TWITCH >> $input")
    }

    private fun shouldIgnore(number: String): Boolean {
        return listOf("002", "003", "004", "375", "372", "376").contains(number)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(CollectorService::class.java)
    }
}
