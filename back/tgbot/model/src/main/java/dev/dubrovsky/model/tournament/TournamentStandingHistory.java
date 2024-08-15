package dev.dubrovsky.model.tournament;

import dev.dubrovsky.model.participant.Participant;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tournament_standings_history")
@Getter
@Setter
@NoArgsConstructor
public class TournamentStandingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tournament_history_id", nullable = false)
    private TournamentHistory tournamentHistory;

    @ManyToOne
    @JoinColumn(name = "participant_id", nullable = false)
    private Participant participant;

    @Column(name = "wins", nullable = false)
    private Integer wins = 0;

    @Column(name = "losses", nullable = false)
    private Integer losses = 0;

    @Column(name = "draws", nullable = false)
    private Integer draws = 0;

    @Column(name = "goals_for", nullable = false)
    private Integer goalsFor = 0;

    @Column(name = "goals_against", nullable = false)
    private Integer goalsAgainst = 0;

    @Column(name = "points", nullable = false)
    private Integer points = 0;

}
