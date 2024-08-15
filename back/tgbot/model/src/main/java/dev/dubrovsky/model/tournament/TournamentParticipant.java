package dev.dubrovsky.model.tournament;

import dev.dubrovsky.model.participant.Participant;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tournament_participants")
@Getter
@Setter
@NoArgsConstructor
public class TournamentParticipant {

    @Id
    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @Id
    @ManyToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;

    @Column(name = "team_name")
    private String teamName;

}
