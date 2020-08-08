package app.datacollect.twitchchatadapter.twitchconnection

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TwitchConnectionConfig {

    @Bean
    fun twitchConnection(@Value("\${twitch.host}") host: String,
                         @Value("\${twitch.port}") port: Int,
                         @Value("\${twitch.username}") username: String,
                         @Value("\${twitch.token}") token: String): TwitchConnection {
        val twitchConnection = TwitchConnection(host, port, username, token)
        twitchConnection.login()
        return twitchConnection
    }
}
