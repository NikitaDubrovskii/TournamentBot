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
        /*if (standingRepository.count() == 0) {
            Tournament tournament = tournamentRepository.findByIsActive(true);

            Standing standing = new Standing();
            standing.setParticipant();
            standing.setTournament(tournament);
        }*/
        return null;
    }

}
