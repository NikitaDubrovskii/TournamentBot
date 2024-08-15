package dev.dubrovsky.model.participant;

import dev.dubrovsky.model.match.Match;
import dev.dubrovsky.model.tournament.TournamentParticipant;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "participants")
@Getter
@Setter
@NoArgsConstructor
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false, unique = true)
    private Integer userId;

    @Column(name = "username", nullable = false)
    private String username;

    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TournamentParticipant> tournaments;

    @OneToMany(mappedBy = "team1", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Match> matchesAsTeam1;

    @OneToMany(mappedBy = "team2", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Match> matchesAsTeam2;

}
