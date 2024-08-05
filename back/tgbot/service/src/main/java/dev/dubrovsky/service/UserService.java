package dev.dubrovsky.service;

import dev.dubrovsky.model.User;
import dev.dubrovsky.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private final UserRepository userRepository;

    private Map<Long, Boolean> waitingForNameMap = new HashMap<>();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    public boolean isWaitingForName(Long userId) {
        return waitingForNameMap.getOrDefault(userId, false);
    }

    public void setWaitingForName(Long userId, boolean isWaiting) {
        waitingForNameMap.put(userId, isWaiting);
    }

}
