package dev.dubrovsky.repository.user;

import dev.dubrovsky.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findByChatId(Long chatId);

}
