package dev.dubrovsky.service.user;

import dev.dubrovsky.model.user.User;
import dev.dubrovsky.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean isUserRegistered(Long chatId) {
        User user = userRepository.findByChatId(chatId);
        return user != null && user.getRegistered();
    }

    public void registerUser(String username, Long chatId) {
        User user = new User(username, chatId, true);
        userRepository.save(user);
    }

    public void updateName(Long chatId, String name) {
        User user = userRepository.findByChatId(chatId);
        if (user != null) {
            user.setName(name);
            userRepository.save(user);
        }
    }

    public String getName(Long chatId) {
        User user = userRepository.findByChatId(chatId);
        return user != null ? user.getName() : null;
    }

    public void deleteName(Long chatId) {
        User user = userRepository.findByChatId(chatId);
        if (user != null) {
            user.setName(null);
            userRepository.save(user);
        }
    }

}
