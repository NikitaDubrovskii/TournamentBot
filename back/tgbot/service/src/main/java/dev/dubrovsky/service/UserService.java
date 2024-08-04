package dev.dubrovsky.service;

import dev.dubrovsky.model.User;
import dev.dubrovsky.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(String username, Long chatId) {
        User user = new User(username, chatId);
        userRepository.save(user);
    }

}
