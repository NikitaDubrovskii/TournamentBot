package dev.dubrovsky.service.tournament;

import dev.dubrovsky.repository.tournament.TournamentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TournamentService {

    private final TournamentRepository tournamentRepository;

}
