package dev.dubrovsky.controller.user;

import dev.dubrovsky.model.user.User;
import dev.dubrovsky.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "https://tournamentbot-b7tb.onrender.com") //может можно убрать
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    public User getUser(@RequestParam("username") String username) {
        return userService.findByUsername(username);
    }

}
