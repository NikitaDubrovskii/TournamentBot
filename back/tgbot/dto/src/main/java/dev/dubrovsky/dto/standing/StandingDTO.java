package dev.dubrovsky.dto.standing;

import dev.dubrovsky.model.standing.Standing;
import dev.dubrovsky.model.tournament.TournamentParticipant;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StandingDTO {

    private Long tournament;

    private String participantName;

    private String teamName;

    private Integer played;

    private Integer wins;

    private Integer losses;

    private Integer draws;

    private Integer goalsFor;

    private Integer goalsAgainst;

    private Integer points;

    public StandingDTO(Standing standing) {
        this.tournament = standing.getTournament().getId();
        this.participantName = standing.getParticipant().getUsername();

        TournamentParticipant tournamentParticipant = standing.getParticipant().getTournaments()
                .stream()
                .filter(tp -> tp.getTournament().getId().equals(tournament))
                .findFirst()
                .orElse(null);

        if (tournamentParticipant != null) {
            this.teamName = tournamentParticipant.getTeamName();
        } else {
            this.teamName = "";
        }

        this.played = standing.getPlayed();
        this.wins = standing.getWins();
        this.losses = standing.getLosses();
        this.draws = standing.getDraws();
        this.goalsFor = standing.getGoalsFor();
        this.goalsAgainst = standing.getGoalsAgainst();
        this.points = standing.getPoints();
    }

}
