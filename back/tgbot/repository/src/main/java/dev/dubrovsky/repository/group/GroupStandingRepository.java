package dev.dubrovsky.repository.group;

import dev.dubrovsky.model.group.GroupStanding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupStandingRepository extends JpaRepository<GroupStanding, Integer> {



}
