package dev.dubrovsky.controller.tournament;

import dev.dubrovsky.payload.tournament.NewTournamentPayload;
import dev.dubrovsky.service.tournament.TournamentService;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/tournament")
@CrossOrigin(origins = "https://tournamentbot-b7tb.onrender.com")
@AllArgsConstructor
public class TournamentController {

    private final TournamentService tournamentService;

    @PostMapping("/add")
    public ResponseEntity<?> createTournament(@RequestBody NewTournamentPayload tournamentPayload,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body("Validation errors: " + errors);
        } else {
            tournamentService.createTournament(tournamentPayload);
            return ResponseEntity.ok("Tournament created");
        }
    }

}
