package dev.dubrovsky.repository.user;

import dev.dubrovsky.model.user.UserStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatisticsRepository extends JpaRepository<UserStatistics, Integer> {



}
