package app.datacollect.twitchchatadapter.event.service

import app.datacollect.twitchchatadapter.event.domain.EventType
import app.datacollect.twitchchatadapter.event.domain.ObjectType
import app.datacollect.twitchchatadapter.event.domain.RawEvent
import app.datacollect.twitchchatadapter.event.domain.Version
import app.datacollect.twitchchatadapter.event.repository.EventRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class EventService(private val repository: EventRepository) {
    fun saveChatMessageEvent(rawData: String) {
        val rawEvent = RawEvent(
                UUID.randomUUID(),
                EventType.CHAT_MESSAGE_SNAPSHOT,
                ObjectType.CHAT_MESSAGE,
                Version.V1,
                rawData,
                Instant.now())
        logger.debug("Saving chat message event with id '{}'", rawEvent.id)
        repository.saveEvent(rawEvent)
        logger.info("Saved chat message event with id '{}'", rawEvent.id)
    }

    fun saveUserJoinEvent(rawData: String) {
        val rawEvent = RawEvent(
                UUID.randomUUID(),
                EventType.USER_JOIN_SNAPSHOT,
                ObjectType.USER_JOIN,
                Version.V1,
                rawData,
                Instant.now())
        logger.debug("Saving user join event with id '{}'", rawEvent.id)
        repository.saveEvent(rawEvent)
        logger.info("Saved user join event with id '{}'", rawEvent.id)
    }

    fun saveUserLeaveEvent(rawData: String) {
        val rawEvent = RawEvent(
                UUID.randomUUID(),
                EventType.USER_LEAVE_SNAPSHOT,
                ObjectType.USER_LEAVE,
                Version.V1,
                rawData,
                Instant.now())
        logger.debug("Saving user leave event with id '{}'", rawEvent.id)
        repository.saveEvent(rawEvent)
        logger.info("Saved user leave event with id '{}'", rawEvent.id)
    }

    fun saveHostEnabledEvent(rawData: String) {
        val rawEvent = RawEvent(
                UUID.randomUUID(),
                EventType.HOST_ENABLED,
                ObjectType.HOST,
                Version.V1,
                rawData,
                Instant.now())
        logger.debug("Saving host enabled event with id '{}'", rawEvent.id)
        repository.saveEvent(rawEvent)
        logger.info("Saved host enabled event with id '{}'", rawEvent.id)
    }

    fun saveHostDisabledEvent(rawData: String) {
        val rawEvent = RawEvent(
                UUID.randomUUID(),
                EventType.HOST_DISABLED,
                ObjectType.HOST,
                Version.V1,
                rawData,
                Instant.now())
        logger.debug("Saving host disabled event with id '{}'", rawEvent.id)
        repository.saveEvent(rawEvent)
        logger.info("Saved host disabled event with id '{}'", rawEvent.id)
    }

    fun saveClearMessageEvent(rawData: String) {
        val rawEvent = RawEvent(
                UUID.randomUUID(),
                EventType.CLEAR_MESSAGE_SNAPSHOT,
                ObjectType.CLEAR_MESSAGE,
                Version.V1,
                rawData,
                Instant.now())
        logger.debug("Saving clear message event with id '{}'", rawEvent.id)
        repository.saveEvent(rawEvent)
        logger.info("Saved clear message event with id '{}'", rawEvent.id)
    }

    fun saveClearChatEvent(rawData: String) {
        val rawEvent = RawEvent(
                UUID.randomUUID(),
                EventType.CLEAR_CHAT_SNAPSHOT,
                ObjectType.CLEAR_CHAT,
                Version.V1,
                rawData,
                Instant.now())
        logger.debug("Saving clear chat event with id '{}'", rawEvent.id)
        repository.saveEvent(rawEvent)
        logger.info("Saved clear chat event with id '{}'", rawEvent.id)
    }

    fun saveGlobalClearChatEvent(rawData: String) {
        val rawEvent = RawEvent(
                UUID.randomUUID(),
                EventType.GLOBAL_CLEAR_CHAT_SNAPSHOT,
                ObjectType.GLOBAL_CLEAR_CHAT,
                Version.V1,
                rawData,
                Instant.now())
        logger.debug("Saving global clear chat event with id '{}'", rawEvent.id)
        repository.saveEvent(rawEvent)
        logger.info("Saved global clear chat event with id '{}'", rawEvent.id)
    }

    fun saveUserNoticeEvent(rawData: String) {
        val rawEvent = RawEvent(
                UUID.randomUUID(),
                EventType.USER_NOTICE_SNAPSHOT,
                ObjectType.USER_NOTICE,
                Version.V1,
                rawData,
                Instant.now())
        logger.debug("Saving user notice event with id '{}'", rawEvent.id)
        repository.saveEvent(rawEvent)
        logger.info("Saved user notice event with id '{}'", rawEvent.id)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(EventService::class.java)
    }
}
