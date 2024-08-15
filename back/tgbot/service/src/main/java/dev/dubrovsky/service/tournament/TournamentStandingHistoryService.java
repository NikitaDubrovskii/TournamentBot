package dev.dubrovsky.service.tournament;

import dev.dubrovsky.repository.tournament.TournamentStandingHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TournamentStandingHistoryService {

    private final TournamentStandingHistoryRepository tournamentStandingHistoryRepository;

}
