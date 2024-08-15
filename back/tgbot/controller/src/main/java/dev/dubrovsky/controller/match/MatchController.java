package dev.dubrovsky.controller.match;

import dev.dubrovsky.service.match.MatchService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/match")
@AllArgsConstructor
public class MatchController {

    private final MatchService matchService;

}
