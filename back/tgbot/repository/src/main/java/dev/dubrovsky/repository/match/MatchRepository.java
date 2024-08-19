package dev.dubrovsky.repository.match;

import dev.dubrovsky.model.match.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findByTournamentIsActive(Boolean isActive);

    List<Match> findByTournamentIsActiveTrue();

}
