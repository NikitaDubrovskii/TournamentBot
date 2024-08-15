package dev.dubrovsky.repository.tournament;

import dev.dubrovsky.model.tournament.TournamentParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentParticipantRepository extends JpaRepository<TournamentParticipant, Long> {
}
