package dev.dubrovsky.controller.standing;

import dev.dubrovsky.service.standing.StandingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/standing")
@AllArgsConstructor
public class StandingController {

    private final StandingService standingService;

}
