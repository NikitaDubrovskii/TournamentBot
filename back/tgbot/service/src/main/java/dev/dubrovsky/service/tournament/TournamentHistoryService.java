package dev.dubrovsky.service.tournament;

import dev.dubrovsky.repository.tournament.TournamentHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TournamentHistoryService {

    public final TournamentHistoryRepository tournamentHistoryRepository;

}
