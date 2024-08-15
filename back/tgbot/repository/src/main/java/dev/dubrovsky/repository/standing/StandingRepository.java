package dev.dubrovsky.repository.standing;

import dev.dubrovsky.model.standing.Standing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StandingRepository extends JpaRepository<Standing, Long> {



}
