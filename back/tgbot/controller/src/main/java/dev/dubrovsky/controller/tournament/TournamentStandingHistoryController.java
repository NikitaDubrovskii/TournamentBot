package dev.dubrovsky.controller.tournament;

import dev.dubrovsky.service.tournament.TournamentStandingHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/standing/history")
@AllArgsConstructor
public class TournamentStandingHistoryController {

    private final TournamentStandingHistoryService tournamentStandingHistoryService;

}
