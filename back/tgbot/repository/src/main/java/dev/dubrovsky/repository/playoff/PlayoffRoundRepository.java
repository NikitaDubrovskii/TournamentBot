package dev.dubrovsky.repository.playoff;

import dev.dubrovsky.model.playoff.PlayoffRound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayoffRoundRepository extends JpaRepository<PlayoffRound, Long> {



}
