package app.datacollect.twitchchatadapter.security

import dev.jarand.authprotectedrequests.annotation.EnableProtectedRequests
import dev.jarand.authprotectedrequests.annotation.ProtectRequest
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod

@Configuration
@EnableProtectedRequests(
        protectedRequests = [
            ProtectRequest(HttpMethod.POST, ["/active-channel"], "twitch-chat-adapter.active-channel.write")
        ],
        openRequests = [])
class SecurityConfig
