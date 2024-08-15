package dev.dubrovsky.controller.tournament;

import dev.dubrovsky.service.tournament.TournamentHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/tournament/history")
@AllArgsConstructor
public class TournamentHistoryController {

    private final TournamentHistoryService tournamentHistoryService;

}
