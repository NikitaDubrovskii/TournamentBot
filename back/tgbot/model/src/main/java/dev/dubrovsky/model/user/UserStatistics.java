package dev.dubrovsky.model.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_statistics")
@Getter
@Setter
@NoArgsConstructor
public class UserStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "telegram_id", nullable = false, unique = true)
    private Integer telegramId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "total_wins", nullable = false)
    private Integer totalWins = 0;

    @Column(name = "total_losses", nullable = false)
    private Integer totalLosses = 0;

    @Column(name = "total_draws", nullable = false)
    private Integer totalDraws = 0;

    @Column(name = "total_tournaments_won", nullable = false)
    private Integer totalTournamentsWon = 0;

}
