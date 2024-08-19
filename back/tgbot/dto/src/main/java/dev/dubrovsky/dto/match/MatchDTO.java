package dev.dubrovsky.dto.match;

import dev.dubrovsky.model.match.Match;
import dev.dubrovsky.model.participant.Participant;
import dev.dubrovsky.model.tournament.Tournament;
import dev.dubrovsky.model.tournament.TournamentParticipant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchDTO {

    private Long id;

    private Long tournamentId;

    private String team1Name;

    private String team2Name;

    private Integer team1Score;

    private Integer team2Score;

    public MatchDTO(Match match) {
        this.id = match.getId();
        this.tournamentId = match.getTournament().getId();
        this.team1Name = getTeamName(match.getTeam1(), match.getTournament());
        this.team2Name = getTeamName(match.getTeam2(), match.getTournament());
        this.team1Score = match.getTeam1Score();
        this.team2Score = match.getTeam2Score();
    }

    private String getTeamName(Participant participant, Tournament tournament) {
        return participant.getTournaments().stream()
                .filter(tp -> tp.getTournament().equals(tournament))
                .map(TournamentParticipant::getTeamName)
                .findFirst()
                .orElse("");
    }

}
