package dev.dubrovsky.controller.tournament;

import dev.dubrovsky.service.tournament.TournamentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/tournament")
@AllArgsConstructor
public class TournamentController {

    private final TournamentService tournamentService;

}
