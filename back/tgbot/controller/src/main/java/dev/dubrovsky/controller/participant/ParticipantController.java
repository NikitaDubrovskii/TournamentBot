package dev.dubrovsky.controller.participant;

import dev.dubrovsky.service.participant.ParticipantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/participant")
@AllArgsConstructor
public class ParticipantController {

    private final ParticipantService participantService;

}
