package dev.dubrovsky.service.tournament;

import dev.dubrovsky.model.participant.Participant;
import dev.dubrovsky.model.tournament.Tournament;
import dev.dubrovsky.model.tournament.TournamentParticipant;
import dev.dubrovsky.model.tournament.TournamentParticipantId;
import dev.dubrovsky.payload.participant.NewParticipantPayload;
import dev.dubrovsky.payload.tournament.NewTournamentPayload;
import dev.dubrovsky.repository.participant.ParticipantRepository;
import dev.dubrovsky.repository.tournament.TournamentParticipantRepository;
import dev.dubrovsky.repository.tournament.TournamentRepository;
import dev.dubrovsky.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final ParticipantRepository participantRepository;
    private final TournamentParticipantRepository tournamentParticipantRepository;
    private final UserRepository userRepository;

    public void createTournament(NewTournamentPayload tournamentPayload) {
        Tournament tournament = new Tournament();
        tournament.setName(tournamentPayload.name());
        tournament.setFormat(tournamentPayload.format());
        tournamentRepository.save(tournament);

        for (NewParticipantPayload participantPayload : tournamentPayload.participants()) {
            Participant participant = participantRepository.findByUsername(participantPayload.username())
                    .orElseGet(() -> {
                        Participant newParticipant = new Participant();
                        newParticipant.setUsername(participantPayload.username());
                        Integer id = userRepository.findByUsername(participantPayload.username()).getId();
                        newParticipant.setUserId(id);
                        return participantRepository.save(newParticipant);
                    });

            TournamentParticipantId tournamentParticipantId = new TournamentParticipantId(tournament.getId(), participant.getId());
            TournamentParticipant tournamentParticipant = new TournamentParticipant();
            tournamentParticipant.setTournamentParticipantId(tournamentParticipantId);
            tournamentParticipant.setTournament(tournament);
            tournamentParticipant.setParticipant(participant);
            tournamentParticipant.setTeamName(participantPayload.teamName());

            tournamentParticipantRepository.save(tournamentParticipant);
        }
    }

}
