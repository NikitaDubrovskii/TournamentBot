package dev.dubrovsky.repository.tournament;

import dev.dubrovsky.model.tournament.TournamentStandingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentStandingHistoryRepository extends JpaRepository<TournamentStandingHistory, Long> {



}
