package dev.dubrovsky.repository.standing;

import dev.dubrovsky.model.participant.Participant;
import dev.dubrovsky.model.standing.Standing;
import dev.dubrovsky.model.tournament.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StandingRepository extends JpaRepository<Standing, Long> {

    List<Standing> findAllByTournamentIsActive(Boolean isActive);

    List<Standing> findAllByTournamentIsActiveTrue();

    Optional<Standing> findById(Long id);

    Optional<Standing> findByParticipantAndTournament(Participant team1, Tournament tournament);

}
