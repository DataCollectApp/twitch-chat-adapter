package app.datacollect.twitchchatadapter.activechannel.controller

import app.datacollect.twitchchatadapter.activechannel.resource.CreateActiveChannelResource
import app.datacollect.twitchchatadapter.activechannel.service.ActiveChannelService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("active-channel")
class ActiveChannelController(private val activeChannelService: ActiveChannelService) {

    @PostMapping
    fun createActiveChannel(@RequestBody resource: CreateActiveChannelResource): ResponseEntity<Void> {
        activeChannelService.insertActiveChannel(resource.channel)
        return ResponseEntity.ok().build()
    }
}
