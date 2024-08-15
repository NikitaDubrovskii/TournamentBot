package dev.dubrovsky.repository.group;

import dev.dubrovsky.model.group.GroupMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMatchRepository extends JpaRepository<GroupMatch, Integer> {



}
