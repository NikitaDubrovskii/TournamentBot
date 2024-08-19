package dev.dubrovsky.service.standing;

import dev.dubrovsky.model.standing.Standing;
import dev.dubrovsky.model.tournament.Tournament;
import dev.dubrovsky.repository.participant.ParticipantRepository;
import dev.dubrovsky.repository.standing.StandingRepository;
import dev.dubrovsky.repository.tournament.TournamentParticipantRepository;
import dev.dubrovsky.repository.tournament.TournamentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StandingService {

    public final StandingRepository standingRepository;
    private final TournamentRepository tournamentRepository;
    private final ParticipantRepository participantRepository;
    private final TournamentParticipantRepository tournamentParticipantRepository;

    public List<Standing> getAll() {
        return standingRepository.findAllByTournamentIsActiveTrue();
    }

    public Standing getById(Long id) {
        return standingRepository.findById(id).orElse(null);
    }

}
