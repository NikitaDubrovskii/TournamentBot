package dev.dubrovsky.controller;

import dev.dubrovsky.model.User;
import dev.dubrovsky.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "https://tournamentbot-b7tb.onrender.com")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public User getUser(@RequestParam("username") String username) {
        return userService.findByUsername(username);
    }

}
