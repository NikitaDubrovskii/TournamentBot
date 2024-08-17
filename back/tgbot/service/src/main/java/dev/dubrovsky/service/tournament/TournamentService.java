package dev.dubrovsky.service.tournament;

import dev.dubrovsky.model.match.Match;
import dev.dubrovsky.model.participant.Participant;
import dev.dubrovsky.model.standing.Standing;
import dev.dubrovsky.model.tournament.Tournament;
import dev.dubrovsky.model.tournament.TournamentParticipant;
import dev.dubrovsky.model.tournament.TournamentParticipantId;
import dev.dubrovsky.payload.participant.NewParticipantPayload;
import dev.dubrovsky.payload.tournament.NewTournamentPayload;
import dev.dubrovsky.repository.match.MatchRepository;
import dev.dubrovsky.repository.participant.ParticipantRepository;
import dev.dubrovsky.repository.standing.StandingRepository;
import dev.dubrovsky.repository.tournament.TournamentParticipantRepository;
import dev.dubrovsky.repository.tournament.TournamentRepository;
import dev.dubrovsky.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final ParticipantRepository participantRepository;
    private final TournamentParticipantRepository tournamentParticipantRepository;
    private final UserRepository userRepository;
    private final MatchRepository matchRepository;
    private final StandingRepository standingRepository;

    @Transactional
    public void createTournament(NewTournamentPayload tournamentPayload) {
        Tournament tournament = new Tournament();
        tournament.setName(tournamentPayload.name());
        tournament.setFormat(tournamentPayload.format());
        tournamentRepository.save(tournament);

        List<Participant> participants = new ArrayList<>();
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

            participants.add(participant);

            Standing standing = new Standing();
            standing.setParticipant(participant);
            standing.setTournament(tournament);
            standingRepository.save(standing);
        }

        generateMatches(tournament, participants);
    }

    private void deactivatePreviousTournament() {
        Tournament activeTournament = tournamentRepository.findByIsActiveTrue();
        if (activeTournament != null) {
            activeTournament.setIsActive(false);
            tournamentRepository.save(activeTournament);
        }
    }

    private void generateMatches(Tournament tournament, List<Participant> participants) {
        int numParticipants = participants.size();

        for (int i = 0; i < numParticipants - 1; i++) {
            for (int j = i + 1; j < numParticipants; j++) {
                // Создание матча для home - away
                Match match = new Match();
                match.setTournament(tournament);
                match.setTeam1(participants.get(i));
                match.setTeam2(participants.get(j));
                matchRepository.save(match);
            }
        }

        /*for (int i = 0; i < numParticipants - 1; i++) {
            for (int j = i + 1; j < numParticipants; j++) {
                // Создание матча для home - away
                Match match1 = new Match();
                match1.setTournament(tournament);
                match1.setHomeParticipant(participants.get(i));
                match1.setAwayParticipant(participants.get(j));
                matchRepository.save(match1);

                // Создание матча для away - home
                Match match2 = new Match();
                match2.setTournament(tournament);
                match2.setHomeParticipant(participants.get(j));
                match2.setAwayParticipant(participants.get(i));
                matchRepository.save(match2);
            }
        }*/
    }

}
